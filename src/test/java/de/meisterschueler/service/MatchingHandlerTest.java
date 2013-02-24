package de.meisterschueler.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;

import de.meisterschueler.basic.Hand;
import de.meisterschueler.basic.Key;
import de.meisterschueler.basic.MatchingItem;
import de.meisterschueler.basic.MidiEventPair;

public class MatchingHandlerTest  {

	private MatchingHandler matchingHandler = new MatchingHandler();
	private GuidoService guidoService = new GuidoService();
	private SongHandler songHandler = new SongHandler();
	private MatchingItem bestMatchingItem = null;

	private ResultListenerDummy resultServiceDummy = new ResultListenerDummy();

	@Before
	public void init() {
		matchingHandler.setSongs(songHandler.getSongs());
		matchingHandler.initMatchingItems();
		matchingHandler.setSignalService(new SignalServiceDummy());
		matchingHandler.setResultListener(resultServiceDummy);
	}

	@Test
	public void hanonNo1LeftCompleteTest() {
		String gmn = "c0 e f g a g f e d f g a b a g f e g a b c1 b0 a g f a b c1 d c b0 a g b c1 d e d c b0 a c1 d e f e d c b0 d1 e f g f e d c1 e f g a g f e d f g a b a g f e g a b c2 b1 a g f a b c2 d c b1 a g b c2 d e d c b1 a c2 d e f e d c b1 d2 e f g f e d ";
		gmn += "g2 e d c b1 c2 d e f d c b1 a b c2 d e c b1 a g a b c2 d b1 a g f g a b c2 a1 g f e f g a b g f e d e f g a f e d c d e f g e d c b0 c1 d e f d c b0 a b c1 d e c b0 a g a b c1 d b0 a g f g a b c1 a0 g f e f g a b g f e d e f g a f e d c d e f g e d c b-1 c0 d e g";
		List<MidiEventPair> midiEvents = guidoService.gmnToMidi(gmn);
		proceedMidiEvents(midiEvents);
		assertNotNull(bestMatchingItem);
		assertEquals( "No. 1", bestMatchingItem.getSong().getName() );
		assertEquals( 0, bestMatchingItem.getTransposition() );
		assertEquals( Key.C, bestMatchingItem.getKey() );
		assertEquals( Hand.LEFT, bestMatchingItem.getHand() );
	}
	
	@Test
	public void hanonNo1LeftTest() {
		List<MidiEventPair> midiEvents = guidoService.gmnToMidi("b0 d#1 e f# g# f# e d# c# e f# g# a# g# f# e");
		proceedMidiEvents(midiEvents);
		assertNotNull(bestMatchingItem);
		assertEquals( "No. 1", bestMatchingItem.getSong().getName() );
		assertEquals( 11, bestMatchingItem.getTransposition() );
		assertEquals( Key.H, bestMatchingItem.getKey() );
		assertEquals( Hand.LEFT, bestMatchingItem.getHand() );
	}

	@Test
	public void hanonNo1RightTest() {
		List<MidiEventPair> midiEvents = guidoService.gmnToMidi("c1 e f g a g f e d f g a b a g f e g a b c2");
		proceedMidiEvents(midiEvents);
		assertNotNull(bestMatchingItem);
		assertEquals( "No. 1", bestMatchingItem.getSong().getName() );
		assertEquals( 0, bestMatchingItem.getTransposition() );
		assertEquals( Key.C, bestMatchingItem.getKey() );
		assertEquals( Hand.RIGHT, bestMatchingItem.getHand() );
	}
	
	@Test
	public void hanonNo1BothTest() {
		List<MidiEventPair> midiEvents = guidoService.gmnToMidi("{c0,c1} {e1,e0} {f0,f1} {g0,g1} {a0,a1} {g1,g0} {f0,f1} {e0,e1}");
		proceedMidiEvents(midiEvents);
		assertNotNull(bestMatchingItem);
		assertEquals( "No. 1", bestMatchingItem.getSong().getName() );
		assertEquals( 0, bestMatchingItem.getTransposition() );
		assertEquals( Key.C, bestMatchingItem.getKey() );
		assertEquals( Hand.BOTH, bestMatchingItem.getHand() );
	}

