package de.meisterschueler.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

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
	
	private ReentrantLock lock = new ReentrantLock();

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

	private String oldPitchSequence;

	public void initMatchingItems() {
		lock.lock();
		
		oldPitchSequence = "";
		matchingItems.clear();
		for (Song song : songs) {
			for (Hand hand : song.getHands()) {
				MatchingItem item = new MatchingItem();
				item.setSong(song);
				item.setHand(hand);
				List<Score> scores = song.getVoice(hand);

				String pitchSequence = matchingService.scoresToPitchSequence(scores);
				String intervalSequence = matchingService.scoresToIntervalSequence(scores);

				item.setScorePitchSequence(pitchSequence);
				item.setScoreIntervalSequence(intervalSequence);

				item.setTransposition(0);
				item.setPrunning(false);	
				matchingItems.add(item);
			}
		}
		
		lock.unlock();
	}

	public void match(MidiEvent midiEvent) {
		//keyboardHandler.update(null, midiEvent); TODO: des geh�rt woanerscht hi !

		MidiEvent correctedMidiEvent = midiService.correctMidi(midiEvent);
		midiService.addMidi(midiEvents, correctedMidiEvent);

		match();
	}

	public void matchAllPairs(List<MidiEventPair> midiEventPairs) {
		midiEvents.clear();

		for (MidiEventPair pair : midiEventPairs) {
			midiEvents.add(pair);
		}

		match();
	}

	public void matchAllEvents(List<MidiEvent> allMidiEvents) {
		midiEvents.clear();

		for (MidiEvent midiEvent : allMidiEvents) {
			midiService.addMidi(midiEvents, midiEvent);
		}

		// Performance reasons: match at least one small part to disable 
		// non matching items as early as possible before we match the whole part
		if (midiEvents.size() > 32) {
			List<MidiEventPair> all = midiEvents;
			List<MidiEventPair> part1 = midiEvents.subList(0, 16);
			List<MidiEventPair> part2 = midiEvents.subList(0, 32);

			midiEvents = part1;
			match();

			midiEvents = part2;
			match();

			midiEvents = all;
			match();
		} else {
			match();
		}
	}

	synchronized private void match() {
		
		lock.lock();
		
		String pitchSequence = matchingService.midiEventsToPitchSequence(midiEvents);
		String intervalSequence = matchingService.midiEventsToIntervalSequence(midiEvents);
		String pressedSequence = matchingService.midiEventsToPressedSequence(midiEvents);

		boolean pitchSequenceChanged = (!oldPitchSequence.equals(pitchSequence) || status == Status.INIT);
		boolean onePrunning = false;

		oldPitchSequence = pitchSequence;

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

		// Checken, ob beendet
		matchingService.updateFinished(bestMatchingItem);

		if (bestMatchingItem.isFinished()) {
			// Events von dem Folgelied abtrennen
			midiEvents = matchingService.cutMatchingMidiEvents(bestMatchingItem);

			List<MidiEventPair> matchingMidiEvents = bestMatchingItem.getNotes();
			bestMatchingItem.setNotePitchSequence(matchingService.midiEventsToPitchSequence(matchingMidiEvents));
			bestMatchingItem.setNoteIntervalSequence(matchingService.midiEventsToIntervalSequence(matchingMidiEvents));
			bestMatchingItem.setPressedSequence(matchingService.midiEventsToPressedSequence(matchingMidiEvents));
			
			// Scores und MidiEvents matchen
			matchingService.updateMerge(bestMatchingItem);
			
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
		
		lock.unlock();
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

