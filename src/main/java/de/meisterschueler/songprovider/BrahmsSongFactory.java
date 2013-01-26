package de.meisterschueler.songprovider;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.fraction.Fraction;

import de.meisterschueler.basic.Hand;
import de.meisterschueler.basic.Key;
import de.meisterschueler.basic.Score;
import de.meisterschueler.basic.Song;
import de.meisterschueler.service.GuidoService;

public class BrahmsSongFactory extends SongFactory {
	private GuidoService guidoService = new GuidoService();

	private List<Song> songs = new ArrayList<Song>();

	public BrahmsSongFactory() {
		for (int i = 0; i <= 2; i++) {
			songs.add(getNo(i));
		}

		songBook.setName("51 Übungen für Klavier");
		songBook.setComposer("Johannes Brahms");
		songBook.setSongs(songs);
	}

	private Song getNo(int no) {
		List<Score> leftHand = null;
		List<Score> rightHand = null;
		Fraction meter = new Fraction(2,4);
		String name = "";
		String description = "";
		Long id = null;
		Key key = null;
		switch (no) {
		case 0: {
			name = "15";
			id = 21500L;
			key = Key.C;
			String rightPattern = "\\meter<\"4/4\">c2/1 \\repeatBegin d/24 f d a1 f a d2 f d a1 f a d2 f d b&1 g b& d2 e d b&1 g b& d2 e d b1 g# b d2 e " +
					"d b1 g# b c#2 e c# b&1 a b& c#2 e c# b&1 a b& \\repeatEnd " +
					"\\repeatBegin d2 f d a1 f a d2 f d a1 f a d2 f d a#1 f a# d2 f d a#1 f a#" +
					"d2 f d b1 g b d2 f d b1 g b d2 e d b1 g b d2 e d b1 g b " +
					"d2 e d a1 g a d2 e d a1 g a {c2,e} {e,a} {c,e} a1 g a {c2,e} {e,a} {c,e} a1 g a \\repeatEnd {f/2,a,d2} _ ";
			String leftPattern  = "\\clef<\"bass\"> \\meter<\"4/4\">c1/1 \\repeatBegin  a0/24 f a d1 f d a0 f a d1 f d b&0 g b& d1 f d b&0 g b& d1 e d b0 g# b d1 e d b0 g#" +
					"b d1 e d b&0 a b& c#1 e c# b&0 g# b& c#1 e c# \repeatEnd" +
					"\\repeatBegin a0 f a d1 f d a0 f a d1 f d a#0 f a# d1 f d a#0 f a# d1 f d" +
					"b0 g b d1 f d b0 g b d1 f d b0 g b d1 e d b0 g b d1 e d" +
					"a0 g a d1 e d a0 g a d1 e d a0 g a c1 e c a0 g a c1 e c" +
					"\\repeatEnd {f0/2,a,d1} _";
			break;
		}

		case 1: {
			name = "8a";
			id = 20801L;
			meter = new Fraction(2,4);
			key = Key.C;
			String pattern1 = "|: d1/16 c e g b c2 a1 f :||: d& c e g b c2 a&1 f :||: e& d f# a c#2 d b&1 g :||: f e g# b d#2 e c a1 :||: g f a c2 e f d b&1 :||: g& f a c2 e f d& b&1 :||: a& g b d2 f# g e& c :||: b&1 a c#2 e g# a f d :||: c b1 d#2 f# a# b g e :|";
			String pattern2 = "d c e g b c3 a2 f d c a1 f";

			int leftFingers1[] = { 4, 5, 4, 3, 2, 1, 2, 3 };
			int leftFingers2[] = { 4, 5, 4, 3, 2, 1, 2, 3, 4, 1, 2, 3 };
			int rightFingers1[]  = { 2, 1, 2, 3, 4, 5, 4, 3 };
			int rightFingers2[]  = { 2, 1, 2, 3, 4, 5, 4, 3, 2, 1, 4, 3 };

			leftHand = guidoService.gmnToScores(pattern1, leftFingers1);
			leftHand.addAll(guidoService.gmnToScores(pattern2, leftFingers2));

			rightHand = guidoService.gmnToScores(guidoService.oneOctaveUp(pattern1), rightFingers1);
			rightHand.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(pattern2), rightFingers2));

			break;
		}

		case 2: {
			name = "8b";
			id = 20802L;
			meter = new Fraction(2,4);
			key = Key.C;
			String pattern1 =  "|: d0/16 c e g b c1 e g b c2 e g b c3 a2 f d c a1 f d c a0 f :|";
			pattern1 += "|: d&0 c e g b c1 e g b c2 e g b c3 a&2 f d& c a&1 f d& c a&0 f :|";
			pattern1 += "|: e&0 d f# a c#1 d f# a c#2 d f# a c#3 d b2 g e d b1 g e d b0 g :|";
			pattern1 += "|: f0 e g# b d :|";
			
			String pattern2 = "d1 c e g b c2 e g b c3 e g c4/4 {e1,c2} _";
			
			int leftFingers1[] = { 4, 5, 4, 3, 2, 1, 2, 3 };
			int leftFingers2[] = { 4, 5, 4, 3, 2, 1, 2, 3, 4, 1, 2, 3 };
			int rightFingers1[]  = { 2, 1, 2, 3, 4, 5, 4, 3 };
			int rightFingers2[]  = { 2, 1, 2, 3, 4, 5, 4, 3, 2, 1, 4, 3 };

			leftHand = guidoService.gmnToScores(pattern1, leftFingers1);
			leftHand.addAll(guidoService.gmnToScores(pattern2, leftFingers2));

			rightHand = guidoService.gmnToScores(guidoService.oneOctaveUp(pattern1), rightFingers1);
			rightHand.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(pattern2), rightFingers2));

			break;
		}
		case 19: {
			
		}

		}

		Song song = new Song();
		song.setVoice(Hand.LEFT, leftHand);
		song.setVoice(Hand.RIGHT, rightHand);
		song.setKey(key);
		song.setName("B. " + name);
		if (!description.isEmpty()) {
			song.setDescription(description);
		}
		//		song.meter = meter;

		return song;
	}
}
