package de.meisterschueler.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;

import de.meisterschueler.alignment.MatchingState;
import de.meisterschueler.alignment.NeedlemanWunsch;
import de.meisterschueler.basic.Hand;
import de.meisterschueler.basic.Key;
import de.meisterschueler.basic.MatchingItem;
import de.meisterschueler.basic.MidiEventPair;
import de.meisterschueler.basic.Score;
import de.meisterschueler.basic.Score.Status;


public class MatchingServiceImpl implements MatchingService {

	@Override
	public List<Score> simpleMatch(List<Score> scores, List<MidiEventPair> notes) {
		String seq1  = scoresToPitchSequence(scores);
		String seq2 = midiEventsToPitchSequence(notes);

		String alignment = NeedlemanWunsch.getAlignments(seq1, seq2);

		List<Score> result = merge(scores, notes, alignment);
		return result;
	}

	@Override
	public List<MidiEvent> orderByChord(List<MidiEvent> notes) {
		List<MidiEvent> result = new ArrayList<MidiEvent>();
		SortedMap<Integer, MidiEvent> chordEvents = new TreeMap<Integer, MidiEvent>();
		long timestamp = 0;
		for (MidiEvent event : notes) {
			if (event.getTick() - timestamp > 50) {
				result.addAll(chordEvents.values());
				chordEvents.clear();
				timestamp = event.getTick();
			}
			if (event instanceof NoteOn) {
				chordEvents.put(((NoteOn)event).getNoteValue(), event);
			}
		}

		result.addAll(chordEvents.values());
		return result;
	}

	@Override
	public String scoresToIntervalSequence(List<Score> scores) {
		String seq = scoresToPitchSequence(scores);
		return sequenceToIntervalSequence(seq);
	}

	@Override
	public String scoresToPitchSequence(List<Score> scores) {
		String sequence = new String();
		for (Score score : scores) {
			if (!score.isPause()) {
				sequence += (char)(score.getPitch());
			}
		}
		return sequence;
	}

	@Override
	public String midiEventsToIntervalSequence(List<MidiEventPair> notes) {
		String seq = midiEventsToPitchSequence(notes);
		return sequenceToIntervalSequence(seq);
	}

	@Override
	public String midiEventsToPitchSequence(List<MidiEventPair> notes) {
		String sequence = new String();
		for (MidiEventPair event : notes) {
			char value = (char) event.getNoteOn().getNoteValue();
			sequence += value;
		}
		return sequence;
	}

	@Override
	public String midiEventsToPressedSequence(List<MidiEventPair> notes) {
		String sequence = new String();
		for (MidiEventPair event : notes) {
			if (event.getNoteOff() != null) {
				sequence += ".";
			} else {
				sequence += "D";
			}
		}
		return sequence;
	}

