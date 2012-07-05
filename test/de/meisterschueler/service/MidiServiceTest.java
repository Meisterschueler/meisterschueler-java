package de.meisterschueler.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;

import de.meisterschueler.basic.MidiEventPair;

public class MidiServiceTest {
	private MidiService midiService = new MidiService();
	
	@Test
	public void pairMidiEventsTest() {
		// AaBbCc
		// AaBbFGCfHIcghDid
		// AaBbEFCecf | GHgDhd

		NoteOn A = new NoteOn(0, 0, 10, 0); NoteOff a = new NoteOff(0, 0, 10, 0);
		NoteOn B = new NoteOn(0, 0, 20, 0); NoteOff b = new NoteOff(0, 0, 20, 0);
		NoteOn C = new NoteOn(0, 0, 30, 0); NoteOff c = new NoteOff(0, 0, 30, 0);
		NoteOn D = new NoteOn(0, 0, 40, 0); NoteOff d = new NoteOff(0, 0, 40, 0);
		NoteOn E = new NoteOn(0, 0, 60, 0); NoteOff e = new NoteOff(0, 0, 60, 0);
		NoteOn F = new NoteOn(0, 0, 70, 0); NoteOff f = new NoteOff(0, 0, 70, 0);
		NoteOn G = new NoteOn(0, 0, 80, 0); NoteOff g = new NoteOff(0, 0, 80, 0);
		NoteOn H = new NoteOn(0, 0, 90, 0); NoteOff h = new NoteOff(0, 0, 90, 0);

		List<MidiEvent> notes = new ArrayList<MidiEvent>();
		notes.add(A);
		notes.add(a);
		notes.add(B);
		notes.add(b);
		notes.add(E);
		notes.add(F);
		notes.add(C);
		notes.add(e);
		notes.add(G);
		notes.add(H);
		notes.add(c);
		notes.add(f);
		notes.add(g);
		notes.add(D);
		notes.add(h);
		notes.add(d);
		
		List<MidiEventPair> midiEventPairs = midiService.pairMidi(notes);
		
		assertEquals( A, midiEventPairs.get(0).getNoteOn() );
		assertEquals( a, midiEventPairs.get(0).getNoteOff() );
		assertEquals( B, midiEventPairs.get(1).getNoteOn() );
		assertEquals( b, midiEventPairs.get(1).getNoteOff() );
		assertEquals( E, midiEventPairs.get(2).getNoteOn() );
		assertEquals( e, midiEventPairs.get(2).getNoteOff() );
		assertEquals( F, midiEventPairs.get(3).getNoteOn() );
		assertEquals( f, midiEventPairs.get(3).getNoteOff() );
		assertEquals( C, midiEventPairs.get(4).getNoteOn() );
		assertEquals( c, midiEventPairs.get(4).getNoteOff() );
		assertEquals( G, midiEventPairs.get(5).getNoteOn() );
		assertEquals( g, midiEventPairs.get(5).getNoteOff() );
		assertEquals( H, midiEventPairs.get(6).getNoteOn() );
		assertEquals( h, midiEventPairs.get(6).getNoteOff() );
		assertEquals( D, midiEventPairs.get(7).getNoteOn() );
		assertEquals( d, midiEventPairs.get(7).getNoteOff() );
	}
}
