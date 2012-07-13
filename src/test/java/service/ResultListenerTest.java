package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import basic.MidiEventPair;

import com.leff.midi.MidiFile;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;


public class ResultListenerTest {

	private ResultListener resultListener = new ResultListenerLocal();
	private GuidoService guidoService = new GuidoServiceImpl();
	
	@Test
	public void saveMidiEventsTest() {
		String c0 = "c0 d e f g f e d";
		List<MidiEventPair> midiEvents = guidoService.gmnToMidi(StringUtils.repeat(c0, 100));
		
		midiEvents.clear();
		NoteOn noteOn = new NoteOn(System.currentTimeMillis(), 0, 64, 50);
		NoteOff noteOff = new NoteOff(System.currentTimeMillis()+120, 0, 64, 50); 
		midiEvents.add(new MidiEventPair(noteOn, noteOff));
//		resultService.saveMidiEvents(midiEvents, "example.mid");
		
		try {
			File file = new File("example.mid");
			MidiFile midiFile = new MidiFile(file);
			midiFile.getTrackCount();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
