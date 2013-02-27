package de.meisterschueler.songprovider;

import java.util.ArrayList;
import java.util.List;

import de.meisterschueler.basic.Hand;
import de.meisterschueler.basic.Score;
import de.meisterschueler.basic.Song;
import de.meisterschueler.service.GuidoService;
import de.meisterschueler.service.ScoreService;

public class HanonSongFactory extends SongFactory {

	private GuidoService guidoService = new GuidoService();
	private ScoreService scoreService = new ScoreService();

	private List<Song> songs = new ArrayList<Song>();

	public HanonSongFactory() {
		for (int i = 0; i <= 35; i++) {
			songs.add(getNo(i));
		}

		songBook.setName("The Virtuoso Pianist");
		songBook.setComposer("C. L. Hanon");
		songBook.setSongs(songs);
	}

	private Song getNo(int no) {
		List<Score> leftScores = new ArrayList<Score>();
		List<Score> rightScores = null;
		String name = "";
		String description = "";
		Long id = null;
		String meter = "2/4";
		switch (no) {
		case 0: {
			name = "1";
			description = "";
			id = 100L;

			String patternUp = "c0/16 e f g a g f e";
			int leftFingersUp[] = { 5, 4, 3, 2, 1, 2, 3, 4 };
			int rightFingersUp[]  = { 1, 2, 3, 4, 5, 4, 3, 2 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown    = "g2/16 e d c b1 c2 d e";
			int leftFingersDown[]  = { 1, 2, 3, 4, 5, 4, 3, 2 };
			int rightFingersDown[] = { 5, 4, 3, 2, 1, 2, 3, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14};

			List<Score> leftScoresUp = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			List<Score> leftScoresDown = guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown);
			Score lastUpScore = leftScoresUp.get(leftScoresUp.size()-1);
			scoreService.shiftScores(leftScoresDown, lastUpScore.getPosition().add(lastUpScore.getMeasure()));
			leftScores.addAll(leftScoresUp);
			leftScores.addAll(leftScoresDown);

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 1: {
			name = "2";
			description = "";
			id = 200L;

			String patternUp = "c0/16 e a g f g f e";
			int leftFingersUp[] = { 5, 3, 1, 2, 3, 2, 3, 4 };
			int rightFingersUp[] = { 1, 2, 5, 4, 3, 4, 3, 2 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2/16 d b1 c2 d c d e";
			int leftFingersDown[] = { 1, 3, 5, 4, 3, 4, 3, 2 };
			int rightFingersDown[] = { 5, 2, 1, 2, 3, 2, 3, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 2: {
			name = "3";
			description = "";
			id = 300L;

			String patternUp = "c0/16 e a g f e f g";
			int leftFingersUp[] = { 5, 3, 1, 2, 3, 4, 3, 2 };
			int rightFingersUp[] = { 1, 2, 5, 4, 3, 2, 3, 4 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2/16 d b1 c2 d e d c";
			int leftFingersDown[] = { 1, 3, 5, 4, 3, 2, 3, 4 };
			int rightFingersDown[] = { 5, 2, 1, 2, 3, 4, 3, 2 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 3: {
			name = "4";
			description = "Special exercise for the 3rd, 4th and 5th fingers of the hand.";
			id = 400L;

			String patternUp = "c0/16 d c e a g f e";
			int leftFingersUp[] = { 5, 4, 5, 3, 1, 2, 3, 4 };
			int rightFingersUp[] = { 1, 2, 1, 2, 5, 4, 3, 2 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2/16 f g d b1 c2 d e";
			int leftFingersDown[] = { 1, 2, 1, 3, 5, 4, 3, 2 };
			int rightFingersDown[] = { 5, 4, 5, 2, 1, 2, 3, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 4: {
			name = "5";
			description = "We repeat, that the fingers should be lifted high, and with precision, until this entire volm is mastered.";
			id = 500L;

			String patternUp = "c0/16 a g a f g e f";
			int leftFingersUp[] = { 5, 1, 2, 1, 3, 2, 4, 3 };
			int rightFingersUp[] = { 1, 5, 4, 5, 3, 4, 2, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "c2/16 d c e d f e g";
			int leftFingersDown[] = { 5, 4, 5, 3, 4, 2, 3, 1 };
			int rightFingersDown[] = { 1, 2, 1, 3, 2, 4, 3, 5 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));
			break;
		}

		case 5: {
			name = "6";
			description = "To obtain the good results which we promise those who study this work, it is indispensable to play daily, at least once, the exercise already learned.";
			id = 600L;

			String patternUp1 = "c0/16 a g a f a e a";
			String patternUp2 = "c0/16 a g a f a e d";
			int leftFingersUp1[] = { 5, 1, 2, 1, 3, 1, 4, 1 };
			int leftFingersUp2[] = { 5, 1, 2, 1, 3, 1, 4, 5 };
			int rightFingersUp1[] = { 1, 5, 4, 5, 3, 5, 2, 5 };
			int rightFingersUp2[] = { 1, 5, 4, 5, 3, 5, 2, 1 };
			int stepsUp1[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
			int stepsUp2[] = {13};

			String patternDown1 = "g2/16 b1 c2 b1 d2 b1 e2 b1";
			String patternDown2 = "g2/16 b1 c2 b1 d2 b1 e2 d2";
			int leftFingersDown1[] = { 1, 5, 4, 5, 3, 5, 2, 5 };
			int leftFingersDown2[] = { 1, 5, 4, 5, 3, 5, 2, 3 };
			int rightFingersDown1[] = { 5, 1, 2, 1, 3, 1, 4, 1 };
			int rightFingersDown2[] = { 5, 1, 2, 1, 3, 1, 4, 3 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};
			int stepsDown2[] = {-13};

			leftScores = guidoService.gmnToScores(patternUp1, leftFingersUp1, stepsUp1);
			leftScores.addAll(guidoService.gmnToScores(patternUp2, leftFingersUp2, stepsUp2));
			leftScores.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftScores.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp1), rightFingersUp1, stepsUp1);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp2), rightFingersUp2, stepsUp2));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));

			break;
		}

		case 6: {
			name = "7";
			description = "Exercise of the greatest importance for the 3rd, 4th and 5th fingers.";
			id = 700L;

			String patternUp = "c0/16 e d f e g f e";
			int leftFingersUp[] = { 5, 3, 4, 2, 3, 1, 2, 3 };
			int rightFingersUp[] = { 1, 3, 2, 4, 3, 5, 4, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2/16 e f d e c d e";
			int leftFingersDown[] = { 1, 3, 2, 4, 3, 5, 4, 3 };
			int rightFingersDown[] = { 5, 3, 4, 2, 3, 1, 3, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 7: {
			name = "8";
			description = "Very important exercise for all five fingers.";
			id = 800L;

			String patternUp = "c0/16 e g a f g e f";
			int leftFingersUp[] = { 5, 4, 2, 1, 3, 2, 4, 3 };
			int rightFingersUp[] = { 1, 2, 4, 5, 3, 4, 2, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2/16 e c b1 d2 c e d";
			int leftFingersDown[] = { 1, 2, 4, 5, 3, 4, 2, 3 };
			int rightFingersDown[] = { 5, 4, 2, 1, 3, 2, 4, 3};
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 8: {
			name = "9";
			description = "Extension of the 4th and 5th, and general finger-exercise.";
			id = 900L;

			String patternUp = "c0/16 e f e g f a g";
			int leftFingersUp[] = { 5, 4, 3, 4, 2, 3, 1, 2 };
			int rightFingersUp[] = { 1, 2, 3, 2, 4, 3, 5, 4 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown1 = "g2/16 e d e c d b1 c2";
			String patternDown2 = "g2/16 e d e c d c d";
			int leftFingersDown1[] = { 1, 2, 3, 2, 4, 3, 5, 4 };
			int leftFingersDown2[] = { 1, 2, 3, 2, 4, 3, 4, 3 };
			int rightFingersDown1[] = { 5, 4, 3, 4, 2, 3, 1, 2 };
			int rightFingersDown2[] = { 5, 4, 3, 4, 2, 3, 2, 3 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};
			int stepsDown2[] = {-13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftScores.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));

			break;
		}

		case 9: {
			name = "10";
			description = "Preparation for the trill, for the 3rd and 4th fingers of the left hand in ascending (1); and for the 3rd and 4th of the right, descending (2).";
			id = 1000L;

			String patternUp = "c0/16 a g f e f e f";
			int leftFingersUp[] = { 5, 1, 2, 3, 4, 3, 4, 3 };
			int rightFingersUp[] = { 1, 5, 4, 3, 2, 3, 2, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2/16 b1 c2 d e d e d";
			int leftFingersDown[] = { 1, 5, 4, 3, 2, 3, 2, 3 };
			int rightFingersDown[] = { 5, 1, 2, 3, 4, 3, 4, 3 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 10: {
			name = "11";
			description = "Another preparation for the trill, for the 4th and 5th fingers.";
			id = 1100L;

			String patternUp = "c0/16 e a g a g f g";
			int leftFingersUp[] = { 5, 3, 1, 2, 1, 2, 3, 2 };
			int rightFingersUp[] = { 1, 2, 5, 4, 5, 4, 3, 4 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2/16 d b1 c2 b1 c2 d c";
			int leftFingersDown[] = { 1, 3, 5, 4, 5, 4, 3, 2 };
			int rightFingersDown[] = { 5, 2, 1, 2, 1, 2, 3, 2 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));
			break;
		}

		case 11: {
			name = "12";
			description = "Extension of 1-5, and exercise for 3-4-5.";
			id = 1200L;

			String patternUp1  = "g0/16 c e d c d e c";
			String patternUp2  = "a0/16 c e d c d e c";
			String patternUp3  = "a0/16 c e d c d e f";
			int leftFingersUp12[]   = { 1, 5, 3, 4, 5, 4, 3, 5 };
			int leftFingersUp3[]    = { 1, 5, 3, 4, 5, 4, 3, 2 };
			int rightFingersUp12[]  = { 5, 1, 3, 2, 1, 2, 3, 1 };
			int rightFingersUp3[]   = { 5, 1, 3, 2, 1, 2, 3, 4 };
			int stepsUp1[] = {0};
			int stepsUp2[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
			int stepsUp3[] = {13};

			String patternDown1    = "c2/16 a f g a g f a";
			String patternDown2    = "c0/16 g e f g f e f";
			int leftFingersDown[]  = { 1, 5, 3, 4, 5, 4, 3, 5 };
			int rightFingersDown[] = { 5, 1, 3, 2, 1, 2, 3, 4 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};
			int stepsDown2[] = {0};

			leftScores = guidoService.gmnToScores(patternUp1, leftFingersUp12, stepsUp1);
			leftScores.addAll(guidoService.gmnToScores(patternUp2, leftFingersUp12, stepsUp2));
			leftScores.addAll(guidoService.gmnToScores(patternUp3, leftFingersUp3, stepsUp3));
			leftScores.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown, stepsDown1));
			leftScores.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown, stepsDown2));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp1), rightFingersUp12, stepsUp1);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp2), rightFingersUp12, stepsUp2));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp3), rightFingersUp3, stepsUp3));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown1), rightFingersDown, stepsDown1));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown2), rightFingersDown, stepsDown2));

			break;
		}

		case 12: {
			name = "13";
			description = "(3-4-5)";
			id = 1300L;

			String patternUp = "e0/16 c f d g e f g";
			int leftFingersUp[] = { 3, 5, 2, 4, 1, 3, 2, 1 };
			int rightFingersUp[] = { 3, 1, 4, 2, 5, 3, 4, 5 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "e2/16 g d f e c d e";
			int leftFingersDown[] = { 3, 1, 4, 2, 3, 5, 4, 3 };
			int rightFingersDown[] = { 3, 5, 2, 4, 3, 1, 3, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 13: {
			name = "14";
			description = "(3-4) Another preparation for the trill, for the 3rd and 4th fingers.";
			id = 1400L;

			String patternUp = "c0/16 d f e f e g f";
			int leftFingersUp[] = { 5, 4, 2, 3, 2, 3, 1, 2 };
			int rightFingersUp[] = { 1, 2, 4, 3, 4, 3, 5, 4 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2/16 f d e d e c d";
			int leftFingersDown[] = { 1, 2, 4, 3, 4, 3, 5, 4 };
			int rightFingersDown[] = { 5, 4, 2, 3, 2, 3, 1, 3 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 14: {
			name = "15";
			description = "Extension of 1-2, and exercise for all 5 fingers.";
			id = 1500L;

			String patternUp1 = "c0/16 e d f e g f a";
			String patternUp2 = "c0/16 e d f e g f g";
			int leftFingersUp1[] = { 5, 3, 4, 2, 3, 1, 2, 1 };
			int leftFingersUp2[] = { 5, 3, 4, 2, 3, 1, 3, 2 };
			int rightFingersUp1[] = { 1, 2, 1, 3, 2, 4, 3, 5 };
			int rightFingersUp2[] = { 1, 2, 1, 3, 2, 4, 3, 4 };
			int stepsUp1[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
			int stepsUp2[] = {13};

			String patternDown1 = "g2/16 e f d e c d b1";
			String patternDown2 = "g2/16 e f d e c d c";
			int leftFingersDown1[] = { 1, 2, 1, 3, 2, 4, 3, 5 };
			int leftFingersDown2[] = { 1, 2, 1, 3, 2, 4, 3, 4 };
			int rightFingersDown1[] = { 5, 3, 4, 2, 3, 1, 2, 1 };
			int rightFingersDown2[] = { 5, 3, 4, 2, 3, 1, 3, 2 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10,- 11, -12};
			int stepsDown2[] = {-13};

			leftScores = guidoService.gmnToScores(patternUp1, leftFingersUp1, stepsUp1);
			leftScores.addAll(guidoService.gmnToScores(patternUp2, leftFingersUp2, stepsUp2));
			leftScores.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftScores.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp1), rightFingersUp1, stepsUp1);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp2), rightFingersUp2, stepsUp2));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));

			break;
		}

		case 15: {
			name = "16";
			description = "Extension of 3-5, and exercise for 3-4-5.";
			id = 1600L;

			String patternUp = "c0/16 e0 d0 e0 a0 g0 f0 g0";
			int leftFingersUp[] = { 5, 3, 4, 3, 1, 2, 3, 2 };
			int rightFingersUp[] = { 1, 3, 2, 3, 5, 4, 3, 4 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2/16 d2 e2 d2 b1 c2 d2 c2";
			int leftFingersDown[] = { 1, 3, 2, 3, 5, 4, 3, 4 };
			int rightFingersDown[] = { 1, 3, 2, 3, 5, 4, 3, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 16: {
			name = "17";
			description = "Extension of 1-2, 2-4, 4-5, and exercise for 3-4-5.";
			id = 1700L;

			String patternUp1  = "c0/16 e a g b a g a";
			String patternUp2  = "c0/16 e a g b a g f";
			int leftFingersUp1[] = { 5, 4, 2, 3, 1, 2, 3, 2 };
			int leftFingersUp2[] = { 5, 4, 2, 3, 1, 2, 3, 4 };
			int rightFingersUp1[] = { 1, 2, 4, 3, 5, 4, 3, 4 };
			int rightFingersUp2[] = { 1, 2, 4, 3, 5, 4, 3, 2 };
			int stepsUp1[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
			int stepsUp2[] = {13};

			String patternDown1    = "g2/16 d b1 c2 a1 b c2 a1";
			String patternDown2    = "g2/16 d b1 c2 a1 b c2 b1";
			int leftFingersDown1[]  = { 1, 2, 4, 3, 5, 4, 3, 5 };
			int leftFingersDown2[]  = { 1, 2, 4, 3, 5, 4, 3, 5 };
			int rightFingersDown1[] = { 5, 3, 2, 3, 1, 2, 3, 1 };
			int rightFingersDown2[] = { 5, 3, 2, 3, 1, 2, 3, 1 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11};
			int stepsDown2[] = {-12};

			leftScores = guidoService.gmnToScores(patternUp1, leftFingersUp1, stepsUp1);
			leftScores.addAll(guidoService.gmnToScores(patternUp2, leftFingersUp2, stepsUp2));
			leftScores.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftScores.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp1), rightFingersUp1, stepsUp1);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp2), rightFingersUp2, stepsUp2));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));
			break;
		}

		case 17: {
			name = "18";
			description = "(1-2-3-4-5)";
			id = 1800L;

			String patternUp = "c0/16 d f e g f d e";
			int leftFingersUp[] = { 5, 4, 2, 3, 1, 2, 4, 3 };
			int rightFingersUp[] = { 1, 2, 4, 3, 5, 4, 2, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2/16 f d e c d f e";
			int leftFingersDown[] = { 1, 2, 4, 3, 5, 4, 2, 3 };
			int rightFingersDown[] = { 5, 4, 2, 3, 1, 2, 4, 3 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 18: {
			name = "19";
			description = "(1-2-3-4-5)";
			id = 1900L;

			String patternUp = "c0/16 a f g a f e g";
			int leftFingersUp[] = { 5, 1, 3, 2, 1, 3, 4, 2 };
			int rightFingersUp[] = { 1, 5, 3, 4, 5, 3, 2, 4 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2/16 b1 d2 c b1 d2 e c";
			int leftFingersDown[] = { 1, 5, 3, 4, 5, 3, 2, 4 };
			int rightFingersDown[] = { 5, 1, 3, 2, 1, 3, 4, 2 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 19: {
			name = "20";
			description = "Extension of 2-4, 4-5, and exercise for 2-3-4.";
			id = 2000L;

			String patternUp1 = "e0/16 g c1 e c b0 c1 a0";
			String patternUp2 = "e0/16 g c1 e c b0 c1 g0";
			int leftFingersUp[] = { 5, 4, 2, 1, 2, 3, 2, 4 };
			int rightFingersUp[] = { 1, 2, 4, 5, 4, 3, 4, 2 };
			int stepsUp1[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
			int stepsUp2[] = {14};

			String patternDown1 = "e3/16 c3 g2 e2 g2 f2 g2 e2";
			String patternDown2 = "e3/16 c3 g2 e2 g2 f2 g2 f2";
			int leftFingersDown1[] = { 1, 2, 4, 5, 3, 4, 3, 5 };
			int leftFingersDown2[] = { 1, 2, 4, 5, 3, 4, 3, 4 };
			int rightFingersDown1[] = { 5, 4, 2, 1, 3, 2, 3, 1 };
			int rightFingersDown2[] = { 5, 4, 2, 1, 3, 2, 3, 2 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};
			int stepsDown2[] = {-14};

			leftScores = guidoService.gmnToScores(patternUp1, leftFingersUp, stepsUp1);
			leftScores.addAll(guidoService.gmnToScores(patternUp2, leftFingersUp, stepsUp2));
			leftScores.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftScores.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp1), rightFingersUp, stepsUp1);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp2), rightFingersUp, stepsUp2));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));

			break;
		}
		case 20: {
			name = "21";
			id = 2100L;
			meter = "4/4";

			String patternUp = "c0/16 d e d c e f g a g f g a g f e";
			int leftFingersUp[] = { 5, 4, 3, 4, 5, 4, 3, 2, 1, 2, 3, 2, 1, 2, 3, 4 };
			int rightFingersUp[] = { 1, 2, 3, 2, 1, 2, 3, 4, 5, 4, 3, 4, 5, 4, 3, 2 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown1 = "g2/16 f e f g e d c b1 c2 d c b1 c2 d e";
			String patternDown2 = "a0/16 g f g a f e d c d e d c d e d";
			int leftFingersDown1[] = { 1, 2, 3, 2, 1, 2, 3, 4, 5, 4, 3, 4, 5, 4, 3, 2 };
			int leftFingersDown2[] = { 1, 2, 3, 2, 1, 2, 3, 4, 5, 4, 3, 4, 5, 4, 3, 4 };
			int rightFingersDown1[] = { 5, 4, 3, 4, 5, 4, 3, 2, 1, 2, 3, 2, 1, 2, 3, 4 };
			int rightFingersDown2[] = { 5, 4, 3, 4, 5, 4, 3, 2, 1, 2, 3, 2, 1, 2, 3, 2 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};
			int stepsDown2[] = {0};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftScores.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));
			break;
		}
		case 21: {
			name = "22";
			id = 2200L;
			meter = "C";

			String patternUp = "c0/16 e d e c e f g a f g f a g f e";
			int leftFingersUp[] = { 5, 3, 2, 3, 5, 4, 3, 2, 1, 3, 2, 3, 1, 2, 3, 4 };
			int rightFingersUp[] = { 1, 3, 2, 3, 1, 2, 3, 4, 5, 3, 4, 3, 5, 4, 3, 2 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown1 = "g2/16 e f e g e d c b1 d2 c d b1 c2 d e";
			String patternDown2 = "a0/16 f g f a f e d c e d e c f e d";
			int leftFingersDown1[] = { 1, 3, 2, 3, 1, 2, 3, 4, 5, 3, 4, 3, 5, 4, 3, 2 };
			int leftFingersDown2[] = { 1, 3, 2, 3, 1, 2, 3, 4, 5, 3, 4, 3, 5, 2, 3, 4 };
			int rightFingersDown1[] = { 5, 3, 4, 3, 5, 4, 3, 2, 1, 3, 2, 3, 1, 2, 3, 4 };
			int rightFingersDown2[] = { 5, 3, 4, 3, 5, 4, 3, 2, 1, 3, 2, 3, 1, 4, 3, 2 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};
			int stepsDown2[] = {0};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftScores.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));
			break;
		}
		case 22: {
			name = "23";
			id = 2300L;
			meter = "C";

			String patternUp = "c0 d e d c d e d c a g f e f g f";
			int leftFingersUp[] = { 5, 4, 3, 4, 5, 4, 3, 4, 5, 1, 2, 3, 4, 3, 2, 3 };
			int rightFingersUp[] = { 1, 2, 3, 2, 1, 2, 3, 2, 1, 5, 4, 3, 2, 3, 4, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 f e f g f e f g b1 c2 d e d c d";
			int leftFingersDown[] = { 1, 2, 3, 2, 1, 2, 3, 2, 1, 5, 4, 3, 2, 3, 4, 3 };
			int rightFingersDown[] = { 5, 4, 3, 4, 5, 4, 3, 4, 5, 1, 2, 3, 4, 3, 2, 3 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		case 23: {
			name = "24";
			id = 2400L;
			meter = "C";

			String patternUp = "e0/16 d e c e d e c e d e c a f g e";
			int leftFingersUp[] = { 3, 4, 3, 5, 3, 4, 3, 5, 3, 4, 3, 5, 1, 3, 2, 4 };
			int rightFingersUp[] = { 3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 3, 1, 5, 3, 4, 2 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "e2/16 g f g e g f g e g f g b1 d2 c e";
			int leftFingersDown[] = { 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 2, 1, 5, 3, 4, 2 };
			int rightFingersDown[] = { 3, 5, 4, 5, 3, 5, 4, 5, 3, 5, 4, 5, 1, 3, 2, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		case 24: {
			name = "25";
			id = 2500L;
			meter = "C";

			String patternUp = "c0/16 d e c d e f d e f g f e g f e";
			int leftFingersUp1[] = { 5, 4, 3, 5, 4, 3, 2, 4, 3, 2, 1, 2, 3, 1, 3, 4 };
			int leftFingersUp2[] = { 5, 4, 3, 5, 4, 3, 2, 4, 3, 2, 1, 2, 3, 1, 3, 4 };
			int rightFingersUp1[] = { 1, 2, 3, 1, 2, 3, 4, 2, 3, 4, 5, 4, 3, 5, 4, 3 };
			int rightFingersUp2[] = { 1, 2, 3, 1, 2, 3, 4, 2, 3, 4, 5, 4, 3, 5, 3, 2 };
			int stepsUp1[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
			int stepsUp2[] = {13};

			String patternDown = "g2/16 f e g f e d f e d c e d c d e";
			int leftFingersDown1[] = { 1, 2, 3, 1, 2, 3, 4, 2, 3, 4, 5, 3, 4, 5, 4, 3 };
			int leftFingersDown2[] = { 1, 2, 3, 1, 2, 3, 4, 2, 3, 4, 5, 3, 4, 5, 3, 2 };
			int rightFingersDown[] = { 5, 4, 3, 5, 4, 3, 2, 4, 3, 2, 1, 3, 2, 1, 3, 4 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};
			int stepsDown2[] = {-13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp1, stepsUp1);
			leftScores.addAll(guidoService.gmnToScores(patternUp, leftFingersUp2, stepsUp2));
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown1, stepsDown1));
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown2, stepsDown2));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp1, stepsUp1);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp2, stepsUp2));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown1));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown2));

			break;
		}
		case 25: {
			name = "26";
			id = 2600L;
			meter = "C";

			String patternUp = "e0/16 f g e d e f d c d e c a g a g";
			int leftFingersUp[] = { 3, 2, 1, 3, 4, 3, 2, 4, 5, 4, 3, 5, 1, 2, 1, 2 };
			int rightFingersUp[] = { 3, 4, 5, 3, 2, 3, 4, 2, 1, 2, 3, 1, 5, 4, 5, 4 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2/16 e f g f d e f e c d e d c d c";
			int leftFingersDown[] = { 1, 3, 2, 1, 2, 4, 3, 2, 3, 5, 4, 3, 4, 3, 4, 3 };
			int rightFingersDown[] = { 5, 3, 4, 5, 4, 2, 3, 4, 3, 1, 2, 3, 2, 1, 2, 1 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		case 26: {
			name = "27";
			id = 2700L;
			meter = "C";

			String patternUp = "e0/16 f d e c e f g a g a g a g f e";
			int leftFingersUp[] = { 3, 2, 4, 2, 5, 4, 3, 2, 1, 2, 1, 2, 1, 2, 3, 4 };
			int rightFingersUp[] = { 3, 4, 2, 3, 1, 2, 3, 4, 5, 4, 5, 4, 5, 4, 3, 2 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2/16 f g e f e d c b1 c2 b1 c2 b1 c2 d e";
			int leftFingersDown[] = { 1, 2, 1, 3, 1, 2, 3, 4, 5, 4, 5, 4, 5, 4, 3, 2 };
			int rightFingersDown[] = { 5, 4, 5, 3, 5, 4, 3, 2, 1, 2, 1, 2, 1, 2, 3, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		case 27: {
			name = "28";
			id = 2800L;
			meter = "C";

			String patternUp = "c0/16 e d e c e d e c a g a f g e f";
			int leftFingersUp[] = { 5, 3, 4, 3, 5, 3, 4, 3, 5, 1, 2, 1, 3, 2, 4, 3 };
			int rightFingersUp[] = { 1, 3, 2, 3, 1, 3, 2, 3, 1, 5, 4, 5, 3, 4, 2, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2/16 e f e g e f e g b1 c2 b1 d2 c e d";
			int leftFingersDown[] = { 1, 3, 2, 3, 1, 3, 2, 3, 1, 5, 4, 5, 3, 4, 2, 3 };
			int rightFingersDown[] = { 5, 3, 4, 3, 5, 3, 4, 3, 5, 1, 2, 1, 3, 2, 4, 3 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		case 28: {
			name = "29";
			id = 2900L;
			meter = "C";

			String patternUp = "c0/16 d c e d e d f e f e g f g f g";
			int leftFingersUp1[] = { 5, 4, 5, 3, 4, 3, 4, 2, 3, 2, 3, 1, 2, 1, 2, 1 };
			int leftFingersUp2[] = { 5, 4, 5, 3, 4, 3, 4, 2, 3, 2, 3, 1, 3, 2, 3, 2 };
			int rightFingersUp1[] = { 1, 2, 1, 3, 2, 3, 2, 4, 3, 4, 3, 5, 4, 5, 4, 5 };
			int rightFingersUp2[] = { 1, 2, 1, 3, 2, 3, 2, 4, 3, 4, 3, 5, 4, 5, 3, 4 };
			int stepsUp1[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
			int stepsUp2[] = {13};

			String patternDown1 = "g2/16 f g e f e f d e d e c d e d e";
			String patternDown2 = "a0/16 g a f g f g e f e f d e f e d";
			int leftFingersDown1[] = { 1, 2, 1, 3, 2, 3, 2, 4, 3, 4, 3, 5, 4, 3, 4, 3 };
			int leftFingersDown2[] = { 1, 2, 1, 3, 2, 3, 2, 4, 3, 4, 3, 5, 3, 2, 3, 4 };
			int rightFingersDown1[] = { 5, 4, 5, 3, 4, 3, 4, 2, 3, 2, 3, 1, 2, 3, 2, 3 };
			int rightFingersDown2[] = { 5, 4, 5, 3, 4, 3, 4, 2, 3, 2, 3, 1, 3, 4, 3, 2 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};
			int stepsDown2[] = {0};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp1, stepsUp1);
			leftScores.addAll(guidoService.gmnToScores(patternUp, leftFingersUp2, stepsUp2));
			leftScores.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftScores.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp1, stepsUp1);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp2, stepsUp2));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));
			break;
		}
		case 29: {
			name = "30";
			id = 3000L;
			meter = "C";

			String patternUp = "c0/16 d c d c d c e a g a g a g a f";
			int leftFingersUp[] = { 5, 4, 5, 4, 5, 4, 5, 3, 1, 2, 1, 2, 1, 2, 1, 3 };
			int rightFingersUp[] = { 1, 2, 1, 2, 1, 2, 1, 3, 5, 4, 5, 4, 5, 4, 5, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown1 = "g2/16 f g f g f g e b1 c2 b1 c2 b1 c2 b1 d2";
			String patternDown2 = "g2 f g f g f g e b1 c2 b1 c2 b1 c2 b1 c2";
			int leftFingersDown1[] = { 1, 2, 1, 2, 1, 2, 1, 3, 5, 4, 5, 4, 5, 4, 5, 3 };
			int leftFingersDown2[] = { 1, 2, 1, 2, 1, 2, 1, 3, 5, 4, 5, 4, 5, 4, 5, 4 };
			int rightFingersDown1[] = { 5, 4, 5, 4, 5, 4, 5, 3, 1, 2, 1, 2, 1, 2, 1, 3 };
			int rightFingersDown2[] = { 5, 4, 5, 4, 5, 4, 5, 3, 1, 2, 1, 2, 1, 2, 1, 2 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};
			int stepsDown2[] = {-13};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftScores.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));

			break;
		}
		case 30: {
			name = "31";
			id = 3100L;
			meter = "3/4";

			String leftPatternUp = "c0/16 c1 b0 c1 a0 c1 g0 c1 f0 c1 e0 c1";
			String rightPatternUp = "e1/16 e2 d e c e b1 e2 a1 e2 g1 e2";
			int leftFingersUp[] = { 5, 1, 2, 1, 3, 1, 4, 1, 5, 1, 5, 1 };
			int rightFingersUp[] = { 1, 5, 4, 5, 3, 5, 2, 5, 1, 5, 1, 5 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String leftPatternDown = "c3/16 c2 d c e c f c g c a c";
			String rightPatternDown = "e4/16 e3 f e g e a e b e c4 e3";
			int leftFingersDown[] = { 1, 5, 4, 5, 3, 5, 2, 5, 1, 5, 1, 5 };
			int rightFingersDown[] = { 5, 1, 2, 1, 3, 1, 4, 1, 5, 1, 5, 1 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};

			leftScores = guidoService.gmnToScores(leftPatternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(leftPatternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(rightPatternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(rightPatternDown), rightFingersDown, stepsDown));

			break;
		}
		case 31: {
			name = "38";
			id = 3800L;
			meter = "2/4";

			String patternUp = "c0/16 d e f g a b c1";
			int leftFingersUp[] = { 5, 4, 3, 2, 1, 3, 2, 1 };
			int rightFingersUp[] = { 1, 2, 3, 1, 2, 3, 4, 5 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

			String patternDown = "c3/16 b2 a2 g2 f2 e2 d2 c2";
			int leftFingersDown[] = { 1, 2, 3, 1, 2, 3, 4, 5 };
			int rightFingersDown[] = { 5, 4, 3, 2, 1, 3, 2, 1 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		case 32: {
			name = "50";
			id = 5000L;
			meter = "C";

			String patternUp = "{c0,e0} {d0,f0} {e0,g0} {d0,f0}";
			int leftFingersUp[] = { 5, 3, 4, 2, 3, 1, 4, 2 };
			int rightFingersUp[] = { 1, 3, 2, 4, 3, 5, 2, 4 };
			int stepsUp1[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int stepsUp2[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "{e2,g2} {d2,f2} {c2,e2} {d2,f2}";
			int leftFingersDown[] = { 3, 1, 4, 2, 5, 3, 4, 2 };
			int rightFingersDown[] = { 3, 5, 2, 4, 1, 3, 2, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp1);
			leftScores.addAll(guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp2));
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp1);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp2));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		
		case 33: {
			name = "50a";
			id = 5001L;
			meter = "C";

			String patternUp = "{c0,e} {d,f} {e,g}";
			int leftFingersUp[] = { 5, 3, 4, 2, 3, 1 };
			int rightFingersUp[] = { 1, 3, 2, 4, 3, 5 };
			int stepsUp[] = {0, 3, 6, 9, 12, 15, 18, 21};

			String patternDown1 = "{d3,f} {c,e} {b2,d3}";
			String patternDown2 = "{d0,f} {c,e} {d,f}";
			int leftFingersDown1[] = { 4, 2, 5, 3, 3, 1 };
			int leftFingersDown2[] = { 4, 2, 5, 3, 4, 2 };
			int rightFingersDown1[] = { 2, 4, 1, 3, 3, 5 };
			int rightFingersDown2[] = { 2, 4, 1, 3, 2, 4 };
			int stepsDown1[] = {0, -3, -6, -9, -12, -15, -18};
			int stepsDown2[] = {0};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftScores.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));

			break;
		}
		
		case 34: {
			name = "54";
			id = 5400L;
			meter = "C";

			String patternUp = "{c1,e} {d,f} {c,e} {d,f} {c,e} {d,f} {c,e} {d,f} {e,g} {d,f} {e,g} {d,f} {e,g} {d,f} {e,g} {d,f}";
			int leftFingersUp[] = { 5, 3, 4, 2, 5, 3, 4, 2, 5, 3, 4, 2, 5, 3, 4, 2, 3, 1, 4, 2, 3, 1, 4, 2, 3, 1, 4, 2, 3, 1, 4, 2};
			int rightFingersUp[] = { 1, 3, 2, 4, 1, 3, 2, 4, 1, 3, 2, 4, 1, 3, 2, 4, 3, 5, 2, 4, 3, 5, 2, 4, 3, 5, 2, 4, 3, 5, 2, 4};
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8};

			String patternDown = "{d2,f} {c,e} {d,f} {c,e} {d,f} {c,e} {d,f} {c,e} {b1,d2} {c,e} {b1,d2} {c,e} {b1,d2} {c,e} {b1,d2} {c,e}";
			int leftFingersDown[] = { 3, 1, 4, 2, 3, 1, 4, 2, 3, 1, 4, 2, 3, 1, 4, 2, 5, 3, 4, 2, 5, 3, 4, 2, 5, 3, 4, 2, 5, 3, 4, 2};
			int rightFingersDown[] = { 3, 5, 2, 4, 3, 5, 2, 4, 3, 5, 2, 4, 3, 5, 2, 4, 1, 3, 2, 4, 1, 3, 2, 4, 1, 3, 2, 4, 1, 3, 2, 4};   
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6};

			leftScores = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftScores.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
			
		}
		
		case 35: {
			name = "0";
			description = "Scale";
			id = 0L;

			String pattern = "c0 d e f g a b c1 d e f g a b c2 b1 a g f e d c b0 a g f e d";
			int leftFingers[] = { 5, 4, 3, 2, 1, 3, 2, 1, 4, 3, 2, 1, 3, 2, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 1, 2, 3, 4, 5 };
			int rightFingers[] = { 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 1, 2, 3, 4, 5, 4, 3, 2, 1, 3, 2, 1, 4, 3, 2, 1, 3, 2, 1 };

			leftScores = guidoService.gmnToScores(pattern, leftFingers);

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(pattern), rightFingers);

			break;
		}

		}

		Song song = new Song();
		song.setVoice(Hand.LEFT, leftScores);
		song.setVoice(Hand.RIGHT, rightScores);
		song.setVoice(Hand.BOTH, scoreService.mergeScores(leftScores, rightScores));
		song.setName("No. " + name);
		if (!description.isEmpty()) {
			song.setDescription(description);
		}
		song.setId(id);
		//		song.meter = meter;

		return song;
	}
}