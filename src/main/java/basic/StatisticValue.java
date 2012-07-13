package basic;

public class StatisticValue {
	private double mean;
	private double variance;
	private double[] spectrum;

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	public double getVariance() {
		return variance;
	}

	public void setVariance(double variance) {
		this.variance = variance;
	}


	public double[] getSpectrum() {
		return spectrum;
	}
	
	public void setSpectrum(double[] spectrum) {
		this.spectrum = spectrum;
	}
}