	private String sequenceToIntervalSequence(String seq) {
		final int intervalOffset = 128;
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<seq.length(); i++) {
			sb.append((char)(seq.charAt(i)-seq.charAt(i-1)+intervalOffset));
		}
		return sb.toString();
	}

	@Override
	public List<Score> merge(List<Score> scores, List<MidiEventPair> notes, String alignment) {
		List<Score> result = new ArrayList<Score>();
		
		// Merge NoteOn
		ListIterator<Score> scoreIterator = scores.listIterator();
		int noteIdx = 0;
		for (int idxAlignment = 0; idxAlignment < alignment.length(); idxAlignment++) {

			Score score = null;
			if (alignment.charAt(idxAlignment) != MatchingState.INSERT) { 
				score = new Score(scoreIterator.next());
			}

			switch (alignment.charAt(idxAlignment)) {
			case MatchingState.MATCH:
				score.setStatus(Status.PLAYED);
				score.setNote(notes.get(noteIdx));
				noteIdx++;
				break;
			case MatchingState.WRONG:
				score.setStatus(Status.WRONG);
				score.setNote(notes.get(noteIdx));
				noteIdx++;
				break;
			case MatchingState.INSERT:
				score = new Score();
				score.setStatus(Status.EXTRA);
				score.setNote(notes.get(noteIdx)); 
				noteIdx++;
				break;
			case MatchingState.DELETED:
				score.setStatus(Status.MISSED);
				break;
			}
			result.add(score);
		}

		return result;
	}

	@Override
	public void matchPitch(MatchingItem item) {
		String scorePitchSequence = item.getScorePitchSequence();
		String notePitchSequence = item.getNotePitchSequence();

		String pitchAlignment = NeedlemanWunsch.getAlignments(scorePitchSequence, notePitchSequence);
		item.setPitchAlignment(pitchAlignment);
	}

	@Override
	public void matchInterval(MatchingItem item) {
		String scoreIntervalSequence = item.getScoreIntervalSequence();
		String noteIntervalSequence = item.getNoteIntervalSequence();

		String intervalAlignment = NeedlemanWunsch.getAlignments(scoreIntervalSequence, noteIntervalSequence);
		item.setIntervalAlignment(intervalAlignment);
	}

	@Override
	public void updateMerge(MatchingItem item) {
		Hand hand = item.getHand();
		List<Score> scores = item.getSong().getVoice(hand);
		List<MidiEventPair> notes = item.getNotes();
		String pitchAlignment = item.getPitchAlignment();

		List<Score> merged = merge(scores, notes, pitchAlignment);
		item.setScores(merged);
	}

	@Override
	public void updateQuality(MatchingItem item) {
		String pitchAlignment = item.getPitchAlignment();

		int firstHitChord = pitchAlignment.replaceAll("[mwi]", ".").indexOf(".");
		int lastHitChord = pitchAlignment.replaceAll("[mwi]", ".").lastIndexOf(".");

		int open = StringUtils.countMatches(pitchAlignment, String.valueOf(MatchingState.OPEN));
		int played = StringUtils.countMatches(pitchAlignment, String.valueOf(MatchingState.MATCH));
		int missed = StringUtils.countMatches(pitchAlignment, String.valueOf(MatchingState.DELETED));
		int extra = StringUtils.countMatches(pitchAlignment, String.valueOf(MatchingState.INSERT));
		int wrong = StringUtils.countMatches(pitchAlignment, String.valueOf(MatchingState.WRONG));

		double rangeFactor = (double)(played+wrong+extra)/(double)(lastHitChord - firstHitChord + 1);
		double progressFactor = (double)(played+wrong)/(double)(played+wrong+extra+open);
		double matchFactor = (double)(played)/(double)(played+wrong+extra);

		double transpositionFactor;
		if (item.getTransposition() >= 0) {
			transpositionFactor = 1.0 - item.getTransposition()/1100.0; 
		} else {
			transpositionFactor = 0.99 + item.getTransposition()/1100.0; 
		}

		double quality = rangeFactor*matchFactor*Math.max(0.9, progressFactor)*transpositionFactor;

		if (Double.isNaN(quality) || Double.isInfinite(quality)) {
			item.setQuality(0.0);
		} else {
			item.setQuality(quality);
		}
	}

	@Override
	public void updateTransposition(MatchingItem item) {
		int posAlignment = item.getIntervalAlignment().lastIndexOf("mmm");
		if (posAlignment < 0) {
			return;
		}  

		String goodString = item.getIntervalAlignment().substring(0, posAlignment);

		int scorePosition = goodString.replaceAll("i", "").length();
		int notePosition = goodString.replaceAll("d", "").length();

		char scorePitch = item.getScorePitchSequence().charAt(scorePosition);
		char notePitch = item.getNotePitchSequence().charAt(notePosition);

		item.setTransposition(notePitch - scorePitch);
	}

	@Override
	public void updateKey(MatchingItem item) {
		Key currentKey = item.getSong().getKey();
		item.setKey(currentKey.transposeByPitch(item.getTransposition()));
	}

	@Override
	public void matchPitchPrunned(MatchingItem item) {
		int saveRegion = item.getSaveRegion();
		String saveAlignment = item.getPitchAlignment().substring(0, saveRegion);

		String prunnedScorePitchSequence = item.getScorePitchSequence().substring(saveAlignment.replaceAll("[i]", "").length());
		String prunnedNotePitchSequence = item.getNotePitchSequence().substring(saveAlignment.replaceAll("[d]", "").length());

		String prunnedAlignment = NeedlemanWunsch.getAlignments(prunnedScorePitchSequence, prunnedNotePitchSequence);

		item.setPitchAlignment(saveAlignment + prunnedAlignment);
	}

	@Override
	public void updatePrunning(MatchingItem item) {
		int saveRegion = item.getPitchAlignment().lastIndexOf("mmmmmmmmmm");
		if (saveRegion == -1) {
			item.setPrunning(false);
		} else {
			item.setSaveRegion(saveRegion);
			item.setPrunning(true);
		}
	}

	@Override
	public void updateFinished(MatchingItem item) {
		String alignment = item.getPitchAlignment();
		while (alignment.endsWith("i")) {
			alignment = alignment.substring(0, alignment.length()-1);
		}
		if (alignment.endsWith("m")) {
			alignment.replace("d", "");
			int idxPressed = item.getPressedSequence().indexOf("D");
			if (idxPressed != -1 && idxPressed < alignment.length()) {
				item.setFinished(false);
			} else {
				item.setFinished(true);
			}
		} else {
			item.setFinished(false);
		}
		
//		Pattern finishedPattern = Pattern.compile("^([mwid]*[m]+)([i]*)$");
//		Matcher matcher = finishedPattern.matcher(item.getPitchAlignment());
//		if (matcher.find()) {
//			int idxPressed = item.getPressedSequence().indexOf("D");
//			if (idxPressed != -1 && idxPressed < matcher.group(0).length()) {
//				item.setFinished(false);
//			} else {
//				item.setFinished(true);
//			}
//		} else {
//			item.setFinished(false);
//		}
	}

	@Override
	public List<MidiEventPair> cutMatchingMidiEvents(MatchingItem item) {
		String pitchAlignment = item.getPitchAlignment();
		while (pitchAlignment.endsWith("i")) {
			pitchAlignment = pitchAlignment.substring(0, pitchAlignment.length()-1);
		}
		String noteAlignment = StringUtils.remove(pitchAlignment, "d");

		List<MidiEventPair> goodEvents;
		List<MidiEventPair> evilEvents;

		List<MidiEventPair> notes = item.getNotes();

		goodEvents = new ArrayList<MidiEventPair>(notes.subList(0, noteAlignment.length()));
		evilEvents = new ArrayList<MidiEventPair>(notes.subList(noteAlignment.length(), notes.size()));

		// remove tick offset from goodEvents
		long offset = goodEvents.get(0).getNoteOn().getTick();
		for (MidiEventPair midiEventPair : goodEvents) {
			offset = Math.min(offset, midiEventPair.getNoteOn().getTick() );
		}
		for (MidiEventPair midiEventPair : goodEvents) {
			NoteOn noteOn = midiEventPair.getNoteOn();
			NoteOff noteOff = midiEventPair.getNoteOff();
			midiEventPair.setNoteOn(new NoteOn(noteOn.getTick()-offset, noteOn.getChannel(), noteOn.getNoteValue(), noteOn.getVelocity()));
			midiEventPair.setNoteOff(new NoteOff(noteOff.getTick()-offset, noteOff.getChannel(), noteOff.getNoteValue(), noteOff.getVelocity()));
		}
		
		item.setNotes(goodEvents);
		item.setPitchAlignment(pitchAlignment);
		return evilEvents;
	}
}
