package de.meisterschueler.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import de.meisterschueler.basic.Score;

public class ScoreServiceTest {
	private GuidoService guidoService = new GuidoService();
	private ScoreService scoreService = new ScoreService();
	
	@Test
	public void mergeTest() {
		String rightGmn = "c0/4 g/8 f {c/4,e}";
		String leftGmn = "_/8 c-1 {b-2,d-1} g1 c-1";
		List<Score> rightScores = guidoService.gmnToScores(rightGmn);
		List<Score> leftScores = guidoService.gmnToScores(leftGmn);
		List<Score> merged = scoreService.mergeScores(rightScores, leftScores);
		
		merged = scoreService.removePause(merged);
		
		assertEquals( 10, merged.size() );
		
		assertEquals( 48, merged.get(0).getPitch() );
		assertEquals( 36, merged.get(1).getPitch() );
		assertEquals( 35, merged.get(2).getPitch() );
		assertEquals( 38, merged.get(3).getPitch() );
		assertEquals( 55, merged.get(4).getPitch() );
		assertEquals( 53, merged.get(5).getPitch() );
		assertEquals( 67, merged.get(6).getPitch() );
		assertEquals( 36, merged.get(7).getPitch() );
		assertEquals( 48, merged.get(8).getPitch() );
		assertEquals( 52, merged.get(9).getPitch() );
	}
	
	@Test
	public void removePauseTest() {
		String gmn = "c0 _ d e _ f g";
		List<Score> scores = guidoService.gmnToScores(gmn);
		
		assertEquals( 7, scores.size() );
		
		scores = scoreService.removePause(scores);
		
		assertEquals( 5, scores.size() );
	}
}
