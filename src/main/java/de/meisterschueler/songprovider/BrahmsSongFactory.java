package de.meisterschueler.songprovider;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.fraction.Fraction;

import de.meisterschueler.basic.Hand;
import de.meisterschueler.basic.Key;
import de.meisterschueler.basic.Song;
import de.meisterschueler.service.GuidoService;

public class BrahmsSongFactory extends SongFactory {
	private GuidoService guidoService = new GuidoService();

	private List<Song> songs = new ArrayList<Song>();

	public BrahmsSongFactory() {
		for (int i = 0; i <= 8; i++) {
			songs.add(getNo(i));
		}
		
		songBook.setName("51 Übungen für Klavier");
		songBook.setComposer("Johannes Brahms");
		songBook.setSongs(songs);
	}

	private Song getNo(int no) {
		String leftHand = null;
		String rightHand = null;
		Fraction meter = new Fraction(2,4);
		String name = "";
		String description = "";
		Long id = null;
		Key key = null;
		switch (no) {
	        case 0: {
	            name = "8a";
	            id = 20801L;
	            meter = new Fraction(2,4);
	            key = Key.C;
	            rightHand = "|: d1/16 c e g b c2 a1 f :||: d& c e g b c2 a&1 f :||: e& d f# a c#2 d b&1 g :||: f e g# b d#2 e c a1 :||: g f a c2 e f d b&1 :||: g& f a c2 e f d& b&1 :||: a& g b d2 f# g e& c :||: b&1 a c#2 e g# a f d :||: c b1 d#2 f# a# b g e :| d c e g b c3 a2 f d c a1 f d c a0 f";
	            leftHand = "";
	            break;
	        }
		}
		
		Song song = new Song();
		song.setVoice(Hand.LEFT, guidoService.gmnToScores(leftHand));
		song.setVoice(Hand.RIGHT, guidoService.gmnToScores(rightHand));
		song.setKey(key);
		song.setName("No. " + name);
		if (!description.isEmpty()) {
			song.setDescription(description);
		}
		//		song.meter = meter;

		return song;
	}
}
