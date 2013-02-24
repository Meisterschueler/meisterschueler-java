package de.meisterschueler.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.meisterschueler.basic.MidiEventPair;
import de.meisterschueler.basic.Song;
import de.meisterschueler.songprovider.BachSongFactory;
import de.meisterschueler.songprovider.HanonSongFactory;

public class MatchingHandlerConcurrencyTest {

	private MatchingHandler matchingHandler = new MatchingHandler();
	private HanonSongFactory hanonSongFactory = new HanonSongFactory();
	private BachSongFactory bachSongFactory = new BachSongFactory();
	private GuidoService guidoService = new GuidoService();

	private ResultListenerDummy resultServiceDummy = new ResultListenerDummy();

	@Before
	public void init() {
		List<Song> songs = hanonSongFactory.getSongBook().getSongs();
		songs.addAll( bachSongFactory.getSongBook().getSongs() );
		matchingHandler.setSongs(songs);
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
		
		Thread resetThread = new Thread() {
			@Override
			public void run() {
				matchingHandler.resetMidiEvents();
			}
		};

		matcherThread.start();
		initThread.start();
		resetThread.start();

		try {
			matcherThread.join();
			initThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
