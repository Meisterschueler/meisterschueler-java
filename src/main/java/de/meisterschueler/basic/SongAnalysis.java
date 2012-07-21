package de.meisterschueler.basic;

public class SongAnalysis {
	private int matchCount;
	private int wrongCount;
	private int extraCount;
	private int failCount;
	
	private StatisticValue velocity;
	
	private StatisticValue duration;
	private StatisticValue speed;
	private StatisticValue gap;
	private StatisticValue offset;
	
	public int getMatchCount() {
		return matchCount;
	}
	public void setMatchCount(int matchCount) {
		this.matchCount = matchCount;
	}
	public int getWrongCount() {
		return wrongCount;
	}
	public void setWrongCount(int wrongCount) {
		this.wrongCount = wrongCount;
	}
	public int getExtraCount() {
		return extraCount;
	}
	public void setExtraCount(int extraCount) {
		this.extraCount = extraCount;
	}
	public int getFailCount() {
		return failCount;
	}
	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}
	public StatisticValue getVelocity() {
		return velocity;
	}
	public void setVelocity(StatisticValue velocity) {
		this.velocity = velocity;
	}
	public StatisticValue getDuration() {
		return duration;
	}
	public void setDuration(StatisticValue duration) {
		this.duration = duration;
	}
	public StatisticValue getSpeed() {
		return speed;
	}
	public void setSpeed(StatisticValue speed) {
		this.speed = speed;
	}
	public StatisticValue getGap() {
		return gap;
	}
	public void setGap(StatisticValue gap) {
		this.gap = gap;
	}
	public StatisticValue getOffset() {
		return offset;
	}
	public void setOffset(StatisticValue offset) {
		this.offset = offset;
	}
}
