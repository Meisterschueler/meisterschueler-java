package de.meisterschueler.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;

import de.meisterschueler.basic.Key;
import de.meisterschueler.basic.MatchingItem;
import de.meisterschueler.basic.MidiEventPair;
import de.meisterschueler.basic.Score;
import de.meisterschueler.basic.Score.Status;
import de.meisterschueler.basic.Song;


public class MatchingServiceTest {

	private GuidoService guidoService = new GuidoService();
	private MatchingService matchingService = new MatchingServiceImpl();
	private MidiService midiService = new MidiService();

	@Test
	public void sequenceTest() {
		String gmnString = "c0 d e _ f g";
		List<MidiEventPair> notes = guidoService.gmnToMidi(gmnString);
		String pitchSequence = matchingService.midiEventsToPitchSequence(notes);
		String intervalSequence = matchingService.midiEventsToIntervalSequence(notes);
		assertEquals( 5, pitchSequence.length() );
		assertEquals( 4, intervalSequence.length() );
		
		List<Score> scores = guidoService.gmnToScores(gmnString);
		pitchSequence = matchingService.scoresToPitchSequence(scores);
		intervalSequence = matchingService.scoresToIntervalSequence(scores);
		assertEquals( 5, pitchSequence.length() );
		assertEquals( 4, intervalSequence.length() );

		gmnString = "{c0,e,g}";
		notes = guidoService.gmnToMidi(gmnString);
		pitchSequence = matchingService.midiEventsToPitchSequence(notes);
		intervalSequence = matchingService.midiEventsToIntervalSequence(notes);
		assertEquals( 3, pitchSequence.length() );
		assertEquals( 2, intervalSequence.length() );
		
		scores = guidoService.gmnToScores(gmnString);
		pitchSequence = matchingService.scoresToPitchSequence(scores);
		intervalSequence = matchingService.scoresToIntervalSequence(scores);
		assertEquals( 3, pitchSequence.length() );
		assertEquals( 2, intervalSequence.length() );
	}
	
	@Test
	public void simpleMatchTest() {
		List<Score> scores = guidoService.gmnToScores("c0 e f g a g f e");
		List<MidiEventPair> notes = guidoService.gmnToMidi("c0 e f g a g f e");

		for( Score score : scores ) {
			assertNull( score.getNote() );
		}

		List<Score> result = matchingService.simpleMatch(scores, notes);

		for( Score score : result ) {
			assertNotNull( score.getNote().getNoteOn() );
			assertNotNull( score.getNote().getNoteOff() );
			assertEquals( score.getNote().getNoteOn().getNoteValue(), score.getPitch());
			assertEquals( score.getNote().getNoteOff().getNoteValue(), score.getPitch());
		}
	}

	@Test
	public void chordMatchTest() {
		List<Score> scores = guidoService.gmnToScores("{c0,e,g}");
		List<MidiEventPair> notes = guidoService.gmnToMidi("c0 e g");

		List<Score> result = matchingService.simpleMatch(scores, notes);

		for (Score score : result)  {
			assertNotNull( score.getNote() );
			assertEquals( score.getNote().getNoteOn().getNoteValue(), score.getPitch());
			assertEquals( score.getNote().getNoteOff().getNoteValue(), score.getPitch());
		} 
	}

	@Test
	public void simpleMatchTest2() {
		List<Score> scores = guidoService.gmnToScores("c0 d e f g/2 g");
		List<MidiEvent> notes = new ArrayList<MidiEvent>();
		notes.add(new NoteOn(   0, 0, 48, 1));
		notes.add(new NoteOn(1000, 1, 50, 3));
		notes.add(new NoteOn(2000, 2, 52, 5));
		notes.add(new NoteOn(3000, 3, 53, 7));
		notes.add(new NoteOn(4000, 4, 55, 9));
		notes.add(new NoteOn(5000, 5, 55, 11));

		List<Score> result = matchingService.simpleMatch(scores, midiService.pairMidiEvents(notes));

		for (int i=0; i<result.size(); i++) {
			NoteOn noteOn = scores.get(i).getNote().getNoteOn();
			assertEquals(i*1000, noteOn.getTick());
			assertEquals(i, noteOn.getChannel());
			assertEquals(scores.get(i).getPitch(), noteOn.getNoteValue());
			assertEquals(i*2+1, noteOn.getVelocity());
		}
	}
	
