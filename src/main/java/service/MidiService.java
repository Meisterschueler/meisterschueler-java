package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import basic.MidiEventPair;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import com.leff.midi.event.meta.TimeSignature;


public class MidiService {
	public void saveMidiEvents(List<MidiEventPair> midiEventPairs, String filename) {
		MidiFile midi = createMidiFile(midiEventPairs);

		File output = new File(filename);
		try {
			midi.writeToFile(output);
		} catch(IOException e) {
			System.err.println(e);
		}
	}

	public MidiFile createMidiFile(List<MidiEventPair> midiEventPairs) {
		MidiTrack tempoTrack = new MidiTrack();
		MidiTrack noteTrack = new MidiTrack();

		TimeSignature ts = new TimeSignature();
		ts.setTimeSignature(4, 4, TimeSignature.DEFAULT_METER, TimeSignature.DEFAULT_DIVISION);

		Tempo t = new Tempo();
		t.setBpm(120);

		tempoTrack.insertEvent(ts);
		tempoTrack.insertEvent(t);

		for(MidiEventPair midiEvent : midiEventPairs) {
			NoteOn noteOn = midiEvent.getNoteOn();
			NoteOff noteOff = midiEvent.getNoteOff();
			noteTrack.insertNote(noteOn.getChannel(), noteOn.getNoteValue(), noteOn.getVelocity(), noteOn.getTick(), noteOff.getTick()-noteOn.getTick());
		}

		ArrayList<MidiTrack> tracks = new ArrayList<MidiTrack>();
		tracks.add(tempoTrack);
		tracks.add(noteTrack);

		return new MidiFile(MidiFile.DEFAULT_RESOLUTION, tracks);
	}

	public List<MidiEventPair> pairMidi(List<MidiEvent> notes) {
		List<MidiEventPair> result = new ArrayList<MidiEventPair>();
		for (MidiEvent event : notes) {
			addMidi(result, event);
		}
		return result;
	}

	public void addMidi(List<MidiEventPair> midiEvents, MidiEvent midiEvent) {	
		if (midiEvent instanceof NoteOn) {
			midiEvents.add(new MidiEventPair((NoteOn) midiEvent, null));
		} else if (midiEvent instanceof NoteOff){
			NoteOff noteOff = (NoteOff) midiEvent;
			ListIterator<MidiEventPair> it = midiEvents.listIterator(midiEvents.size());
			boolean found = false;
			while (it.hasPrevious() && !found) {
				MidiEventPair previous = it.previous();
				if (previous.getNoteOn().getNoteValue() == noteOff.getNoteValue() && previous.getNoteOff() == null) {
					previous.setNoteOff(noteOff);
					found = true;
				}
			}
			if (!found) {
				System.err.println("Oops... I did not found the passende Note, Alta!");
			}
		}
	}
}
