package de.meisterschueler.basic;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Song {
	private String name;
	private String description;
	private Long id;
	private Map<Hand, List<Score> > voices = new HashMap<Hand, List<Score> >();
	private Key key;
	
	public Song() {
		this.key = Key.C;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Score> getVoice(Hand hand) {
		return voices.get(hand);
	}

	public void setVoice(Hand hand, List<Score> voice) {
		this.voices.put(hand, voice);
	}
	
	public Set<Hand> getHands() {
		return voices.keySet();
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
}
