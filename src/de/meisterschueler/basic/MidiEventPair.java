package de.meisterschueler.basic;

import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;

public class MidiEventPair {
	private NoteOn noteOn;
	private NoteOff noteOff;
	
	public MidiEventPair(NoteOn noteOn, NoteOff noteOff) {
		this.noteOn = noteOn;
		this.noteOff = noteOff;
	}
	
	public NoteOn getNoteOn() {
		return noteOn;
	}
	
	public void setNoteOn(NoteOn noteOn) {
		this.noteOn = noteOn;
	}
	
	public NoteOff getNoteOff() {
		return noteOff;
	}
	
	public void setNoteOff(NoteOff noteOff) {
		this.noteOff = noteOff;
	}
}
