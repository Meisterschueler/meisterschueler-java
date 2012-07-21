package de.meisterschueler.service;

import java.util.Observable;
import java.util.Observer;

import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;

public class KeyboardHandler implements Observer {

	private int[][] keymap = new int[128][128];
	private boolean[] keyboardStatus = new boolean[128];
	
	public KeyboardHandler() {
		for (int i=0; i<128; i++) {
			keyboardStatus[i] = false;
		}
	}
	
	public boolean isKeyPressed() {
		for (int i=0; i<128; i++) {
			if (keyboardStatus[i]) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof NoteOn) {
			NoteOn noteOn = (NoteOn) arg;
			keyboardStatus[noteOn.getNoteValue()] = true;
		} else if (arg instanceof NoteOff) {
			NoteOff noteOff = (NoteOff) arg;
			keyboardStatus[noteOff.getNoteValue()] = false;
		}
	}
}
