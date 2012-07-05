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
import de.meisterschueler.basic.Score.Status;
import de.meisterschueler.basic.SongAnalysis;
import de.meisterschueler.basic.StatisticValue;

public class StatisticsServiceImpl implements StatisticsService {

	@Override
	public List<Score> calcDuration(List<Score> scores) {
		Score currentScore = null;
		for (Score score : scores) {
			currentScore = score;
			boolean chordFinished = false;
			do {
				if (currentScore.getNote().getNoteOn() != null && currentScore.getNote().getNoteOff() != null) {
					AdditionalValues statistics = currentScore.retrieveAdditional();
					statistics.setDuration(currentScore.getNote().getNoteOff().getTick() - currentScore.getNote().getNoteOn().getTick());
				}

				if (currentScore.getSibling() != null) {
					currentScore = currentScore.getSibling();
				} else {
					chordFinished = true;
				}

			} while (chordFinished == false);
		}

		return scores;
	}

	@Override
	public List<Score> calcSpeed(List<Score> scores) {

		double speed = 0;
		for (int idx=0; idx<scores.size(); idx++) {
			// find currentScore
			Score currentScore = scores.get(idx);
			boolean currentFound = true;
			while (currentScore.getStatus() != Status.PLAYED && currentFound) {
				if (currentScore.getSibling() != null) {
					currentScore = currentScore.getSibling();
				} else {
					currentFound = false;
				}
			}

			// find nextScore
			boolean nextFound = true;
			Score nextScore = null;
			for (int idxNext=idx+1; idxNext<scores.size(); idxNext++) {
				nextScore = scores.get(idxNext);
				while (nextScore.getStatus() != Status.PLAYED && nextFound) {
					if (nextScore.getSibling() != null) {
						nextScore = nextScore.getSibling();
					} else {
						nextFound = false;
					}
				}
			}
			if (nextScore == null) {
				nextFound = false;
			}

			if (currentFound == true && nextFound == true) {
				// calc speed 
				Fraction deltaPosition = nextScore.getPosition().subtract(currentScore.getPosition()); 
				double deltaTime = nextScore.getNote().getNoteOn().getTick()-currentScore.getNote().getNoteOn().getTick();

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

	@Override
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

	@Override
	public List<Score> calcOffset(List<Score> scores) {
		for (Score score : scores) {
			if (score.getSibling() != null) {
				while (score.getNote().getNoteOn() == null && score.getSibling() != null) {
					score = score.getSibling();
				}
				if (score.getSibling() != null) {
					long base = score.getNote().getNoteOn().getTick();
					score.retrieveAdditional().setOffset(0);
					do {
						score = score.getSibling();
						if (score.getNote() != null && score.getNote().getNoteOn() != null) {
							score.retrieveAdditional().setOffset((int) (score.getNote().getNoteOn().getTick()-base));
						}
					} while (score.getSibling() != null);
				}
			}
		}
		return scores;
	}

	@Override
	public Map<Finger, SongAnalysis> retrieveAnalysis(List<Score> scores) {
		Map<Finger,SongAnalysis> analysisMap = new HashMap<Finger,SongAnalysis>();
		for (Finger finger : Finger.values()) {
			SongAnalysis analysis = retrieveAnalysis(scores, finger);
			if (analysis != null) {
				analysisMap.put(finger, analysis);
			}
		}
		return analysisMap;
	}

	@Override
	public SongAnalysis retrieveAnalysis(List<Score> scores, Finger finger) {

		List<Double> velocities = new ArrayList<Double>();
		List<Double> durations = new ArrayList<Double>();
		List<Double> speeds = new ArrayList<Double>();
		List<Double> gaps = new ArrayList<Double>();
//		List<Double> offsets = new ArrayList<Double>();
		for (Score score : scores) {
			if (score.getStatus() == Status.PLAYED && (score.getFinger() == finger || finger == Finger.NONE)) {

				velocities.add((double)score.getNote().getNoteOn().getVelocity());

				AdditionalValues additional = score.retrieveAdditional();
				durations.add(additional.getDuration());

				speeds.add(additional.getSpeed());
				gaps.add(additional.getGap());
			}
		}

		SongAnalysis result = new SongAnalysis();
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
		double sd = StatUtils.variance(dvalues, mean);
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
