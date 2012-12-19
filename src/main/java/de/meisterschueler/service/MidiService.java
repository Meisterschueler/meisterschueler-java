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
	private static final long tickDelta = 20;	// maximum gap in a chord

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
		ArrayList<MidiEvent> events = new ArrayList<MidiEvent>();
		for (MidiEventPair midiEventPair : midiEventPairs) {
			events.add(midiEventPair.getNoteOn());
			events.add(midiEventPair.getNoteOff());
		}
		Collections.sort(events);

		return createMidiFile2(events);
	}

	public List<MidiEventPair> pairMidiEvents(List<MidiEvent> notes) {
		List<MidiEventPair> result = new ArrayList<MidiEventPair>();
		for (MidiEvent event : notes) {
			addMidi(result, event);
		}
		return result;
	}

	public MidiEvent correctMidi(MidiEvent midiEvent) {
		if (midiEvent instanceof NoteOn) {
			NoteOn noteOn = (NoteOn) midiEvent;
			if (noteOn.getVelocity() == 0) {
				NoteOff noteOff = new NoteOff(noteOn.getTick(), noteOn.getDelta(), noteOn.getChannel(), noteOn.getNoteValue(), noteOn.getVelocity());
				return noteOff;
			}
		}
		return midiEvent;
	}

	public void addMidi(List<MidiEventPair> midiEventPairs, MidiEvent midiEvent) {
		if (midiEvent instanceof NoteOn) {
			NoteOn noteOn = (NoteOn)midiEvent;
			NoteOn currentNote = noteOn;
			ListIterator<MidiEventPair> it = midiEventPairs.listIterator(midiEventPairs.size());

			// go back until next note is later
			boolean foundGoodTime = false;
			while (it.hasPrevious() && !foundGoodTime) {
				NoteOn previousNote = it.previous().getNoteOn();
				if (previousNote.getTick()  < noteOn.getTick()) {
					if (it.hasNext()) {
						it.next();
					}
					foundGoodTime = true;
				}
			}

			// go back to last gap
			boolean foundGap = false;
			while (it.hasPrevious() && !foundGap) {
				NoteOn previousNote = it.previous().getNoteOn();
				if (previousNote.getTick() + tickDelta < currentNote.getTick()) {
					if (it.hasNext()) {
						it.next();
					}
					foundGap = true;
				} else {
					currentNote = previousNote;
				}
			}

			// go forth until next note is higher
			boolean finished = false;
			NoteOn nextNote; 
			while (it.hasNext() && !finished) {
				nextNote = it.next().getNoteOn();
				if (nextNote.getNoteValue() > noteOn.getNoteValue()) {
					if (it.hasPrevious()) {
						it.previous();
					}
					finished = true;
				}
			}

			it.add(new MidiEventPair(noteOn, null));

		} else if (midiEvent instanceof NoteOff) {
			NoteOff noteOff = (NoteOff) midiEvent;
			ListIterator<MidiEventPair> it = midiEventPairs.listIterator(midiEventPairs.size());
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

	public List<MidiEventPair> loadMidiFile(MidiFile midiFile) {
		List<MidiEventPair> midiEventPairs = new ArrayList<MidiEventPair>();

		Object[] objects = midiFile.getTracks().get(1).getEvents().toArray();
		for (Object o : objects) {
			if (o instanceof MidiEvent) {
				this.addMidi(midiEventPairs, (MidiEvent)o);
			}
		}

		return midiEventPairs;
	}

	public MidiFile createMidiFile2(List<MidiEvent> midiEvents) {
		// Create Tempo Track
		MidiTrack tempoTrack = new MidiTrack();

		TimeSignature ts = new TimeSignature();
		ts.setTimeSignature(4, 4, TimeSignature.DEFAULT_METER, TimeSignature.DEFAULT_DIVISION);

		Tempo t = new Tempo();
		t.setBpm(120);

		tempoTrack.insertEvent(ts);
		tempoTrack.insertEvent(t);

		// Create Note Track
		MidiTrack noteTrack = new MidiTrack();

		for (MidiEvent event : midiEvents) {
			noteTrack.insertEvent(event);
		}

		ArrayList<MidiTrack> tracks = new ArrayList<MidiTrack>();
		tracks.add(tempoTrack);
		tracks.add(noteTrack);

		return new MidiFile(MidiFile.DEFAULT_RESOLUTION, tracks);
	}
}
