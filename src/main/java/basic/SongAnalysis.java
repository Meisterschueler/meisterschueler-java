package basic;

public class SongAnalysis {
	private StatisticValue velocity;
	
	private StatisticValue duration;
	private StatisticValue speed;
	private StatisticValue gap;
	private StatisticValue offset;
	
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