	@Test
	public void hanonNo4Test() {
		List<MidiEventPair> midiEvents = guidoService.gmnToMidi("d0 e d f# b a g f# e f# e g c#1 b0 a g");
		proceedMidiEvents(midiEvents);
		assertNotNull(bestMatchingItem);
		assertEquals( "No. 4", bestMatchingItem.getSong().getName() );
		assertEquals( 2, bestMatchingItem.getTransposition() );
		assertEquals( Key.D, bestMatchingItem.getKey() );
		assertEquals( Hand.LEFT, bestMatchingItem.getHand() );
	}

	@Test
	public void hanonNo26Test() {
		List<MidiEventPair> midiEvents = guidoService.gmnToMidi("e3 f g e d e f d c d e c a g a g f g a f e f g e d e f d b a b a g a b g f g a f e f g e c4 b3 c4 b3 a b c4 a3 g a b g f g a f d4 c d c b3 c4 d b3 a b c4 a3 g a b g e4 d e d c d e c b3 c4 d b3 a b c4 a3 f4 e f e d e f d c d e c");
		proceedMidiEvents(midiEvents);
		assertNotNull(bestMatchingItem);
		assertEquals( "No. 26", bestMatchingItem.getSong().getName() );
		assertTrue( bestMatchingItem.getPitchAlignment().startsWith("mmmmmmmmmm") );
		assertTrue( bestMatchingItem.getIntervalAlignment().startsWith("mmmmm") );
	}

	@Test
	public void statusTest() {
		assertEquals( MatchingHandler.Status.INIT, matchingHandler.getStatus() );
		matchingHandler.match(new NoteOn(0, 0, 42, 50));
		assertEquals( MatchingHandler.Status.PLAYING, matchingHandler.getStatus() );
		matchingHandler.match(new NoteOff(100, 0, 42, 50));
		assertEquals( MatchingHandler.Status.WAITING, matchingHandler.getStatus() );
	}

	@Test
	public void songFinishedTest() {
		List<MidiEventPair> midiCEvents = guidoService.gmnToMidi("c1 d e f g a b c2 d e f g a b c3 b2 a g f e d c b1 a g f e d");
		List<MidiEventPair> midiCisEvents = guidoService.gmnToMidi("c#1 d# e# f# g# a# b# c#2 d# e# f# g# a# b# c#3 b#2 a# g# f# e# d# c# b#1 a# g# f# e# d#");

		// Play C scale
		int eventSize = midiCEvents.size();
		proceedMidiEvents(midiCEvents.subList(0, eventSize-2));
		assertEquals( "No. 0", matchingHandler.getBestMatchingItem().getSong().getName() );
		assertNull( resultServiceDummy.getLastResult() );
		matchingHandler.match(midiCEvents.get(eventSize-2).getNoteOn());
		assertNull( resultServiceDummy.getLastResult() );
		matchingHandler.match(midiCEvents.get(eventSize-2).getNoteOff());
		assertNull( resultServiceDummy.getLastResult() );
		matchingHandler.match(midiCEvents.get(eventSize-1).getNoteOn());
		assertNull( resultServiceDummy.getLastResult() );
		matchingHandler.match(midiCEvents.get(eventSize-1).getNoteOff());
		assertNotNull( resultServiceDummy.getLastResult() );
		assertEquals( "No. 0", resultServiceDummy.getLastResult().getSong().getName() );
		assertEquals( Key.C, resultServiceDummy.getLastResult().getKey() );

		// Play C# scale
		eventSize = midiCisEvents.size();
		proceedMidiEvents(midiCisEvents.subList(0, eventSize-2));
		assertEquals( "No. 0", matchingHandler.getBestMatchingItem().getSong().getName() );
		assertEquals( Key.C, resultServiceDummy.getLastResult().getKey() );
		matchingHandler.match(midiCisEvents.get(eventSize-2).getNoteOn());
		assertEquals( Key.C, resultServiceDummy.getLastResult().getKey() );
		matchingHandler.match(midiCisEvents.get(eventSize-2).getNoteOff());
		assertEquals( Key.C, resultServiceDummy.getLastResult().getKey() );
		matchingHandler.match(midiCisEvents.get(eventSize-1).getNoteOn());
		assertEquals( Key.C, resultServiceDummy.getLastResult().getKey() );
		matchingHandler.match(midiCisEvents.get(eventSize-1).getNoteOff());
		assertEquals( Key.Des, resultServiceDummy.getLastResult().getKey() );
	}

