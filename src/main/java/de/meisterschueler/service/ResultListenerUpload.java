package de.meisterschueler.service;

import java.util.List;

import com.leff.midi.MidiFile;

import de.meisterschueler.basic.MatchingItem;
import de.meisterschueler.basic.MidiEventPair;


public class ResultListenerUpload implements ResultListener {

	private MidiService midiService = new MidiService();
	private UploadService uploadService = new UploadService();

	@Override
	public void gotResult(final MatchingItem item) {
		new Thread() {
			@Override
			public void run() {
				printResult(item);
				
				List<MidiEventPair> midi = item.getNotes(); 
				MidiFile midiFile = midiService.createMidiFile(midi);
				uploadService.sendResult(midiFile);
			}
		}.start();
	}

	private void printResult(MatchingItem item) {
		String filename = item.getSong().getName() + " (" + item.getKey().toString() + ")";
		System.out.println("Finished: " + filename);
		midiService.saveMidiEvents(item.getNotes(), filename + System.currentTimeMillis() + ".mid");
	}
}
