package de.meisterschueler.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;

import de.meisterschueler.basic.Hand;
import de.meisterschueler.basic.Key;
import de.meisterschueler.basic.MatchingItem;
import de.meisterschueler.basic.MidiEventPair;
import de.meisterschueler.basic.Song;
import de.meisterschueler.songprovider.HanonSongFactory;

public class MatchingHandlerTest  {

	private MatchingHandler matchingHandler = new MatchingHandler();
	private HanonSongFactory hanonSongFactory = new HanonSongFactory();
	private GuidoService guidoService = new GuidoServiceImpl();
	private MatchingItem bestMatchingItem = null;

	private ResultListenerDummy resultServiceDummy = new ResultListenerDummy();

	@Before
	public void init() {
		List<Song> songs = hanonSongFactory.getSongBook().getSongs();
		matchingHandler.setSongs(songs);
		matchingHandler.initMatchingItems();
		matchingHandler.setSignalService(new SignalServiceDummy());
		matchingHandler.setResultListener(resultServiceDummy);
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
		List<MidiEventPair> midiCEvents = guidoService.gmnToMidi("c1 d e f g a b c2 d e f g a b c3 b2 a g f e d c b1 a g f e d");
		List<MidiEventPair> midiGEvents = guidoService.gmnToMidi("g1 a b c2 d e f# g a b c3 d e f# g f# e d c b2 a g f# e d c b1 a");
		
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
	}

	private void proceedMidiEvents(List<MidiEventPair> midiEvents) {
		for (MidiEventPair event : midiEvents) {
			matchingHandler.match(event.getNoteOn());
			matchingHandler.match(event.getNoteOff());
		}
		bestMatchingItem = matchingHandler.getBestMatchingItem();
	}
}
