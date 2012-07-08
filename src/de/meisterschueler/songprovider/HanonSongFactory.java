package de.meisterschueler.songprovider;

import java.util.ArrayList;
import java.util.List;

import de.meisterschueler.basic.Hand;
import de.meisterschueler.basic.Score;
import de.meisterschueler.basic.Song;
import de.meisterschueler.service.GuidoService;
import de.meisterschueler.service.GuidoServiceImpl;

public class HanonSongFactory extends SongFactory {

	private GuidoService guidoService = new GuidoServiceImpl();

	private List<Song> songs = new ArrayList<Song>();

	public HanonSongFactory() {
		for (int i = 0; i <= 34; i++) {
			songs.add(getNo(i));
		}

		songBook.setName("The Virtuoso Pianist");
		songBook.setComposer("C. L. Hanon");
		songBook.setSongs(songs);
	}

	private Song getNo(int no) {
		List<Score> leftHand = null;
		List<Score> rightHand = null;
		String description = "";
		String meter = "2/4";
		String name = "";
		switch (no) {
		case 0: {
			name = "1";
			description = "";

			String patternUp = "c0 e f g a g f e";
			int leftFingersUp[] = { 5, 4, 3, 2, 1, 2, 3, 4 };
			int rightFingersUp[]  = { 1, 2, 3, 4, 5, 4, 3, 2 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown    = "g2 e d c b1 c2 d e";
			int leftFingersDown[]  = { 1, 2, 3, 4, 5, 4, 3, 2 };
			int rightFingersDown[] = { 5, 4, 3, 2, 1, 2, 3, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 1: {
			name = "2";
			description = "";

			String patternUp = "c0 e a g f g f e";
			int leftFingersUp[] = { 5, 3, 1, 2, 3, 2, 3, 4 };
			int rightFingersUp[] = { 1, 2, 5, 4, 3, 4, 3, 2 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 d b1 c2 d c d e";
			int leftFingersDown[] = { 1, 3, 5, 4, 3, 4, 3, 2 };
			int rightFingersDown[] = { 5, 2, 1, 2, 3, 2, 3, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 2: {
			name = "3";
			description = "";

			String patternUp = "c0 e a g f e f g";
			int leftFingersUp[] = { 5, 3, 1, 2, 3, 4, 3, 2 };
			int rightFingersUp[] = { 1, 2, 5, 4, 3, 2, 3, 4 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 d b1 c2 d e d c";
			int leftFingersDown[] = { 1, 3, 5, 4, 3, 2, 3, 4 };
			int rightFingersDown[] = { 5, 2, 1, 2, 3, 4, 3, 2 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 3: {
			name = "4";
			description = "Special exercise for the 3rd, 4th and 5th fingers of the hand.";

			String patternUp = "c0 d c e a g f e";
			int leftFingersUp[] = { 5, 4, 5, 3, 1, 2, 3, 4 };
			int rightFingersUp[] = { 1, 2, 1, 2, 5, 4, 3, 2 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 f g d b1 c2 d e";
			int leftFingersDown[] = { 1, 2, 1, 3, 5, 4, 3, 2 };
			int rightFingersDown[] = { 5, 4, 5, 2, 1, 2, 3, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 4: {
			name = "5";
			description = "We repeat, that the fingers should be lifted high, and with precision, until this entire volm is mastered.";

			String patternUp = "c0 a g a f g e f";
			int leftFingersUp[] = { 5, 1, 2, 1, 3, 2, 4, 3 };
			int rightFingersUp[] = { 1, 5, 4, 5, 3, 4, 2, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "c2 d c e d f e g";
			int leftFingersDown[] = { 5, 4, 5, 3, 4, 2, 3, 1 };
			int rightFingersDown[] = { 1, 2, 1, 3, 2, 4, 3, 5 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));
			break;
		}

		case 5: {
			name = "6";
			description = "To obtain the good results which we promise those who study this work, it is indispensable to play daily, at least once, the exercise already learned.";

			String patternUp1 = "c0 a g a f a e a";
			String patternUp2 = "c0 a g a f a e d";
			int leftFingersUp1[] = { 5, 1, 2, 1, 3, 1, 4, 1 };
			int leftFingersUp2[] = { 5, 1, 2, 1, 3, 1, 4, 5 };
			int rightFingersUp1[] = { 1, 5, 4, 5, 3, 5, 2, 5 };
			int rightFingersUp2[] = { 1, 5, 4, 5, 3, 5, 2, 1 };
			int stepsUp1[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
			int stepsUp2[] = {13};

			String patternDown1 = "g2 b1 c2 b1 d2 b1 e2 b1";
			String patternDown2 = "g0 b-1 c0 b-1 d0 b-1 e0 d";
			int leftFingersDown1[] = { 1, 5, 4, 5, 3, 5, 2, 5 };
			int leftFingersDown2[] = { 1, 5, 4, 5, 3, 5, 2, 3 };
			int rightFingersDown1[] = { 5, 1, 2, 1, 3, 1, 4, 1 };
			int rightFingersDown2[] = { 5, 1, 2, 1, 3, 1, 4, 3 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};
			int stepsDown2[] = {-13};

			leftHand = guidoService.gmnToScores(patternUp1, leftFingersUp1, stepsUp1);
			leftHand.addAll(guidoService.gmnToScores(patternUp2, leftFingersUp2, stepsUp2));
			leftHand.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftHand.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp1), rightFingersUp1, stepsUp1);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternUp2), rightFingersUp2, stepsUp2));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));

			break;
		}

		case 6: {
			name = "7";
			description = "Exercise of the greatest importance for the 3rd, 4th and 5th fingers.";

			String patternUp = "c0 e d f e g f e";
			int leftFingersUp[] = { 5, 3, 4, 2, 3, 1, 2, 3 };
			int rightFingersUp[] = { 1, 3, 2, 4, 3, 5, 4, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 e f d e c d e";
			int leftFingersDown[] = { 1, 3, 2, 4, 3, 5, 4, 3 };
			int rightFingersDown[] = { 5, 3, 4, 2, 3, 1, 3, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 7: {
			name = "8";
			description = "Very important exercise for all five fingers.";

			String patternUp = "c0 e g a f g e f";
			int leftFingersUp[] = { 5, 4, 2, 1, 3, 2, 4, 3 };
			int rightFingersUp[] = { 1, 2, 4, 5, 3, 4, 2, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 e c b1 d2 c e d";
			int leftFingersDown[] = { 1, 2, 4, 5, 3, 4, 2, 3 };
			int rightFingersDown[] = { 5, 4, 2, 1, 3, 2, 4, 3};
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 8: {
			name = "9";
			description = "Extension of the 4th and 5th, and general finger-exercise.";

			String patternUp = "c0 e f e g f a g";
			int leftFingersUp[] = { 5, 4, 3, 4, 2, 3, 1, 2 };
			int rightFingersUp[] = { 1, 2, 3, 2, 4, 3, 5, 4 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown1 = "g2 e d e c d b1 c2";
			String patternDown2 = "g2 e d e c d c d";
			int leftFingersDown1[] = { 1, 2, 3, 2, 4, 3, 5, 4 };
			int leftFingersDown2[] = { 1, 2, 3, 2, 4, 3, 4, 3 };
			int rightFingersDown1[] = { 5, 4, 3, 4, 2, 3, 1, 2 };
			int rightFingersDown2[] = { 5, 4, 3, 4, 2, 3, 2, 3 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};
			int stepsDown2[] = {-13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftHand.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));

			break;
		}

		case 9: {
			name = "10";
			description = "Preparation for the trill, for the 3rd and 4th fingers of the left hand in ascending (1); and for the 3rd and 4th of the right, descending (2).";

			String patternUp = "c0 a g f e f e f";
			int leftFingersUp[] = { 5, 1, 2, 3, 4, 3, 4, 3 };
			int rightFingersUp[] = { 1, 5, 4, 3, 2, 3, 2, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 b1 c2 d e d e d";
			int leftFingersDown[] = { 1, 5, 4, 3, 2, 3, 2, 3 };
			int rightFingersDown[] = { 5, 1, 2, 3, 4, 3, 4, 3 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 10: {
			name = "11";
			description = "Another preparation for the trill, for the 4th and 5th fingers.";

			String patternUp = "c0 e a g a g f g";
			int leftFingersUp[] = { 5, 3, 1, 2, 1, 2, 3, 2 };
			int rightFingersUp[] = { 1, 2, 5, 4, 5, 4, 3, 4 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 d b1 c2 b1 c2 d c";
			int leftFingersDown[] = { 1, 3, 5, 4, 5, 4, 3, 2 };
			int rightFingersDown[] = { 5, 2, 1, 2, 1, 2, 3, 2 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));
			break;
		}

		case 11: {
			name = "12";
			description = "Extension of 1-5, and exercise for 3-4-5.";

			String patternUp1  = "g0 c e d c d e c";
			String patternUp2  = "a0 c e d c d e c";
			String patternUp3  = "a0 c e d c d e f";
			int leftFingersUp12[]   = { 1, 5, 3, 4, 5, 4, 3, 5 };
			int leftFingersUp3[]    = { 1, 5, 3, 4, 5, 4, 3, 2 };
			int rightFingersUp12[]  = { 5, 1, 3, 2, 1, 2, 3, 1 };
			int rightFingersUp3[]   = { 5, 1, 3, 2, 1, 2, 3, 4 };
			int stepsUp1[] = {0};
			int stepsUp2[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
			int stepsUp3[] = {13};

			String patternDown1    = "c2 a f g a g f a";
			String patternDown2    = "c0 g e f g f e f";
			int leftFingersDown[]  = { 1, 5, 3, 4, 5, 4, 3, 5 };
			int rightFingersDown[] = { 5, 1, 3, 2, 1, 2, 3, 4 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};
			int stepsDown2[] = {0};

			leftHand = guidoService.gmnToScores(patternUp1, leftFingersUp12, stepsUp1);
			leftHand.addAll(guidoService.gmnToScores(patternUp2, leftFingersUp12, stepsUp2));
			leftHand.addAll(guidoService.gmnToScores(patternUp3, leftFingersUp3, stepsUp3));
			leftHand.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown, stepsDown1));
			leftHand.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown, stepsDown2));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp1), rightFingersUp12, stepsUp1);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternUp2), rightFingersUp12, stepsUp2));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternUp3), rightFingersUp3, stepsUp3));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown1), rightFingersDown, stepsDown1));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown2), rightFingersDown, stepsDown2));

