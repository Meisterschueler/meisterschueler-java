package de.meisterschueler.service;

import java.util.List;
import java.util.Map;


import com.leff.midi.MidiFile;

import de.meisterschueler.basic.Finger;
import de.meisterschueler.basic.MatchingItem;
import de.meisterschueler.basic.MidiEventPair;
import de.meisterschueler.basic.SongAnalysis;


public class ResultListenerUpload implements ResultListener {

	private StatisticsService statisticsService = new StatisticsService();
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

		Map<Finger, SongAnalysis> result = statisticsService.retrieveAnalysis(item.getScores());
		for (Finger finger : result.keySet()) {
			SongAnalysis songAnalysis = result.get(finger);
			System.out.println(finger.toString() + ": " + songAnalysis.getVelocity().getMean() + " / " + songAnalysis.getVelocity().getStandardDeviation());
		}
	}
}
