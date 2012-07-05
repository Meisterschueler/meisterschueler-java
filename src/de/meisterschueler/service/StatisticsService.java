package de.meisterschueler.service;

import java.util.List;
import java.util.Map;

import de.meisterschueler.basic.Finger;
import de.meisterschueler.basic.Score;
import de.meisterschueler.basic.SongAnalysis;

public interface StatisticsService {

	List<Score> calcDuration(List<Score> result);

	List<Score> calcSpeed(List<Score> scores);

	List<Score> calcGap(List<Score> scores);

	List<Score> calcOffset(List<Score> scores);

	SongAnalysis retrieveAnalysis(List<Score> scores, Finger finger);

	Map<Finger, SongAnalysis> retrieveAnalysis(List<Score> scores);
}
