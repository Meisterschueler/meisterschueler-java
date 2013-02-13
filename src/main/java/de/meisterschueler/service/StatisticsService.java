package de.meisterschueler.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import de.meisterschueler.basic.AdditionalValues;
import de.meisterschueler.basic.Finger;
import de.meisterschueler.basic.Score;
import de.meisterschueler.basic.SongAnalysis;
import de.meisterschueler.basic.StatisticValue;
import de.meisterschueler.basic.Score.Status;



public class StatisticsService {

	public List<Score> calcDuration(List<Score> scores) {
		for (Score score : scores) {
			if (score.getNote().getNoteOn() != null && score.getNote().getNoteOff() != null) {
				AdditionalValues statistics = score.retrieveAdditional();
				statistics.setDuration(score.getNote().getNoteOff().getTick() - score.getNote().getNoteOn().getTick());
			}
		}

		return scores;
	}

	public List<Score> calcSpeed(List<Score> scores) {

		if (scores.size() < 2) {
			return scores;
		}
		
		double speed = 0;
		Iterator<Score> scoreIt = scores.iterator();
		Iterator<Score> postScoreIt = scores.iterator();
		
		Score currentScore = null;
		Score postScore = null;
		while (scoreIt.hasNext()) {
			// find currentScore
			boolean currentFound = false;
			while (currentFound == false && scoreIt.hasNext()) {
				currentScore = scoreIt.next();
				if (currentScore.getStatus() == Status.PLAYED) {
					currentFound = true;
				}
			}
		
			// find nextScore
			boolean postFound = false;
			if (currentScore != null && postScore != null && postScore.getPosition().doubleValue() > currentScore.getPosition().doubleValue()) {
				postFound = true;
			}
			while (postFound == false && postScoreIt.hasNext()) {
				postScore = postScoreIt.next();
				if (postScore.getStatus() == Status.PLAYED && postScore.getPosition().doubleValue() > currentScore.getPosition().doubleValue()) {
					postFound = true;
				}
			}

			if (currentFound == true && postFound == true) {
				// calc speed 
				Fraction deltaPosition = postScore.getPosition().subtract(currentScore.getPosition()); 
				double deltaTime = postScore.getNote().getNoteOn().getTick()-currentScore.getNote().getNoteOn().getTick();

				speed = deltaPosition.doubleValue()/deltaTime*1000.0*4.0*60.0;
				AdditionalValues statistics = currentScore.retrieveAdditional();
				statistics.setSpeed(speed);
			} else if (currentFound == true){
				// get last valid speed
				AdditionalValues statistics = currentScore.retrieveAdditional();
				statistics.setSpeed(speed);
			}
		} 

		return scores;
	}

	public List<Score> calcGap(List<Score> scores) {
		Iterator<Score> scoreIterator = scores.iterator();
		Score leadingScore = new Score();
		Score followerScore = new Score();

		while (scoreIterator.hasNext()) {
			// find good leadingNote
			while (scoreIterator.hasNext() && (leadingScore.getNote() == null || leadingScore.getNote().getNoteOff() == null)) {
				leadingScore = scoreIterator.next();	
			}

			// find good followerNote
			do {
				followerScore = scoreIterator.next();
			} while (scoreIterator.hasNext() && (followerScore.getNote() == null || followerScore.getNote().getNoteOn() == null));

			double gap = followerScore.getNote().getNoteOn().getTick() - leadingScore.getNote().getNoteOff().getTick();
			leadingScore.retrieveAdditional().setGap(gap);

			leadingScore = followerScore;
		}

		// Last score has no gap
		leadingScore.retrieveAdditional().setGap(0.0);


		return scores;
	}

	public List<Score> calcOffset(List<Score> scores) {
		if (scores.size() < 2) {
			return scores;
		}

		Score baseScore = null;
		Score score = null;

		Iterator<Score> scoreIt = scores.iterator();
		baseScore = scoreIt.next();

		while (scoreIt.hasNext()) {
			score = scoreIt.next();
			if (score.getPosition().doubleValue() == baseScore.getPosition().doubleValue()) {
				long offset = score.getNote().getNoteOn().getTick() - baseScore.getNote().getNoteOn().getTick();
				score.retrieveAdditional().setOffset((int)offset);
			} else {
				baseScore = score;
			}
		}
		return scores;
	}

