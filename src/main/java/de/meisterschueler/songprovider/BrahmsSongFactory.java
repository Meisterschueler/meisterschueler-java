package de.meisterschueler.songprovider;

import java.util.ArrayList;
import java.util.List;

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

		case 4: {
			name = "8b";
			id = 20801L;
			key = Key.C;
			rightHand = "\\meter<\"3/4\">\\repeatBegin d0/16  c e g b c1 e g b c2 e g b c3 a2 f d c a1 f d c a0 f \\repeatEnd " +
					"\\repeatBegin d&0 c e g b c1 e g b c2 e g b c3 a2 f d c a1 f d c a0 f \\repeatEnd " +
					"\\repeatBegin e&0 d f# a c#1 d f# a c#2 d f# a c#3 d b&2 g e& d b&1 g \\repeatEnd " +
					"\\repeatBegin f0 e g# b d#1 e g# b d#2 e g# b d#3 e c a2 f e c a1 \\repeatEnd " +
					"\\repeatBegin g0 f a c1 e f a c2 e f a c3 e f d b&2 g f d b&1 g f d b&0 \\repeatEnd " +
					"\\repeatBegin g&0 f a c1 e f a c2 e f a c3 e f d& b&2 g& f d& b&1 g& f d& b&0 \\repeatEnd " +
					"\\repeatBegin a&0 g b d1 f# g b d2 f# g b d3 f# g e& c a&2 g e& c a&1 g e& c \\repeatEnd " +
					"\\repeatBegin b&0 a c#1 e g# a c#2 e g# a c#3 e g# a f d b&2 a f d b&1 a f d \\repeatEnd " +
					"\\repeatBegin c1 b0 d#1 f# a# b d#2 f# a# b d#3 f# a# b g e c b2 g e c b1 g e \repeatEnd";
			leftHand = "";

			String patternRight2 = "d1 c e g b c2 e g b c3 e g c4/4 {e1,c2} _";
			String patternLeft2  = "d0 c e g b c1 e g b c2 e g c3/4 {c0,g,c1}";
			break;
		}
		
		case 5: {
			name = "19";
			id = 20900L;
            key = Key.C;
            rightHand = "";
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
