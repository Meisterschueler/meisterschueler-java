package de.meisterschueler.service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import com.leff.midi.MidiFile;

import de.meisterschueler.basic.Finger;
import de.meisterschueler.basic.MatchingItem;
import de.meisterschueler.basic.MidiEventPair;
import de.meisterschueler.basic.SongAnalysis;

public class ResultListenerLocal implements ResultListener {

	private static final String GAE_LOCAL = "http://127.0.0.1:8888/meisterschueler_gae";
	private static final String GAE_REMOTE = "http://performanceworm.appspot.com/meisterschueler_gae";

	private StatisticsService statisticsService = new StatisticsServiceImpl();
	private MidiService midiService = new MidiService();

	@Override
	public void gotResult(final MatchingItem item) {
		new Thread() {
			@Override
			public void run() {
				printResult(item);
				sendResult(item);
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
			System.out.println(finger.toString() + ": " + songAnalysis.getVelocity().getMean() + " / " + songAnalysis.getVelocity().getVariance());
		}
	}

	private void sendResult(MatchingItem item) {
		try {
			// Create tmp midi file
			List<MidiEventPair> midi = item.getNotes(); 
			MidiFile midiFile = midiService.createMidiFile(midi);
			File file = File.createTempFile("temp", ".mid");
			midiFile.writeToFile(file);

			// Put content in byte array
			byte [] fileData = new byte[(int)file.length()];
			DataInputStream dis = new DataInputStream(new FileInputStream(file));
			dis.readFully(fileData);
			dis.close();	

			// Send data
			URL url = new URL(GAE_REMOTE);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);

			OutputStream wr = conn.getOutputStream();
			wr.write(fileData);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				// Process line...
			}
			wr.close();
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