	public Map<Finger, SongAnalysis> retrieveAnalysis(List<Score> scores) {
		Map<Finger,SongAnalysis> analysisMap = new HashMap<Finger, SongAnalysis>();
		for (Finger finger : Finger.values()) {
			SongAnalysis analysis = retrieveAnalysis(scores, finger);
			if (analysis != null) {
				analysisMap.put(finger, analysis);
			}
		}
		return analysisMap;
	}

	public SongAnalysis retrieveAnalysis(List<Score> scores, Finger finger) {

		List<Double> velocities = new ArrayList<Double>();
		List<Double> durations = new ArrayList<Double>();
		List<Double> speeds = new ArrayList<Double>();
		List<Double> gaps = new ArrayList<Double>();
		//		List<Double> offsets = new ArrayList<Double>();

		int matchCounter = 0;
		int extraCounter = 0;
		int missedCounter = 0;
		int wrongCounter = 0;
		for (Score score : scores) {
			Status status = score.getStatus();
			if (score.getFinger() == finger || finger == Finger.NONE) {
				if (status == Status.PLAYED) {
					matchCounter++;
				} else if (status == Status.EXTRA) {
					extraCounter++;
				} else if (status == Status.MISSED) {
					missedCounter++;
				} else if (status == Status.WRONG) {
					wrongCounter++;
				}

				if (status == Status.PLAYED) {

					velocities.add((double)score.getNote().getNoteOn().getVelocity());

					AdditionalValues additional = score.retrieveAdditional();
					durations.add(additional.getDuration());

					speeds.add(additional.getSpeed());
					gaps.add(additional.getGap());
				}
			}
		}

		SongAnalysis result = new SongAnalysis();
		result.setMatchCount(matchCounter);
		result.setWrongCount(wrongCounter);
		result.setExtraCount(extraCounter);
		result.setFailCount(missedCounter);

		if (velocities.size() > 0) {
			StatisticValue velocityValue = getStatisticValue(velocities);
			StatisticValue durationValue = getStatisticValue(durations);
			StatisticValue speedValue = getStatisticValue(speeds);
			StatisticValue gapValue = getStatisticValue(gaps);
			//			StatisticValue offsetValue = getStatisticValue(offsets);

			result.setVelocity(velocityValue);
			result.setDuration(durationValue);
			result.setSpeed(speedValue);
			result.setGap(gapValue);
			//			result.setOffset(offsetValue);
			return result;
		} else {
			return null;
		}
	}

	public StatisticValue getStatisticValue(List<Double> values) {
		double[] dvalues = new double[values.size()];
		for (int i=0; i<values.size(); i++) {
			dvalues[i] = values.get(i);
		}

		double mean = StatUtils.mean(dvalues);
		double sd = Math.sqrt(StatUtils.variance(dvalues, mean));
		double[] spectrum = makeFFT(dvalues);

		StatisticValue result = new StatisticValue();
		result.setMean(mean);
		result.setVariance(sd);
		result.setSpectrum(spectrum);

		return result;
	}

	public double[] makeFFT(double[] values) {
		// create a pow(2)-array
		int size = (int)Math.pow(2, Math.ceil(Math.log(values.length)/Math.log(2.0)));
		double[] pow2 = new double[size];
		for (int i=0; i<values.length; i++) {
			pow2[i] = values[i];
		}

		// Make FFT
		FastFourierTransformer fastFourierTransformer = new FastFourierTransformer(DftNormalization.STANDARD);
		Complex[] fft = fastFourierTransformer.transform(pow2, TransformType.FORWARD);

		// Compute and return power
		double[] result = new double[fft.length];
		for (int i=0; i<fft.length; i++) {
			result[i] = fft[i].abs();
		}
		return result;
	}
}
