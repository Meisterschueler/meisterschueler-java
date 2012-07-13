package service;

import java.util.List;

import basic.MidiEventPair;
import basic.Score;


public interface GuidoService {
	public abstract Score gmnToScore(String gmnString);

	public abstract List<Score> gmnToScores(String gmnString);
	
	public abstract List<Score> gmnToScores(String gmnString, int[] fingers);

	public abstract List<Score> gmnToScores(String gmnString, int[] fingers, int[] steps);
	
	public abstract String scoresToString(List<Score> scores);

	public abstract String inflateGmn(String gmnString);

	public abstract String deflateGmn(String gmnString);

	public abstract String transposeGmn(String from, String to, String gmn);
	
	public abstract List<MidiEventPair> gmnToMidi(String gmnString);

	public abstract List<String> gmnToInvertedChords(String gmnString);
}
