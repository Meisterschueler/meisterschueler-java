package de.meisterschueler.service;

import java.util.Observable;
import java.util.Observer;

import com.leff.midi.event.MidiEvent;

public abstract class MidiConnector extends Observable implements Observer {
	public abstract void sendMidiEvent(MidiEvent midiEvent);
}