	@Test
	public void mergeExtraTest() {
		List<Score> scores = guidoService.gmnToScores("c0");
		List<MidiEventPair> notes = guidoService.gmnToMidi("c0 g");
		
		MatchingItem item = new MatchingItem();
		item.setScores(scores);
		item.setNotes(notes);
		
		// Extra is last
		item.setPitchAlignment("me");
		matchingService.updateMerge(item);

		List<Score> result = item.getFlatScores();
		assertEquals( 2, result.size() );
		assertEquals( Status.PLAYED, result.get(0).getStatus() );
		assertEquals( Status.EXTRA, result.get(1).getStatus() );
		
		// Extra is first
		item.setPitchAlignment("em");
		matchingService.updateMerge(item);
		
		result = item.getFlatScores();
		assertEquals( 2, result.size() );
		assertEquals( Status.EXTRA, result.get(0).getStatus() );
		assertEquals( Status.PLAYED, result.get(1).getStatus() );
	}

	@Test
	public void mergeTest() {
		MatchingItem item = new MatchingItem();
		String pitchAlignment = "mwmxmem";
		List<Score> scores = guidoService.gmnToScores("c0 d e f g c1");
		List<MidiEventPair> notes = guidoService.gmnToMidi("c0 d# e g a c1");

		item.setPitchAlignment(pitchAlignment);
		item.setScores(scores);
		item.setNotes(notes);
		
		matchingService.updateMerge(item);

		List<Score> result = item.getFlatScores();
		
		assertEquals( 7, result.size());

		assertEquals( Status.PLAYED, result.get(0).getStatus() );
		assertEquals( 48, result.get(0).getPitch() );
		assertEquals( 48, result.get(0).getNote().getNoteOn().getNoteValue() );

		assertEquals( Status.WRONG, result.get(1).getStatus() );
		assertEquals( 50, result.get(1).getPitch() );
		assertEquals( 51, result.get(1).getNote().getNoteOn().getNoteValue() );

		assertEquals( Status.PLAYED, result.get(2).getStatus() );
		assertEquals( 52, result.get(2).getPitch() );
		assertEquals( 52, result.get(2).getNote().getNoteOn().getNoteValue() );

		assertEquals( Status.MISSED, result.get(3).getStatus() );
		assertEquals( 53, result.get(3).getPitch() );
		assertNull( result.get(3).getNote() );

		assertEquals( Status.PLAYED, result.get(4).getStatus() );
		assertEquals( 55, result.get(4).getPitch() );
		assertEquals( 55, result.get(4).getNote().getNoteOn().getNoteValue() );

		assertEquals( Status.EXTRA, result.get(5).getStatus() );
		assertEquals( 57, result.get(5).getNote().getNoteOn().getNoteValue() );

		assertEquals( Status.PLAYED, result.get(6).getStatus() );
		assertEquals( 60, result.get(6).getPitch() );
		assertEquals( 60, result.get(6).getNote().getNoteOn().getNoteValue() );
	}

	@Test
	public void timingTest() {
		List<MidiEvent> notes = new ArrayList<MidiEvent>();

		// Big space (>50ms)
		MidiEvent me1 = new NoteOn(0, 0, 40, 10);
		MidiEvent me2 = new NoteOn(500, 0, 43, 10);

		// All events with almost the same timestamp (1ms difference) 
		MidiEvent me3 = new NoteOn(1000, 0, 52, 10);
		MidiEvent me4 = new NoteOn(1001, 0, 48, 10);
		MidiEvent me5 = new NoteOn(1002, 0, 55, 10);

		// Again big space
		MidiEvent me6 = new NoteOn(2000, 0, 41, 10);
		MidiEvent me7 = new NoteOn(3000, 0, 42, 10);

		notes.add(me1);
		notes.add(me2);
		notes.add(me3);
		notes.add(me4);
		notes.add(me5);
		notes.add(me6);
		notes.add(me7);

		List<MidiEvent> result = matchingService.orderByChord(notes);

		assertEquals( me1, ((NoteOn)result.get(0)) );
		assertEquals( me2, ((NoteOn)result.get(1)) );
		assertEquals( me4, ((NoteOn)result.get(2)) );
		assertEquals( me3, ((NoteOn)result.get(3)) );
		assertEquals( me5, ((NoteOn)result.get(4)) );
		assertEquals( me6, ((NoteOn)result.get(5)) );
		assertEquals( me7, ((NoteOn)result.get(6)) );
	}