	@Test
	public void songFinishedLegatoTest() {
		String CScale = "c1 d e f g a b c2 d e f g a b c3 b2 a g f e d c b1 a g f e d";
		String GScale = "g1 a b c2 d e f# g a b c3 d e f# g f# e d c b2 a g f# e d c b1 a";

		List<MidiEventPair> midiEventPairs = guidoService.gmnToMidi(CScale + " " + GScale); 
		List<MidiEventPair> midiCEvents = new ArrayList<MidiEventPair>(midiEventPairs.subList(0, 28));
		List<MidiEventPair> midiGEvents = new ArrayList<MidiEventPair>(midiEventPairs.subList(28, 56));

		// Play C scale
		int eventSize = midiCEvents.size();
		proceedMidiEvents(midiCEvents.subList(0, eventSize-2));
		assertEquals( "No. 0", matchingHandler.getBestMatchingItem().getSong().getName() );
		assertNull( resultServiceDummy.getLastResult() );
		matchingHandler.match(midiCEvents.get(eventSize-2).getNoteOn());
		matchingHandler.match(midiCEvents.get(eventSize-2).getNoteOff());
		assertNull( resultServiceDummy.getLastResult() );
		matchingHandler.match(midiCEvents.get(eventSize-1).getNoteOn());
		assertNull( resultServiceDummy.getLastResult() );
		matchingHandler.match(midiGEvents.get(0).getNoteOn());	// This NoteOn is from G scale !!!			
		assertNull( resultServiceDummy.getLastResult() );
		matchingHandler.match(midiCEvents.get(eventSize-1).getNoteOff());	// Here we finish C scale
		assertNotNull( resultServiceDummy.getLastResult() );
		assertEquals( "No. 0", resultServiceDummy.getLastResult().getSong().getName() );
		assertEquals( Key.C, resultServiceDummy.getLastResult().getKey() );
		assertEquals( 28, resultServiceDummy.getLastResult().getScores().size() );

		// Play G scale
		eventSize = midiGEvents.size();
		matchingHandler.match(midiGEvents.get(0).getNoteOff()); // NoteOn already played!!!
		proceedMidiEvents(midiGEvents.subList(1, eventSize-2)); 
		assertEquals( "No. 0", matchingHandler.getBestMatchingItem().getSong().getName() );
		assertEquals( Key.C, resultServiceDummy.getLastResult().getKey() );
		matchingHandler.match(midiGEvents.get(eventSize-2).getNoteOn());
		matchingHandler.match(midiGEvents.get(eventSize-2).getNoteOff());
		assertEquals( Key.C, resultServiceDummy.getLastResult().getKey() );
		matchingHandler.match(midiGEvents.get(eventSize-1).getNoteOn());
		matchingHandler.match(midiGEvents.get(eventSize-1).getNoteOff());
		assertEquals( Key.G, resultServiceDummy.getLastResult().getKey() );
		assertEquals( 28, resultServiceDummy.getLastResult().getScores().size() );
	}

