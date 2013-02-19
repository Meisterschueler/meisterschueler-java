package de.meisterschueler.service;

import java.util.List;


import com.leff.midi.event.MidiEvent;

import de.meisterschueler.basic.MatchingItem;
import de.meisterschueler.basic.MidiEventPair;
import de.meisterschueler.basic.Score;


public interface MatchingService {
	public abstract List<Score> simpleMatch(List<Score> scores, List<MidiEventPair> notes);
	
	public abstract List<MidiEvent> orderByChord(List<MidiEvent> notes);

	public abstract String scoresToIntervalSequence(List<Score> scores);

	public abstract String scoresToPitchSequence(List<Score> scores);
	
	public abstract String midiEventsToIntervalSequence(List<MidiEventPair> notes);

	public abstract String midiEventsToPitchSequence(List<MidiEventPair> midiEventPairs);

	public abstract String midiEventsToPressedSequence(List<MidiEventPair> notes);

	public abstract void updateMerge(MatchingItem item);

	public abstract void updateQuality(MatchingItem item);

	public abstract void matchPitch(MatchingItem item);

	public abstract void matchInterval(MatchingItem item);

	public abstract void matchPitchPrunned(MatchingItem item);

	public abstract void updateTransposition(MatchingItem item);

	public abstract void updateKey(MatchingItem item);

	public abstract void updatePrunning(MatchingItem item);

	public abstract void updateFinished(MatchingItem item);

	public abstract List<MidiEventPair> cutMatchingMidiEvents(MatchingItem item);

	public abstract List<Score> merge(List<Score> scores, List<MidiEventPair> notes, String alignment);
}