	@Test
	public void matchingTest() {
		MatchingItem item = new MatchingItem();
		item.setScorePitchSequence("ABCDE");
		item.setNotePitchSequence("ABCDE");

		matchingService.matchPitch(item);
		assertEquals( "mmmmm", item.getPitchAlignment() );

		matchingService.updateQuality(item);
		assertEquals( 1.0, item.getQuality(), 0.01 );

		item.setNotePitchSequence("AB.DEx");

		matchingService.matchPitch(item);
		assertEquals( "mmwmme", item.getPitchAlignment() );

		matchingService.updateQuality(item);
		assertTrue( 0.9 > item.getQuality() );
	}

	@Test
	public void matchingPrunnedTest() {
		MatchingItem item = new MatchingItem();
		item.setScorePitchSequence("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		item.setNotePitchSequence("ABCDEFGHIJKLM");

		matchingService.matchPitch(item);
		assertEquals( false, item.isPrunning() );

		matchingService.updatePrunning(item);
		assertEquals( true, item.isPrunning() );

		item.setNotePitchSequence("ABCDEFGHIJKLMNOPQRSTUVW");

		matchingService.matchPitchPrunned(item);
		String prunnedAlignment = item.getPitchAlignment();

		matchingService.matchPitch(item);
		String normalAlignment = item.getPitchAlignment();

		assertEquals( normalAlignment, prunnedAlignment );
	}

	@Test
	public void updateTranspositionTest() {
		MatchingItem item = new MatchingItem();
		List<Score> scores = guidoService.gmnToScores("c0 d c e a g f e");
		item.setScorePitchSequence(matchingService.scoresToPitchSequence(scores));
		item.setScoreIntervalSequence(matchingService.scoresToIntervalSequence(scores));

		List<MidiEventPair> notes = guidoService.gmnToMidi("d0 e d f# b a g f#");
		item.setNotePitchSequence(matchingService.midiEventsToPitchSequence(notes));
		item.setNoteIntervalSequence(matchingService.midiEventsToIntervalSequence(notes));

		matchingService.matchPitch(item);
		assertFalse( item.getPitchAlignment().equals("mmmmmmmm") );

		matchingService.matchInterval(item);
		assertEquals( "mmmmmmm", item.getIntervalAlignment() );

		matchingService.updateTransposition(item);
		assertEquals(2, item.getTransposition());

		matchingService.matchPitch(item);
		assertEquals( "mmmmmmmm", item.getPitchAlignment() );

		matchingService.updateTransposition(item);
		assertEquals(2, item.getTransposition());
	}
	
	@Test
	public void updateKeyTest() {
		MatchingItem item = new MatchingItem();

		Song song = new Song();
		song.setKey(Key.C);
		item.setSong(song);

		item.setTransposition(3);
		matchingService.updateKey(item);

		assertEquals( Key.Es, item.getKey() );
	}

	@Test
	public void updateQualityTest() {
		MatchingItem item = new MatchingItem();
		item.setPitchAlignment("mmmmm");
		matchingService.updateQuality(item);
		assertEquals( 1.0, item.getQuality(), 0.01 );

		item.setPitchAlignment("xxxxx");
		matchingService.updateQuality(item);
		assertEquals( 0.0, item.getQuality(), 0.01 );
	}

	@Test
	public void updateFinishedTest() {
		MatchingItem item = new MatchingItem();
		item.setPitchAlignment("mmmx");
		item.setPressedSequence("....");
		matchingService.updateFinished(item);
		assertFalse( item.isFinished() );

		item.setPitchAlignment("mmmmmm");
		item.setPressedSequence("......");
		matchingService.updateFinished(item);
		assertTrue( item.isFinished() );
		item.setPressedSequence("...X..");
		matchingService.updateFinished(item);
		assertFalse( item.isFinished() );

		item.setPitchAlignment("mmmme");
		item.setPressedSequence(".....");
		matchingService.updateFinished(item);
		assertTrue( item.isFinished() );
		item.setPressedSequence("....X");
		matchingService.updateFinished(item);
		assertTrue( item.isFinished() );
		item.setPressedSequence("...X.");
		matchingService.updateFinished(item);
		assertFalse( item.isFinished() );
		
		item.setPitchAlignment("meme");
		item.setPressedSequence("....");
		matchingService.updateFinished(item);
		assertTrue( item.isFinished() );
		item.setPressedSequence(".X..");
		matchingService.updateFinished(item);
		assertFalse( item.isFinished() );
	}

	

	@Test
	public void mergeUnfinishedNotesTest() {
		MatchingItem item = new MatchingItem();
		List<Score> scores = guidoService.gmnToScores("c0 e g");
		List<MidiEventPair> notes = guidoService.gmnToMidi("c0 e g a");

		NoteOff noteOffG = notes.get(2).getNoteOff();
		notes.get(2).setNoteOff(null);
		
		NoteOff noteOffA = notes.get(3).getNoteOff();
		notes.get(3).setNoteOff(null);

		item.setScores(scores);
		item.setFlatScores(scores);
		item.setNotes(notes);
		item.setPitchAlignment("mmme");
		matchingService.updateMerge(item);
		
		List<Score> result = item.getFlatScores();
		
		assertEquals( 4, result.size() );

		assertEquals( Status.PLAYED, result.get(0).getStatus() );
		assertNotNull(result.get(0).getNote().getNoteOn() );
		assertNotNull(result.get(0).getNote().getNoteOff() );
		
		assertEquals( Status.PLAYED, result.get(1).getStatus() );
		assertNotNull(result.get(1).getNote().getNoteOn() );
		assertNotNull(result.get(1).getNote().getNoteOff() );
		
		assertEquals( Status.PLAYED, result.get(2).getStatus() );
		assertNotNull(result.get(2).getNote().getNoteOn() );
		assertNull(result.get(2).getNote().getNoteOff() );
		
		assertEquals( Status.EXTRA, result.get(3).getStatus() );
		assertNotNull(result.get(3).getNote().getNoteOn() );
		assertNull(result.get(3).getNote().getNoteOff() );
	}
	
	@Test
	public void cutMatchingMidiEventsTest() {
		
		List<MidiEventPair> events = new ArrayList<MidiEventPair>();
		events.add(new MidiEventPair(new NoteOn(30000, 0, 48, 50), new NoteOff(31000, 0, 48, 0)));
		events.add(new MidiEventPair(new NoteOn(32000, 0, 52, 50), new NoteOff(33000, 0, 52, 0)));
		events.add(new MidiEventPair(new NoteOn(34000, 0, 55, 50), new NoteOff(35000, 0, 55, 0)));		
		
		List<Score> scores = guidoService.gmnToScores("c0 e");
		
		MatchingItem item = new MatchingItem();
		item.setNotes(events);
		item.setScores(scores);
		item.setPitchAlignment("mme");
		
		List<MidiEventPair> rest = matchingService.cutMatchingMidiEvents(item);
		assertEquals( 1, rest.size() );
		
		List<MidiEventPair> cuttedNotes = item.getNotes();
		assertEquals( 2, cuttedNotes.size() );
		assertEquals( 0, cuttedNotes.get(0).getNoteOn().getTick() );
		assertEquals( 1000, cuttedNotes.get(0).getNoteOff().getTick() );
		assertEquals( 2000, cuttedNotes.get(1).getNoteOn().getTick() );
		assertEquals( 3000, cuttedNotes.get(1).getNoteOff().getTick() );
	}
}
