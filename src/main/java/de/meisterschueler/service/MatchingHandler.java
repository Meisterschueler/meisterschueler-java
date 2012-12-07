package de.meisterschueler.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;

import com.leff.midi.event.MidiEvent;

import de.meisterschueler.basic.Hand;
import de.meisterschueler.basic.MatchingItem;
import de.meisterschueler.basic.MidiEventPair;
import de.meisterschueler.basic.Score;
import de.meisterschueler.basic.Song;
import de.meisterschueler.service.SignalService.Signal;

public class MatchingHandler {

	private KeyboardHandler keyboardHandler = new KeyboardHandler();

	private MatchingService matchingService = new MatchingServiceImpl();
	private SignalService signalService;
	private ResultListener resultListener;
	private MidiService midiService = new MidiService();

	public void setSignalService(SignalService signalService) {
		this.signalService = signalService;
	}

	public void setResultListener(ResultListener resultListener) {
		this.resultListener = resultListener;
	}

	public enum Status { PLAYING, WAITING, INIT };
	private Status status = Status.INIT;

	private List<MatchingItem> matchingItems = new ArrayList<MatchingItem>();
	private List<MidiEventPair> midiEvents = new ArrayList<MidiEventPair>();

	private MatchingItem bestMatchingItem;

	private MultiMap qualityMap;

	private List<Song> songs;

	public void initMatchingItems() {
		matchingItems.clear();
		for (Song song : songs) {
			MatchingItem item = new MatchingItem();
			item.setSong(song);
			item.setHand(Hand.LEFT);
			List<Score> scores = song.getVoice(Hand.LEFT);
			item.setScores(scores);
			matchingItems.add(item);

			item = new MatchingItem();
			item.setSong(song);
			item.setHand(Hand.RIGHT);
			scores = song.getVoice(Hand.RIGHT);
			item.setScores(scores);
			matchingItems.add(item);
		}

		for (MatchingItem item : matchingItems) {
			List<Score> flatScores = matchingService.getFlatScores(item.getScores());
			String pitchSequence = matchingService.scoresToPitchSequence(flatScores);
			String intervalSequence = matchingService.scoresToIntervalSequence(flatScores);

			item.setScorePitchSequence(pitchSequence);
			item.setScoreIntervalSequence(intervalSequence);

			item.setTransposition(0);
			item.setPrunning(false);
		}
	}

	public void match(MidiEvent midiEvent) {
		//keyboardHandler.update(null, midiEvent); TODO: des gehört woanerscht hi !

		String oldPitchSequence = matchingService.midiEventsToPitchSequence(midiEvents);
		MidiEvent correctedMidiEvent = midiService.correctMidi(midiEvent);
		midiService.addMidi(midiEvents, correctedMidiEvent);

		match(oldPitchSequence);
	}
	
	public void match(List<MidiEventPair> midiEventPairs) {
		midiEvents.clear();
		
		for (MidiEventPair pair : midiEventPairs) {
			String oldPitchSequence = matchingService.midiEventsToPitchSequence(midiEvents);
			midiEvents.add(pair);
			match(oldPitchSequence);
		}
	}

	synchronized private void match(String oldPitchSequence) {

		String pitchSequence = matchingService.midiEventsToPitchSequence(midiEvents);
		String intervalSequence = matchingService.midiEventsToIntervalSequence(midiEvents);
		String pressedSequence = matchingService.midiEventsToPressedSequence(midiEvents);

		boolean pitchSequenceChanged = (!oldPitchSequence.equals(pitchSequence) || status == Status.INIT);
		boolean onePrunning = false;

		// Mit allen items matchen
		for (MatchingItem item : matchingItems) {

			// MatchingItem vorbereiten
			item.setNotes(midiEvents);
			item.setNotePitchSequence(pitchSequence);
			item.setNoteIntervalSequence(intervalSequence);
			item.setPressedSequence(pressedSequence);

			if (pitchSequenceChanged) {
				if (!item.isPrunning()) {
					onePrunning = true;

					// Tonart ermitteln
					matchingService.matchInterval(item);
					matchingService.updateTransposition(item);
					matchingService.updateKey(item);

					matchingService.matchPitch(item);
				} else {
					matchingService.matchPitchPrunned(item);
				}
			}

			matchingService.updateQuality(item);
			matchingService.updatePrunning(item);
		}

		// Bestes Match ermitteln
		updateQualityMap();
		List<Double> qualities = getQualities();
		Double bestQuality = qualities.get(qualities.size()-1);
		List<MatchingItem> bestItems = (List<MatchingItem>)qualityMap.get(bestQuality);
		bestMatchingItem = bestItems.get(0);

		// Scores und MidiEvents matchen
		matchingService.updateMerge(bestMatchingItem);

		// Checken, ob beendet
		matchingService.updateFinished(bestMatchingItem);

		if (bestMatchingItem.isFinished()) {
			// Events teilen
			midiEvents = matchingService.cutMatchingMidiEvents(bestMatchingItem);

			resultListener.gotResult(bestMatchingItem);
			signalService.sendSignal(Signal.DONG);

			initMatchingItems();

			// Status updaten
			status = Status.INIT;
		} else {
			// Schlechte disablen
			if (midiEvents.size() >= 8) {
				Double qualityLimit;
				if (onePrunning) {
					qualityLimit = bestQuality;
				} else {
					qualityLimit = bestQuality/5.0;
				}

				for (Double quality : qualities) {
					if (quality < qualityLimit) {
						List<MatchingItem> worstItems = (List<MatchingItem>)qualityMap.get(quality);
						for (MatchingItem worstItem : worstItems) {
							matchingItems.remove(worstItem);
						}
					}
				}
			}

			// Status updaten
			if (keyboardHandler.isKeyPressed()) {
				status = Status.PLAYING;
			} else if (!keyboardHandler.isKeyPressed()) {
				status = Status.WAITING;
			}
		}
	}

	private void updateQualityMap() {
		// Matches in eine QualityMap packen
		qualityMap = new MultiHashMap();
		for (MatchingItem item : matchingItems) {
			qualityMap.put(item.getQuality(), item);
		}
	}

	private List<Double> getQualities() {
		List<Double> qualities = new ArrayList<Double>();
		qualities.addAll(qualityMap.keySet());
		Collections.sort(qualities);
		return qualities;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public MatchingItem getBestMatchingItem() {
		return bestMatchingItem;
	}

	public Status getStatus() {
		return status;
	}

	public void resetMidiEvents() {
		midiEvents.clear();
	}
}

