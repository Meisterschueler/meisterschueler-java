package basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Song {
	private String name;
	private Map<Hand, List<Score> > voices = new HashMap<Hand, List<Score> >();
	private Key key;
	private String description;
	
	public Song() {
		this.key = Key.C;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Score> getVoice(Hand hand) {
		return voices.get(hand);
	}

	public void setVoice(Hand hand, List<Score> voice) {
		this.voices.put(hand, voice);
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
