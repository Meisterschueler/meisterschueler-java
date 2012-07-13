package basic;

public class AdditionalValues {
	
	// Available if NoteOn AND NoteOff exist
	private double duration;	// duration in ms

	// Available if matched to a score
	private double speed;	
	private double gap;			// difference to the next note in ms

	// Available if score belongs to a chord
	private int offset;			// difference to the base note in ms

	public double getDuration() {
		return duration;
	}
	
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getGap() {
		return gap;
	}

	public void setGap(double gap) {
		this.gap = gap;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}
