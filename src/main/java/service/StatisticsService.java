package service;

import java.util.List;
import java.util.Map;

import basic.Finger;
import basic.Score;
import basic.SongAnalysis;


public interface StatisticsService {

	List<Score> calcDuration(List<Score> result);

	List<Score> calcSpeed(List<Score> scores);

	List<Score> calcGap(List<Score> scores);

	List<Score> calcOffset(List<Score> scores);

	SongAnalysis retrieveAnalysis(List<Score> scores, Finger finger);

	Map<Finger, SongAnalysis> retrieveAnalysis(List<Score> scores);
}
