package de.meisterschueler.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import com.leff.midi.event.meta.TimeSignature;

import de.meisterschueler.basic.MidiEventPair;

public class MidiService {
	public void saveMidiEvents(List<MidiEventPair> midiEvents, String filename) {
		List<MidiEvent> events = unpair(midiEvents);
		
		MidiFile midi = createMidiFile(events);

		File output = new File(filename);
		try {
			midi.writeToFile(output);
		} catch(IOException e) {
			System.err.println(e);
		}
	}
	
	public List<MidiEvent> unpair(List<MidiEventPair> midiEvents) {
		List<MidiEvent> result = new ArrayList<MidiEvent>();
		for (MidiEventPair midiEventPair : midiEvents) {
			result.add(midiEventPair.getNoteOn());
			result.add(midiEventPair.getNoteOff());
		}
		Collections.sort(result);
		return result;
	}

	public MidiFile createMidiFile(List<MidiEvent> midiEvents) {
		MidiTrack tempoTrack = new MidiTrack();
		MidiTrack noteTrack = new MidiTrack();

		TimeSignature ts = new TimeSignature();
		ts.setTimeSignature(4, 4, TimeSignature.DEFAULT_METER, TimeSignature.DEFAULT_DIVISION);

		Tempo t = new Tempo();
		t.setBpm(120);

		tempoTrack.insertEvent(ts);
		tempoTrack.insertEvent(t);

		long offset = midiEvents.get(0).getTick();
		for(MidiEvent midiEvent : midiEvents) {
			if (midiEvent instanceof NoteOn) {
				NoteOn noteOn = (NoteOn) midiEvent;
				noteTrack.insertEvent(new NoteOn(noteOn.getTick()-offset, noteOn.getChannel(), noteOn.getNoteValue(), noteOn.getVelocity()));
			} else if (midiEvent instanceof NoteOff) {
				NoteOff noteOff = (NoteOff) midiEvent;
				noteTrack.insertEvent(new NoteOff(noteOff.getTick()-offset, noteOff.getChannel(), noteOff.getNoteValue(), noteOff.getVelocity()));
			}
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
