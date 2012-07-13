package service;

import java.util.Observable;
import java.util.Observer;

import javax.sound.midi.MidiEvent;

public abstract class MidiConnector extends Observable implements Observer {
	public abstract void sendMidiEvent(MidiEvent midiEvent);
}
