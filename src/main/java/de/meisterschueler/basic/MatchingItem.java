package de.meisterschueler.basic;

import java.util.List;

public class MatchingItem {
	private Song song;

	
	private List<Score> scores;
	private String scorePitchSequence;
	private String scoreIntervalSequence;

	private List<MidiEventPair> notes;
	private String notePitchSequence;
	private String noteIntervalSequence;
	
	private String pressedSequence;

	private String pitchAlignment;
	private String intervalAlignment;

	private boolean prunning;
	private int saveRegion;

	private Key key;
	private int transposition;

	private Hand hand;

	private double quality;

	private boolean finished;

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public int getTransposition() {
		return transposition;
	}

	public void setTransposition(int transposition) {
		if (scorePitchSequence != null) {
			String newScorePitchSequence = new String();
			for (int i=0; i<scorePitchSequence.length(); i++) {
				newScorePitchSequence += (char)(scorePitchSequence.charAt(i) + transposition);
			}
			scorePitchSequence = newScorePitchSequence;
		}

		this.transposition += transposition;
	}

	public String getNotePitchSequence() {
		return notePitchSequence;
	}

	public void setNotePitchSequence(String noteSequence) {
		this.notePitchSequence = noteSequence;
	}

	public String getNoteIntervalSequence() {
		return noteIntervalSequence;
	}

	public void setNoteIntervalSequence(String intervalSequence) {
		this.noteIntervalSequence = intervalSequence;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public String getScorePitchSequence() {
		return scorePitchSequence;
	}
	
	public void setScorePitchSequence(String pitchSequence) {
		this.scorePitchSequence = pitchSequence;
	}

	public String getScoreIntervalSequence() {
		return scoreIntervalSequence;
	}

	public void setScoreIntervalSequence(String intervalSequence) {
		this.scoreIntervalSequence = intervalSequence;
	}

	public List<MidiEventPair> getNotes() {
		return notes;
	}

	public void setNotes(List<MidiEventPair> midiEventPairs) {
		this.notes = midiEventPairs;
	}

	public double getQuality() {
		return quality;
	}

	public void setQuality(double quality) {
		this.quality = quality;
	}

	public String getPitchAlignment() {
		return pitchAlignment;
	}

	public void setPitchAlignment(String pitchAlignment) {
		this.pitchAlignment = pitchAlignment;
	}

	public String getIntervalAlignment() {
		return intervalAlignment;
	}

	public void setIntervalAlignment(String intervalAlignment) {
		this.intervalAlignment = intervalAlignment;
	}

	public boolean isPrunning() {
		return prunning;
	}

	public void setPrunning(boolean prunning) {
		this.prunning = prunning;
	}

	public int getSaveRegion() {
		return saveRegion;
	}

	public void setSaveRegion(int saveRegion) {
		this.saveRegion = saveRegion;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public String getPressedSequence() {
		return pressedSequence;
	}
	
	public void setPressedSequence(String pressedSequence) {
		this.pressedSequence = pressedSequence;
	}
}
