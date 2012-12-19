package de.meisterschueler.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.leff.midi.MidiFile;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;

import de.meisterschueler.service.SignalService.Signal;

public class MidiRecorder {

	private SignalService signalService;
	private List<MidiEvent> midiEvents = new ArrayList<MidiEvent>();
	private boolean recording;
	private MidiService midiService;
	
	public void setSignalService(SignalService signalService) {
		this.signalService = signalService;
	}
	
	public void setMidiService(MidiService midiService) {
		this.midiService = midiService;
	}

	public void startPlaying() {
		// ...
	}
	
	public void stopPlaying() {
		// TODO Auto-generated method stub
	}

	public void startRecording() {
		if (!recording) {
			midiEvents.clear();
			recording = true;
		}
	}
	
	public void stopRecording() {
		recording = false;
		MidiFile file = midiService.createMidiFile2(midiEvents);
		try {
			file.writeToFile(new File("Record" + System.currentTimeMillis() + ".mid"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		signalService.sendSignal(Signal.RECORDING_FINISHED);
	}

	public void noteOn(NoteOn noteOn) {
		if (recording) {
			midiEvents.add(noteOn);
		}
	}

	public void noteOff(NoteOff noteOff) {
		if (recording) {
			midiEvents.add(noteOff);
		}
	}

}
