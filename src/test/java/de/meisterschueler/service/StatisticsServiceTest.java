package de.meisterschueler.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;

import de.meisterschueler.basic.Score;
import de.meisterschueler.basic.StatisticValue;
import de.meisterschueler.service.GuidoService;
import de.meisterschueler.service.MatchingService;
import de.meisterschueler.service.MatchingServiceImpl;
import de.meisterschueler.service.MidiService;
import de.meisterschueler.service.StatisticsService;

public class StatisticsServiceTest {

	private StatisticsService statisticsService = new StatisticsService();
	private GuidoService guidoService = new GuidoService();
	private MatchingService matchingService = new MatchingServiceImpl();
	private MidiService midiService = new MidiService();

	@Test
	public void durationTest() {
		List<Score> scores = guidoService.gmnToScores("c0 d e f g/2 g");
		List<MidiEvent> notes = new ArrayList<MidiEvent>();

		notes.add(new NoteOn(    0, 0, 48, 10));
		notes.add(new NoteOff( 900, 0, 48, 10));
		notes.add(new NoteOn( 1000, 0, 50, 10));
		notes.add(new NoteOff(1200, 0, 50, 10));
		notes.add(new NoteOn( 2000, 0, 52, 10));
		notes.add(new NoteOff(3000, 0, 52, 10));
		notes.add(new NoteOn( 3000, 0, 53, 10));
		notes.add(new NoteOn( 4000, 0, 55, 10));
		notes.add(new NoteOff(4100, 0, 53, 10));
		notes.add(new NoteOff(5000, 0, 55, 10));
		notes.add(new NoteOn( 6000, 0, 55, 10));
		notes.add(new NoteOff(7000, 0, 55, 10));

		List<Score> result = matchingService.simpleMatch(scores, midiService.pairMidiEvents(notes));
		result = statisticsService.calcDuration(result);
		
		assertEquals( 900.0, result.get(0).retrieveAdditional().getDuration(), 0.1);
		assertEquals( 200.0, result.get(1).retrieveAdditional().getDuration(), 0.1);
		assertEquals(1000.0, result.get(2).retrieveAdditional().getDuration(), 0.1);
		assertEquals(1100.0, result.get(3).retrieveAdditional().getDuration(), 0.1);
		assertEquals(1000.0, result.get(4).retrieveAdditional().getDuration(), 0.1);
		assertEquals(1000.0, result.get(5).retrieveAdditional().getDuration(), 0.1);
	}
	
	@Test
	public void speedTest() {
		List<Score> scores = guidoService.gmnToScores("c0 d e f g/2 g");
		List<MidiEvent> notes = new ArrayList<MidiEvent>();

		notes.add(new NoteOn(   0, 0, 48, 10));
		notes.add(new NoteOn(1000, 0, 50, 10));
		notes.add(new NoteOn(2000, 0, 52, 10));
		notes.add(new NoteOn(3000, 0, 53, 10));
		notes.add(new NoteOn(4000, 0, 55, 10));
		notes.add(new NoteOn(6000, 0, 55, 10));

		List<Score> result = matchingService.simpleMatch(scores, midiService.pairMidiEvents(notes));
		result = statisticsService.calcSpeed(result);

		assertEquals(60.0, result.get(0).retrieveAdditional().getSpeed(), 0.1);
		assertEquals(60.0, result.get(1).retrieveAdditional().getSpeed(), 0.1);
		assertEquals(60.0, result.get(2).retrieveAdditional().getSpeed(), 0.1);
		assertEquals(60.0, result.get(3).retrieveAdditional().getSpeed(), 0.1);
		assertEquals(60.0, result.get(4).retrieveAdditional().getSpeed(), 0.1);
		assertEquals(60.0, result.get(5).retrieveAdditional().getSpeed(), 0.1);
	}
	
	@Test
	public void gapTest() {
		List<Score> scores = guidoService.gmnToScores("c0 d e f g/2 g");
		List<MidiEvent> notes = new ArrayList<MidiEvent>();

		notes.add(new NoteOn(    0, 0, 48, 10));
		notes.add(new NoteOff( 900, 0, 48, 10));
		notes.add(new NoteOn( 1000, 0, 50, 10));
		notes.add(new NoteOff(1200, 0, 50, 10));
		notes.add(new NoteOn( 2000, 0, 52, 10));
		notes.add(new NoteOff(3000, 0, 52, 10));
		notes.add(new NoteOn( 3000, 0, 53, 10));
		notes.add(new NoteOn( 4000, 0, 55, 10));
		notes.add(new NoteOff(4100, 0, 53, 10));
		notes.add(new NoteOff(5000, 0, 55, 10));
		notes.add(new NoteOn( 6000, 0, 55, 10));
		notes.add(new NoteOff(7000, 0, 55, 10));

		List<Score> result = matchingService.simpleMatch(scores, midiService.pairMidiEvents(notes));
		result = statisticsService.calcGap(result);
		
		assertEquals( 100.0, result.get(0).retrieveAdditional().getGap(), 0.1);
		assertEquals( 800.0, result.get(1).retrieveAdditional().getGap(), 0.1);
		assertEquals(   0.0, result.get(2).retrieveAdditional().getGap(), 0.1);
		assertEquals(-100.0, result.get(3).retrieveAdditional().getGap(), 0.1);
		assertEquals(1000.0, result.get(4).retrieveAdditional().getGap(), 0.1);
		assertEquals(   0.0, result.get(5).retrieveAdditional().getGap(), 0.1);
	}
	
	@Test
	public void offsetTest() {
		List<Score> scores = guidoService.gmnToScores("{c0,e,g}");
		List<MidiEvent> notes = new ArrayList<MidiEvent>();

		notes.add(new NoteOn(  3, 0, 48, 10));
		notes.add(new NoteOn( 10, 0, 52, 10));
		notes.add(new NoteOn( 15, 0, 55, 10));
		
		List<Score> result = matchingService.simpleMatch(scores, midiService.pairMidiEvents(notes));
		result = statisticsService.calcOffset(result);
		
		assertEquals(  0.0, result.get(0).retrieveAdditional().getOffset(), 0.1);
		assertEquals(  7.0, result.get(0).getSibling().retrieveAdditional().getOffset(), 0.1);
		assertEquals( 12.0, result.get(0).getSibling().getSibling().retrieveAdditional().getOffset(), 0.1);
	}
	
	@Test
	public void statisticValueTest() {
		double eps = 0.001;
		
		List<Double> values = Arrays.asList(10.0, 9.0, 11.0, 10.0, 12.0, 14.0, 8.0, 10.0);
		StatisticValue statisticValue = statisticsService.getStatisticValue(values);
		assertEquals( 10.5, statisticValue.getMean(), eps );
		assertEquals( 1.732, statisticValue.getStandardDeviation(), eps );
		
		double[] spectrum = statisticValue.getSpectrum();
		assertEquals( 8, spectrum.length );
		assertEquals( 84, spectrum[0], eps );
		assertEquals( 5.561, spectrum[1], eps );
		assertEquals( 4.243, spectrum[2], eps );
		assertEquals( 6.713, spectrum[3], eps );
		assertEquals( 2.0, spectrum[4], eps );
		assertEquals( 6.713, spectrum[5], eps );
		assertEquals( 4.243, spectrum[6], eps );
		assertEquals( 5.561, spectrum[7], eps );
	}
}
