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
		
		songBook.setName("51 �bungen f�r Klavier");
		songBook.setComposer("Johannes Brahms");
		songBook.setSongs(songs);
	}

	private Song getNo(int no) {
		String leftHand = null;
		String rightHand = null;
		String name = "";
		String description = "";
		Long id = null;
		Key key = null;
		switch (no) {
		case 0: {
			name = "15";
			id = 20500L;
            key = Key.C;
            rightHand = "";
            leftHand = "";
            break;
		}
	
	        case 1: {
	            name = "8a";
	            id = 20801L;
	            key = Key.C;
	            rightHand = "\\meter<\"4/4\">c2/1 \\repeatBegin d/24 f d a1 f a d2 f d a1 f a d2 f d b&1 g b& d2 e d b&1 g b& d2 e d b1 g# b d2 e " +
	            		"d b1 g# b c#2 e c# b&1 a b& c#2 e c# b&1 a b& \\repeatEnd " +
	            		"\\repeatBegin d2 f d a1 f a d2 f d a1 f a d2 f d a#1 f a# d2 f d a#1 f a#" +
	            		"d2 f d b1 g b d2 f d b1 g b d2 e d b1 g b d2 e d b1 g b " +
	            		"d2 e d a1 g a d2 e d a1 g a {c2,e} {e,a} {c,e} a1 g a {c2,e} {e,a} {c,e} a1 g a \\repeatEnd {f/2,a,d2} _ ";
	            leftHand  = "\\clef<\"bass\"> \\meter<\"4/4\">c1/1 \\repeatBegin  a0/24 f a d1 f d a0 f a d1 f d b&0 g b& d1 f d b&0 g b& d1 e d b0 g# b d1 e d b0 g#" +
	            		"b d1 e d b&0 a b& c#1 e c# b&0 g# b& c#1 e c# \repeatEnd" +
	            		"\\repeatBegin a0 f a d1 f d a0 f a d1 f d a#0 f a# d1 f d a#0 f a# d1 f d" +
	            		"b0 g b d1 f d b0 g b d1 f d b0 g b d1 e d b0 g b d1 e d" +
	            		"a0 g a d1 e d a0 g a d1 e d a0 g a c1 e c a0 g a c1 e c" +
	            		"\\repeatEnd {f0/2,a,d1} _";
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
