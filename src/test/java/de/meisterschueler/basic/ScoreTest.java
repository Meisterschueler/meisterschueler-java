package de.meisterschueler.basic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.math3.fraction.Fraction;
import org.junit.Test;

public class ScoreTest {
	@Test
	public void scoreCopyTest() {
		Score score = new Score();
		Score copy = new Score(score);
		
		assertTrue( score != copy );
		assertTrue( score.getMeasure() != copy.getMeasure() );
		assertTrue( score.getPosition() != copy.getPosition() );
	}
	
	@Test
	public void scoreCompareTest() {
		Score scoreOne = new Score();
		scoreOne.setPosition(new Fraction(1, 4));
		scoreOne.setNatural(10);
		
		Score scoreTwo = new Score();
		scoreTwo.setPosition(new Fraction(2, 4));
		scoreTwo.setNatural(10);
		
		assertEquals( -1, scoreOne.compareTo(scoreTwo) );
		
		Score scoreThree = new Score();
		scoreThree.setPosition(new Fraction(2,4));
		scoreThree.setNatural(8);
		
		assertEquals( 1, scoreTwo.compareTo(scoreThree) );
	}
}
