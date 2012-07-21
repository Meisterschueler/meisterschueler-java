package de.meisterschueler.service;

import java.util.List;

import de.meisterschueler.basic.MidiEventPair;
import de.meisterschueler.basic.NamedChord;



public interface ChordService {

	NamedChord getNamedChord(List<MidiEventPair> notes);
}
