package de.meisterschueler.basic;

public class StatisticValue {
	private double mean;
	private double standardDeviation;
	private double[] spectrum;

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	public double getStandardDeviation() {
		return standardDeviation;
	}

	public void setVariance(double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public double[] getSpectrum() {
		return spectrum;
	}
	
	public void setSpectrum(double[] spectrum) {
		this.spectrum = spectrum;
	}
}
