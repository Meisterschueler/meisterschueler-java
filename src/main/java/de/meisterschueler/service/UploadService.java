package de.meisterschueler.service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.leff.midi.MidiFile;

public class UploadService {

	private static final String GAE_LOCAL = "http://127.0.0.1:8888/upload";
	private static final String GAE_REMOTE = "http://performanceworm.appspot.com/meisterschueler_gae";

	public boolean sendResult(MidiFile midiFile) {
		boolean successful = false;
		
		try {
			// Create tmp file
			File file = File.createTempFile("temp", ".mid");
			midiFile.writeToFile(file);

			// Put content in byte array
			byte [] fileData = new byte[(int)file.length()];
			DataInputStream dis = new DataInputStream(new FileInputStream(file));
			dis.readFully(fileData);
			dis.close();	

			// Send data
			URL url = new URL(GAE_LOCAL);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);

			OutputStream wr = conn.getOutputStream();
			wr.write(fileData);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				successful = line.equals("Success");
			}
			wr.close();
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return successful;
	}
}
