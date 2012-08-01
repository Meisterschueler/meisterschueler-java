package de.meisterschueler.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import de.meisterschueler.basic.MidiEventPair;
import de.meisterschueler.basic.Rhythm;

public class RhythmServiceTest {
	
	private GuidoService guidoService = new GuidoService();
	private RhythmService rhythmService = new RhythmService();

	@Test
	public void evenTest() {
		List<MidiEventPair> notes = guidoService.gmnToMidi("c0 d e f g a b c1 d e f g a b c2");
		Rhythm rhythm = rhythmService.getRhythm(notes);
		assertEquals( Rhythm.EVEN, rhythm );
	}
	
	@Test
	public void syncopeTest() {
		List<MidiEventPair> notes = guidoService.gmnToMidi("c0/8. d/16 e/8. f/16 g/8. a/16 b/8. c1/16 d/8. e/16 f/8. g/16 a/8. b/16 c2/8.");
		Rhythm rhythm = rhythmService.getRhythm(notes);
		assertEquals( Rhythm.SYNCOPE, rhythm );
	}
	
	@Test
	public void lombardicTest() {
		List<MidiEventPair> notes = guidoService.gmnToMidi("c0/16 d/8. e/16 f/8. g/16 a/8. b/16 c1/8. d/16 e/8. f/16 g/8. a/16 b/8. c2/16");
		Rhythm rhythm = rhythmService.getRhythm(notes);
		assertEquals( Rhythm.LOMBARDIC, rhythm );
	}
}
