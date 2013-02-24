package de.meisterschueler.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.meisterschueler.basic.MidiEventPair;

public class MatchingHandlerConcurrencyTest {

	private MatchingHandler matchingHandler = new MatchingHandler();
	private SongHandler songHandler = new SongHandler();
	private GuidoService guidoService = new GuidoService();

	private ResultListenerDummy resultServiceDummy = new ResultListenerDummy();

	@Before
	public void init() {
		matchingHandler.setSongs(songHandler.getSongs());
		matchingHandler.initMatchingItems();
		matchingHandler.setSignalService(new SignalServiceDummy());
		matchingHandler.setResultListener(resultServiceDummy);
	}

	@Test
	public void threadSaveTest() {
		Thread matcherThread = new Thread() {
			@Override
			public void run() {
				List<MidiEventPair> midiEventPairs = guidoService.gmnToMidi("c0 e f g a g f e");
				for (MidiEventPair pair : midiEventPairs) {
					matchingHandler.match(pair.getNoteOn());
					matchingHandler.match(pair.getNoteOff());
				}
			}
		};

		Thread initThread = new Thread() {
			@Override
			public void run() {
				matchingHandler.initMatchingItems();
			}
		};

		matcherThread.start();
		System.out.println("running");
		initThread.start();

		try {
			matcherThread.join();
			initThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
