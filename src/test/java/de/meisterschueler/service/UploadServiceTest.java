package de.meisterschueler.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;



import com.leff.midi.MidiFile;

import de.meisterschueler.basic.MidiEventPair;
import de.meisterschueler.service.GuidoService;
import de.meisterschueler.service.MidiService;
import de.meisterschueler.service.UploadService;

public class UploadServiceTest {

	MidiService midiService = new MidiService();
	GuidoService guidoService = new GuidoService();
	UploadService uploadService = new UploadService();
	
	@Test
	public void sendResultTest() {
			// Construct data
			String c0 = "c0 d e f g a b c1 d e f g a b c2 b1 a g f e d c b0 a g f e d";
			List<MidiEventPair> midiEvents = guidoService.gmnToMidi(c0);
			midiService.saveMidiEvents(midiEvents, "example.mid");

			MidiFile midiFile = null;
			try {
				midiFile = new MidiFile(new File("example.mid"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			assertNotNull( midiFile );
			boolean success = uploadService.sendResult(midiFile);
			assertTrue( success );
	}
}
