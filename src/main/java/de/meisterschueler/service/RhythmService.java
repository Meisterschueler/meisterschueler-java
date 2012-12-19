package de.meisterschueler.service;

import java.util.List;

import de.meisterschueler.basic.MidiEventPair;
import de.meisterschueler.basic.Rhythm;

public class RhythmService {

	public Rhythm getRhythm(List<MidiEventPair> notes) {
		for (int i=1; i<notes.size()-1; i++) {
			
		}
		return Rhythm.NONE;
	}
}
