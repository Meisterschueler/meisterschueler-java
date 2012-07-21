package de.meisterschueler.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import de.meisterschueler.basic.Key;
import de.meisterschueler.basic.MidiEventPair;
import de.meisterschueler.basic.NamedChord;
import de.meisterschueler.service.ChordService;
import de.meisterschueler.service.ChordServiceImpl;
import de.meisterschueler.service.GuidoService;




public class ChordServiceTest {

	private ChordService chordService = new ChordServiceImpl();
	private GuidoService guidoService = new GuidoService();
	
	@Test
	public void simpleChordTest() {
	    List<MidiEventPair> notes = guidoService.gmnToMidi("c e g");
	    NamedChord namedChord = chordService.getNamedChord(notes);
	    assertEquals( "", namedChord.getPostfix() );
	    assertEquals( Key.C, namedChord.getKey() );
	    
	    notes = guidoService.gmnToMidi("d f a");
		namedChord = chordService.getNamedChord(notes);
		assertEquals( "m", namedChord.getPostfix() );
		assertEquals( Key.D, namedChord.getKey() );

	    notes = guidoService.gmnToMidi("d f a c2");
	    namedChord = chordService.getNamedChord(notes);
	    assertEquals( "m7", namedChord.getPostfix() );
	    assertEquals( Key.D, namedChord.getKey() );

	    notes = guidoService.gmnToMidi("f#2 b& d&3 f a& b");
	    namedChord = chordService.getNamedChord(notes);
	    assertEquals( "maj11", namedChord.getPostfix() );
	    assertEquals( Key.Fis, namedChord.getKey() );
	}
	
	@Test
	public void inversionTest() {
		List<MidiEventPair> notes = guidoService.gmnToMidi("d1 f a");
		NamedChord namedChord = chordService.getNamedChord(notes);
		assertEquals( "m", namedChord.getPostfix() );
		assertEquals( Key.D, namedChord.getKey() );
		assertEquals( 0, namedChord.getInversion() );
	    
	    notes = guidoService.gmnToMidi("f1 a d2");
	    namedChord = chordService.getNamedChord(notes);
	    assertEquals( "m", namedChord.getPostfix() );
	    assertEquals( Key.D, namedChord.getKey() );
	    assertEquals( 1, namedChord.getInversion() );
	    
	    notes = guidoService.gmnToMidi("a1 d2 f");
	    namedChord = chordService.getNamedChord(notes);
	    assertEquals( "m", namedChord.getPostfix() );
	    assertEquals( Key.D, namedChord.getKey() );
	    assertEquals( 2, namedChord.getInversion() );
	}

}
