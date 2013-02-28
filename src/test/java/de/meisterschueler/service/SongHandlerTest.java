package de.meisterschueler.service;



import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import de.meisterschueler.basic.Hand;
import de.meisterschueler.basic.Score;
import de.meisterschueler.basic.Song;

public class SongHandlerTest {

	private SongHandler songHandler = new SongHandler();
	
	@Test
	public void getSongsTest() {
		List<Song> songs = songHandler.getSongs();
		for (Song song : songs) {
			for (Hand hand : song.getHands()) {
				List<Score> scores = song.getVoice(hand);
				double position = 0.0;
				System.out.println("Trying: " + song.getName() + " (" + hand.toString() + ")");
				for (Score score : scores) {
					assertTrue( score.getPosition().doubleValue() >= position );
					position = score.getPosition().doubleValue();
				}
			}
		}
	}

}
