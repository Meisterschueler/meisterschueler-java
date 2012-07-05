package de.meisterschueler.basic;

import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;

public class MidiPair {
	private NoteOn noteOn;
	private NoteOff noteOff;
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