	@Test
	public void polyphoneTest() {
		// Play No. 50
		List<MidiEventPair> midiEvents = new ArrayList<MidiEventPair>();
		// Thirds, all lower notes played first
		midiEvents.add(new MidiEventPair(new NoteOn(0, 0, 48, 30), new NoteOff(2, 0, 48, 30)));
		midiEvents.add(new MidiEventPair(new NoteOn(1, 0, 52, 30), new NoteOff(3, 0, 52, 30)));

		midiEvents.add(new MidiEventPair(new NoteOn(100, 0, 50, 30), new NoteOff(102, 0, 50, 30)));
		midiEvents.add(new MidiEventPair(new NoteOn(101, 0, 53, 30), new NoteOff(103, 0, 53, 30)));

		midiEvents.add(new MidiEventPair(new NoteOn(200, 0, 52, 30), new NoteOff(202, 0, 52, 30)));
		midiEvents.add(new MidiEventPair(new NoteOn(201, 0, 55, 30), new NoteOff(203, 0, 55, 30)));

		midiEvents.add(new MidiEventPair(new NoteOn(300, 0, 50, 30), new NoteOff(302, 0, 50, 30)));
		midiEvents.add(new MidiEventPair(new NoteOn(301, 0, 53, 30), new NoteOff(303, 0, 53, 30)));

		// but here: lower note second
		midiEvents.add(new MidiEventPair(new NoteOn(400, 0, 52, 30), new NoteOff(402, 0, 52, 30)));
		midiEvents.add(new MidiEventPair(new NoteOn(401, 0, 48, 30), new NoteOff(403, 0, 48, 30)));

		midiEvents.add(new MidiEventPair(new NoteOn(500, 0, 50, 30), new NoteOff(502, 0, 50, 30)));
		midiEvents.add(new MidiEventPair(new NoteOn(501, 0, 53, 30), new NoteOff(503, 0, 53, 30)));

		// and here: lower note second		
		midiEvents.add(new MidiEventPair(new NoteOn(600, 0, 55, 30), new NoteOff(602, 0, 55, 30)));
		midiEvents.add(new MidiEventPair(new NoteOn(601, 0, 52, 30), new NoteOff(603, 0, 52, 30)));

		midiEvents.add(new MidiEventPair(new NoteOn(700, 0, 50, 30), new NoteOff(702, 0, 50, 30)));
		midiEvents.add(new MidiEventPair(new NoteOn(701, 0, 53, 30), new NoteOff(703, 0, 53, 30)));

		proceedMidiEvents(midiEvents);

		assertEquals( "No. 50", matchingHandler.getBestMatchingItem().getSong().getName() );
		assertEquals( "mmmmmmmmmmmmmmmm", matchingHandler.getBestMatchingItem().getPitchAlignment().substring(0, 16) );
	}

	@Test
	public void bachInventio13Test() {
		List<MidiEventPair> midiEvents = guidoService.gmnToMidi("e1 a c2 b1 e b d2 c e g#1 e2");
		proceedMidiEvents(midiEvents);
		assertNotNull(bestMatchingItem);
		assertEquals( "Inventio 13", bestMatchingItem.getSong().getName() );
		assertTrue( bestMatchingItem.getPitchAlignment().startsWith("mmmmmmmmmmm") );
		assertEquals( Hand.RIGHT, bestMatchingItem.getHand() );
	}
	
	@Test
	public void bachInventio13BothHandsTest() {
		List<MidiEventPair> midiEvents = guidoService.gmnToMidi("a-0 e1 {a0,a1} c2 b1 e {a&0,b1} d2 {a0,c2} e0 {a0,e2} c1 {b0,g#1} e0 {a0,e2} c1");
		proceedMidiEvents(midiEvents);
		assertNotNull(bestMatchingItem);
		assertEquals( "Inventio 13", bestMatchingItem.getSong().getName() );
		assertTrue( bestMatchingItem.getPitchAlignment().startsWith("mmmmmmmmmmm") );
		assertEquals( Hand.BOTH, bestMatchingItem.getHand() );
	}

	private void proceedMidiEvents(List<MidiEventPair> midiEvents) {
		for (MidiEventPair event : midiEvents) {
			matchingHandler.match(event.getNoteOn());
			matchingHandler.match(event.getNoteOff());
		}
		bestMatchingItem = matchingHandler.getBestMatchingItem();
	}
}