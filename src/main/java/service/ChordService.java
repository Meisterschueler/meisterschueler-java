package service;

import java.util.List;

import basic.MidiEventPair;
import basic.NamedChord;


public interface ChordService {

	NamedChord getNamedChord(List<MidiEventPair> notes);
}
