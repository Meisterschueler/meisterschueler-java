package de.meisterschueler.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import de.meisterschueler.basic.Score;

public class ScoreService {

	public List<Score> mergeScores(List<Score> rightScores, List<Score> leftScores) {
		List<Score> result = new ArrayList<Score>();
		result.addAll(rightScores);
		result.addAll(leftScores);
		
		result = removePause(result);
		
		Score[] resultArray = new Score[result.size()];
		result.toArray(resultArray);
		
		Arrays.sort(resultArray);
		
		return Arrays.asList(resultArray);
	}

	public List<Score> removePause(List<Score> scores) {
		Iterator<Score> scoreIt = scores.iterator();
		while (scoreIt.hasNext()) {
			Score score = scoreIt.next();
			if (score.isPause()) {
				scoreIt.remove();
			}
		}
		
		return scores;
	}

}