			break;
		}

		case 12: {
			name = "13";
			description = "(3-4-5)";

			String patternUp = "e0 c f d g e f g";
			int leftFingersUp[] = { 3, 5, 2, 4, 1, 3, 2, 1 };
			int rightFingersUp[] = { 3, 1, 4, 2, 5, 3, 4, 5 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "e2 g d f e c d e";
			int leftFingersDown[] = { 3, 1, 4, 2, 3, 5, 4, 3 };
			int rightFingersDown[] = { 3, 5, 2, 4, 3, 1, 3, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 13: {
			name = "14";
			description = "(3-4) Another preparation for the trill, for the 3rd and 4th fingers.";

			String patternUp = "c0 d f e f e g f";
			int leftFingersUp[] = { 5, 4, 2, 3, 2, 3, 1, 2 };
			int rightFingersUp[] = { 1, 2, 4, 3, 4, 3, 5, 4 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 f d e d e c d";
			int leftFingersDown[] = { 1, 2, 4, 3, 4, 3, 5, 4 };
			int rightFingersDown[] = { 5, 4, 2, 3, 2, 3, 1, 3 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 14: {
			name = "15";
			description = "Extension of 1-2, and exercise for all 5 fingers.";

			String patternUp1 = "c0 e d f e g f a";
			String patternUp2 = "c0 e d f e g f g";
			int leftFingersUp1[] = { 5, 3, 4, 2, 3, 1, 2, 1 };
			int leftFingersUp2[] = { 5, 3, 4, 2, 3, 1, 3, 2 };
			int rightFingersUp1[] = { 1, 2, 1, 3, 2, 4, 3, 5 };
			int rightFingersUp2[] = { 1, 2, 1, 3, 2, 4, 3, 4 };
			int stepsUp1[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
			int stepsUp2[] = {13};

			String patternDown1 = "g2 e f d e c d b1";
			String patternDown2 = "g2 e f d e c d c";
			int leftFingersDown1[] = { 1, 2, 1, 3, 2, 4, 3, 5 };
			int leftFingersDown2[] = { 1, 2, 1, 3, 2, 4, 3, 4 };
			int rightFingersDown1[] = { 5, 3, 4, 2, 3, 1, 2, 1 };
			int rightFingersDown2[] = { 5, 3, 4, 2, 3, 1, 3, 2 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10,- 11, -12};
			int stepsDown2[] = {-13};

			leftHand = guidoService.gmnToScores(patternUp1, leftFingersUp1, stepsUp1);
			leftHand.addAll(guidoService.gmnToScores(patternUp2, leftFingersUp2, stepsUp2));
			leftHand.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftHand.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp1), rightFingersUp1, stepsUp1);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternUp2), rightFingersUp2, stepsUp2));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));

			break;
		}

		case 15: {
			name = "16";
			description = "Extension of 3-5, and exercise for 3-4-5.";

			String patternUp = "c0 e0 d0 e0 a0 g0 f0 g0";
			int leftFingersUp[] = { 5, 3, 4, 3, 1, 2, 3, 2 };
			int rightFingersUp[] = { 1, 3, 2, 3, 5, 4, 3, 4 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 d2 e2 d2 b1 c2 d2 c2";
			int leftFingersDown[] = { 1, 3, 2, 3, 5, 4, 3, 4 };
			int rightFingersDown[] = { 1, 3, 2, 3, 5, 4, 3, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 16: {
			name = "17";
			description = "Extension of 1-2, 2-4, 4-5, and exercise for 3-4-5.";

			String patternUp1  = "c0 e0 a0 g0 b0 a0 g0 a0";
			String patternUp2  = "c0 e0 a0 g0 b0 a0 g0 f0";
			int leftFingersUp1[] = { 5, 4, 2, 3, 1, 2, 3, 2 };
			int leftFingersUp2[] = { 5, 4, 2, 3, 1, 2, 3, 4 };
			int rightFingersUp1[] = { 1, 2, 4, 3, 5, 4, 3, 4 };
			int rightFingersUp2[] = { 1, 2, 4, 3, 5, 4, 3, 2 };
			int stepsUp1[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
			int stepsUp2[] = {13};

			String patternDown1    = "g0 d0 b-1 c0 a3 b-1 c0 a3";
			String patternDown2    = "g0 d0 b-1 c0 a3 b-1 c0 b-1";
			int leftFingersDown1[]  = { 1, 2, 4, 3, 5, 4, 3, 5 };
			int leftFingersDown2[]  = { 1, 2, 4, 3, 5, 4, 3, 5 };
			int rightFingersDown1[] = { 5, 3, 2, 3, 1, 2, 3, 1 };
			int rightFingersDown2[] = { 5, 3, 2, 3, 1, 2, 3, 1 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11};
			int stepsDown2[] = {-12};

			leftHand = guidoService.gmnToScores(patternUp1, leftFingersUp1, stepsUp1);
			leftHand.addAll(guidoService.gmnToScores(patternUp2, leftFingersUp2, stepsUp2));
			leftHand.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftHand.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp1), rightFingersUp1, stepsUp1);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternUp2), rightFingersUp2, stepsUp2));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));
			break;
		}

		case 17: {
			name = "18";
			description = "(1-2-3-4-5)";

			String patternUp = "c0 d f e g f d e";
			int leftFingersUp[] = { 5, 4, 2, 3, 1, 2, 4, 3 };
			int rightFingersUp[] = { 1, 2, 4, 3, 5, 4, 2, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 f d e c d f e";
			int leftFingersDown[] = { 1, 2, 4, 3, 5, 4, 2, 3 };
			int rightFingersDown[] = { 5, 4, 2, 3, 1, 2, 4, 3 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -10, -11, -12, -13, -14};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 18: {
			name = "19";
			description = "(1-2-3-4-5)";

			String patternUp = "c0 a f g a f e g";
			int leftFingersUp[] = { 5, 1, 3, 2, 1, 3, 4, 2 };
			int rightFingersUp[] = { 1, 5, 3, 4, 5, 3, 2, 4 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 b1 d2 c b1 d2 e c";
			int leftFingersDown[] = { 1, 5, 3, 4, 5, 3, 2, 4 };
			int rightFingersDown[] = { 5, 1, 3, 2, 1, 3, 4, 2 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}

		case 19: {
			name = "20";
			description = "Extension of 2-4, 4-5, and exercise for 2-3-4.";

			String patternUp1 = "e0 g c1 e c b0 c1 a0";
			String patternUp2 = "e0 g c1 e c b0 c1 g0";
			int leftFingersUp[] = { 5, 4, 2, 1, 2, 3, 2, 4 };
			int rightFingersUp[] = { 1, 2, 4, 5, 4, 3, 4, 2 };
			int stepsUp1[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
			int stepsUp2[] = {14};

			String patternDown1 = "e1 c g0 e g f g e";
			String patternDown2 = "e1 c g0 e g f g f";
			int leftFingersDown1[] = { 1, 2, 4, 5, 3, 4, 3, 5 };
			int leftFingersDown2[] = { 1, 2, 4, 5, 3, 4, 3, 4 };
			int rightFingersDown1[] = { 5, 4, 2, 1, 3, 2, 3, 1 };
			int rightFingersDown2[] = { 5, 4, 2, 1, 3, 2, 3, 2 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};
			int stepsDown2[] = {-14};

			leftHand = guidoService.gmnToScores(patternUp1, leftFingersUp, stepsUp1);
			leftHand.addAll(guidoService.gmnToScores(patternUp2, leftFingersUp, stepsUp2));
			leftHand.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftHand.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp1), rightFingersUp, stepsUp1);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternUp2), rightFingersUp, stepsUp2));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));

			break;
		}
		case 20: {
			name = "21";
			meter = "4/4";

			String patternUp = "c0 d e d c e f g a g f g a g f e";
			int leftFingersUp[] = { 5, 4, 3, 4, 5, 4, 3, 2, 1, 2, 3, 2, 1, 2, 3, 4 };
			int rightFingersUp[] = { 1, 2, 3, 2, 1, 2, 3, 4, 5, 4, 3, 4, 5, 4, 3, 2 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown1 = "g2 f e f g e d c b1 c2 d c b1 c2 d e";
			String patternDown2 = "a0 g f g a f e d c d e d c d e d";
			int leftFingersDown1[] = { 1, 2, 3, 2, 1, 2, 3, 4, 5, 4, 3, 4, 5, 4, 3, 2 };
			int leftFingersDown2[] = { 1, 2, 3, 2, 1, 2, 3, 4, 5, 4, 3, 4, 5, 4, 3, 4 };
			int rightFingersDown1[] = { 5, 4, 3, 4, 5, 4, 3, 2, 1, 2, 3, 2, 1, 2, 3, 4 };
			int rightFingersDown2[] = { 5, 4, 3, 4, 5, 4, 3, 2, 1, 2, 3, 2, 1, 2, 3, 2 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};
			int stepsDown2[] = {0};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftHand.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));
			break;
		}
		case 21: {
			name = "22";
			meter = "C";

			String patternUp = "c0 e0 d0 e0 c0 e0 f0 g0 a0 f0 g0 f0 a0 g0 f0 e0";
			int leftFingersUp[] = { 5, 3, 2, 3, 5, 4, 3, 2, 1, 3, 2, 3, 1, 2, 3, 4 };
			int rightFingersUp[] = { 1, 3, 2, 3, 1, 2, 3, 4, 5, 3, 4, 3, 5, 4, 3, 2 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown1 = "g2 e2 f2 e2 g2 e2 d2 c2 b1 d2 c2 d2 b1 c2 d2 e2";
			String patternDown2 = "a0 f0 g0 f0 a0 f0 e0 d0 c0 e0 d0 e0 c0 f0 e0 d0";
			int leftFingersDown1[] = { 1, 3, 2, 3, 1, 2, 3, 4, 5, 3, 4, 3, 5, 4, 3, 2 };
			int leftFingersDown2[] = { 1, 3, 2, 3, 1, 2, 3, 4, 5, 3, 4, 3, 5, 2, 3, 4 };
			int rightFingersDown1[] = { 5, 3, 4, 3, 5, 4, 3, 2, 1, 3, 2, 3, 1, 2, 3, 4 };
			int rightFingersDown2[] = { 5, 3, 4, 3, 5, 4, 3, 2, 1, 3, 2, 3, 1, 4, 3, 2 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};
			int stepsDown2[] = {0};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftHand.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));
			break;
		}
		case 22: {
			name = "23";
			meter = "C";

			String patternUp = "c0 d0 e0 d0 c0 d0 e0 d0 c0 a0 g0 f0 e0 f0 g0 f0";
			int leftFingersUp[] = { 5, 4, 3, 4, 5, 4, 3, 4, 5, 1, 2, 3, 4, 3, 2, 3 };
			int rightFingersUp[] = { 1, 2, 3, 2, 1, 2, 3, 2, 1, 5, 4, 3, 2, 3, 4, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 f2 e2 f2 g2 f2 e2 f2 g2 b1 c2 d2 e2 d2 c2 d2";
			int leftFingersDown[] = { 1, 2, 3, 2, 1, 2, 3, 2, 1, 5, 4, 3, 2, 3, 4, 3 };
			int rightFingersDown[] = { 5, 4, 3, 4, 5, 4, 3, 4, 5, 1, 2, 3, 4, 3, 2, 3 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		case 23: {
			name = "24";
			meter = "C";

			String patternUp = "e0 d0 e0 c0 e0 d0 e0 c0 e0 d0 e0 c0 a0 f0 g0 e0";
			int leftFingersUp[] = { 3, 4, 3, 5, 3, 4, 3, 5, 3, 4, 3, 5, 1, 3, 2, 4 };
			int rightFingersUp[] = { 3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 3, 1, 5, 3, 4, 2 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "e2 g2 f2 g2 e2 g2 f2 g2 e2 g2 f2 g2 b1 d2 c2 e2";
			int leftFingersDown[] = { 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 2, 1, 5, 3, 4, 2 };
			int rightFingersDown[] = { 3, 5, 4, 5, 3, 5, 4, 5, 3, 5, 4, 5, 1, 3, 2, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		case 24: {
			name = "25";
			meter = "C";

			String patternUp = "c0 d0 e0 c0 d0 e0 f0 d0 e0 f0 g0 f0 e0 g0 f0 e0";
			int leftFingersUp1[] = { 5, 4, 3, 5, 4, 3, 2, 4, 3, 2, 1, 2, 3, 1, 3, 4 };
			int leftFingersUp2[] = { 5, 4, 3, 5, 4, 3, 2, 4, 3, 2, 1, 2, 3, 1, 3, 4 };
			int rightFingersUp1[] = { 1, 2, 3, 1, 2, 3, 4, 2, 3, 4, 5, 4, 3, 5, 4, 3 };
			int rightFingersUp2[] = { 1, 2, 3, 1, 2, 3, 4, 2, 3, 4, 5, 4, 3, 5, 3, 2 };
			int stepsUp1[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
			int stepsUp2[] = {13};

			String patternDown = "g2 f2 e2 g2 f2 e2 d2 f2 e2 d2 c2 e2 d2 c2 d2 e2";
			int leftFingersDown1[] = { 1, 2, 3, 1, 2, 3, 4, 2, 3, 4, 5, 3, 4, 5, 4, 3 };
			int leftFingersDown2[] = { 1, 2, 3, 1, 2, 3, 4, 2, 3, 4, 5, 3, 4, 5, 3, 2 };
			int rightFingersDown[] = { 5, 4, 3, 5, 4, 3, 2, 4, 3, 2, 1, 3, 2, 1, 3, 4 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};
			int stepsDown2[] = {-13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp1, stepsUp1);
			leftHand.addAll(guidoService.gmnToScores(patternUp, leftFingersUp2, stepsUp2));
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown1, stepsDown1));
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown2, stepsDown2));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp1, stepsUp1);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp2, stepsUp2));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown1));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown2));

			break;
		}
		case 25: {
			name = "26";
			meter = "C";

			String patternUp = "e0 f0 g0 e0 d0 e0 f0 d0 c0 d0 e0 c0 a0 g0 a0 g0";
			int leftFingersUp[] = { 3, 2, 1, 3, 4, 3, 2, 4, 5, 4, 3, 5, 1, 2, 1, 2 };
			int rightFingersUp[] = { 3, 4, 5, 3, 2, 3, 4, 2, 1, 2, 3, 1, 5, 4, 5, 4 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 e2 f2 g2 f2 d2 e2 f2 e2 c2 d2 e2 d2 c2 d2 c2";
			int leftFingersDown[] = { 1, 3, 2, 1, 2, 4, 3, 2, 3, 5, 4, 3, 4, 3, 4, 3 };
			int rightFingersDown[] = { 5, 3, 4, 5, 4, 2, 3, 4, 3, 1, 2, 3, 2, 1, 2, 1 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		case 26: {
			name = "27";
			meter = "C";

			String patternUp = "e0 f0 d0 e0 c0 e0 f0 g0 a0 g0 a0 g0 a0 g0 f0 e0";
			int leftFingersUp[] = { 3, 2, 4, 2, 5, 4, 3, 2, 1, 2, 1, 2, 1, 2, 3, 4 };
			int rightFingersUp[] = { 3, 4, 2, 3, 1, 2, 3, 4, 5, 4, 5, 4, 5, 4, 3, 2 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 f2 g2 e2 f2 e2 d2 c2 b1 c2 b1 c2 b1 c2 d2 e2";
			int leftFingersDown[] = { 1, 2, 1, 3, 1, 2, 3, 4, 5, 4, 5, 4, 5, 4, 3, 2 };
			int rightFingersDown[] = { 5, 4, 5, 3, 5, 4, 3, 2, 1, 2, 1, 2, 1, 2, 3, 4 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		case 27: {
			name = "28";
			meter = "C";

			String patternUp = "c0 e0 d0 e0 c0 e0 d0 e0 c0 a0 g0 a0 f0 g0 e0 f0";
			int leftFingersUp[] = { 5, 3, 4, 3, 5, 3, 4, 3, 5, 1, 2, 1, 3, 2, 4, 3 };
			int rightFingersUp[] = { 1, 3, 2, 3, 1, 3, 2, 3, 1, 5, 4, 5, 3, 4, 2, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "g2 e2 f2 e2 g2 e2 f2 e2 g2 b1 c2 b1 d2 c2 e2 d2";
			int leftFingersDown[] = { 1, 3, 2, 3, 1, 3, 2, 3, 1, 5, 4, 5, 3, 4, 2, 3 };
			int rightFingersDown[] = { 5, 3, 4, 3, 5, 3, 4, 3, 5, 1, 2, 1, 3, 2, 4, 3 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		case 28: {
			name = "29";
			meter = "C";

			String patternUp = "c0 d c e d e d f e f e g f g f g";
			int leftFingersUp1[] = { 5, 4, 5, 3, 4, 3, 4, 2, 3, 2, 3, 1, 2, 1, 2, 1 };
			int leftFingersUp2[] = { 5, 4, 5, 3, 4, 3, 4, 2, 3, 2, 3, 1, 3, 2, 3, 2 };
			int rightFingersUp1[] = { 1, 2, 1, 3, 2, 3, 2, 4, 3, 4, 3, 5, 4, 5, 4, 5 };
			int rightFingersUp2[] = { 1, 2, 1, 3, 2, 3, 2, 4, 3, 4, 3, 5, 4, 5, 3, 4 };
			int stepsUp1[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
			int stepsUp2[] = {13};

			String patternDown1 = "g2 f g e f e f d e d e c d e d e";
			String patternDown2 = "a0 g a f g f g e f e f d e f e d";
			int leftFingersDown1[] = { 1, 2, 1, 3, 2, 3, 2, 4, 3, 4, 3, 5, 4, 3, 4, 3 };
			int leftFingersDown2[] = { 1, 2, 1, 3, 2, 3, 2, 4, 3, 4, 3, 5, 3, 2, 3, 4 };
			int rightFingersDown1[] = { 5, 4, 5, 3, 4, 3, 4, 2, 3, 2, 3, 1, 2, 3, 2, 3 };
			int rightFingersDown2[] = { 5, 4, 5, 3, 4, 3, 4, 2, 3, 2, 3, 1, 3, 4, 3, 2 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};
			int stepsDown2[] = {0};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp1, stepsUp1);
			leftHand.addAll(guidoService.gmnToScores(patternUp, leftFingersUp2, stepsUp2));
			leftHand.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftHand.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp1, stepsUp1);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp2, stepsUp2));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));
			break;
		}
		case 29: {
			name = "30";
			meter = "C";

			String patternUp = "c0 d0 c0 d0 c0 d0 c0 e0 a0 g0 a0 g0 a0 g0 a0 f0";
			int leftFingersUp[] = { 5, 4, 5, 4, 5, 4, 5, 3, 1, 2, 1, 2, 1, 2, 1, 3 };
			int rightFingersUp[] = { 1, 2, 1, 2, 1, 2, 1, 3, 5, 4, 5, 4, 5, 4, 5, 3 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown1 = "g2 f2 g2 f2 g2 f2 g2 e2 b1 c2 b1 c2 b1 c2 b1 d2";
			String patternDown2 = "g2 f2 g2 f2 g2 f2 g2 e2 b1 c2 b1 c2 b1 c2 b1 c2";
			int leftFingersDown1[] = { 1, 2, 1, 2, 1, 2, 1, 3, 5, 4, 5, 4, 5, 4, 5, 3 };
			int leftFingersDown2[] = { 1, 2, 1, 2, 1, 2, 1, 3, 5, 4, 5, 4, 5, 4, 5, 4 };
			int rightFingersDown1[] = { 5, 4, 5, 4, 5, 4, 5, 3, 1, 2, 1, 2, 1, 2, 1, 3 };
			int rightFingersDown2[] = { 5, 4, 5, 4, 5, 4, 5, 3, 1, 2, 1, 2, 1, 2, 1, 2 };
			int stepsDown1[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};
			int stepsDown2[] = {-13};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftHand.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));

			break;
		}
		case 30: {  // Das ist erst einmal nur f�r die rechte Hand korrekt
			name = "31";
			meter = "3/4";

			String patternUp = "e-1 e0 d0 e0 c0 e0 b-1 e0 a-1 e0 g-1 e0";
			int leftFingersUp[] = { 5, 1, 2, 1, 3, 1, 4, 1, 5, 1, 5, 1 };
			int rightFingersUp[] = { 1, 5, 4, 5, 3, 5, 2, 5, 1, 5, 1, 5 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

			String patternDown = "e2 e1 f1 e1 g1 e1 a1 e1 b1 e1 c2 e1";
			int leftFingersDown[] = { 1, 5, 4, 5, 3, 5, 2, 5, 1, 5, 1, 5 };
			int rightFingersDown[] = { 5, 1, 2, 1, 3, 1, 4, 1, 5, 1, 5, 1 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		case 31: {
			name = "38";
			meter = "2/4";

			String patternUp = "c0 d0 e0 f0 g0 a0 b0 c1";
			int leftFingersUp[] = { 5, 4, 3, 2, 1, 3, 2, 1 };
			int rightFingersUp[] = { 1, 2, 3, 1, 2, 3, 4, 5 };
			int stepsUp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

			String patternDown = "c7 b2 a2 g2 f2 e2 d2 c2";
			int leftFingersDown[] = { 1, 2, 3, 1, 2, 3, 4, 5 };
			int rightFingersDown[] = { 5, 4, 3, 2, 1, 3, 2, 1 };
			int stepsDown[] = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14};

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		case 32: {
			name = "50";
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

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp1);
			leftHand.addAll(guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp2));
			leftHand.addAll(guidoService.gmnToScores(patternDown, leftFingersDown, stepsDown));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp1);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp2));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown), rightFingersDown, stepsDown));

			break;
		}
		case 33: {
			name = "50a";
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

			leftHand = guidoService.gmnToScores(patternUp, leftFingersUp, stepsUp);
			leftHand.addAll(guidoService.gmnToScores(patternDown1, leftFingersDown1, stepsDown1));
			leftHand.addAll(guidoService.gmnToScores(patternDown2, leftFingersDown2, stepsDown2));

			rightHand = guidoService.gmnToScores(oneOctaveUp(patternUp), rightFingersUp, stepsUp);
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown1), rightFingersDown1, stepsDown1));
			rightHand.addAll(guidoService.gmnToScores(oneOctaveUp(patternDown2), rightFingersDown2, stepsDown2));

			break;
		}
		case 34: {
			name = "0";
			description = "Scale";

			String pattern = "c0 d e f g a b c1 d e f g a b c2 b1 a g f e d c b0 a g f e d";
			int leftFingers[] = { 5, 4, 3, 2, 1, 3, 2, 1, 4, 3, 2, 1, 3, 2, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 1, 2, 3, 4, 5 };
			int rightFingers[] = { 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 1, 2, 3, 4, 5, 4, 3, 2, 1, 3, 2, 1, 4, 3, 2, 1, 3, 2, 1 };

			leftHand = guidoService.gmnToScores(pattern, leftFingers);

			rightHand = guidoService.gmnToScores(oneOctaveUp(pattern), rightFingers);

			break;
		}

		}

		Song song = new Song();
		song.setVoice(Hand.LEFT, leftHand);
		song.setVoice(Hand.RIGHT, rightHand);
		song.setName("No. " + name);
		if (!description.isEmpty()) {
			song.setDescription(description);
		}
		//		song.meter = meter;

		return song;
	}

	private String oneOctaveUp(String gmnString) {
		return guidoService.transposeGmn("c0", "c1", gmnString);
	}
}