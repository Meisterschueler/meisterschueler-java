package basic;

import java.util.List;

public class NamedChord {

	private String postfix;
	private List<Score> scores;
	
	private String intervalString;
	private String noteString;
	
	private Key key;
	private Mode mode;
	private int inversion;

	public static enum Mode { MAJOR, MINOR };
	
	public NamedChord() {
	}

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public String getIntervalString() {
		return intervalString;
	}

	public void setIntervalString(String intervalString) {
		this.intervalString = intervalString;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public int getInversion() {
		return inversion;
	}

	public void setInversion(int inversion) {
		this.inversion = inversion;
	}

}
