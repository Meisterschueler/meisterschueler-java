package de.meisterschueler.alignment;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.meisterschueler.alignment.NeedlemanWunsch;


public class NeedlemanWunschTest {

	@Test
	public void emptySequencesTest() {
		String alignment = NeedlemanWunsch.getAlignments("", "AAA");
		assertEquals("eee", alignment);
		
		alignment = NeedlemanWunsch.getAlignments("AAA", "");
		assertEquals("xxx", alignment);
		
		alignment = NeedlemanWunsch.getAlignments("", "");
		assertEquals("", alignment);
	}
	
	@Test
	public void AAA_AAA_mmm_Test() {
		String alignment = NeedlemanWunsch.getAlignments("AAA", "AAA");
		assertEquals( "mmm", alignment);
	}
	
	@Test
	public void AAA_ABA_mwm_Test() {
		String alignment = NeedlemanWunsch.getAlignments("AAA", "ABA");
		assertEquals( "mwm", alignment);
	}
	
	@Test
	public void AAA_AZA_mexm_Test() {
		String alignment = NeedlemanWunsch.getAlignments("AAA", "AZA");
		//assertEquals( "mexm", alignment);	// 1. Wahl
		assertEquals( "memx", alignment);	// 2. Wahl (auch nicht falsch, aber unschön)
	}
	
	@Test
	public void ABC_AZC_mexm_Test() {
		String alignment = NeedlemanWunsch.getAlignments("ABC", "AZC");
		assertEquals( "mwm", alignment);
	}
	
	@Test
	public void AAA_AABA_mmem_Test() {
		String alignment = NeedlemanWunsch.getAlignments("AAA", "AABA");
		assertEquals( "mmem", alignment);
	}
	
	@Test
	public void AABA_AAA_mmxm_Test() {
		String alignment = NeedlemanWunsch.getAlignments("AABA", "AAA");
		assertEquals( "mmxm", alignment);
	}

	@Test
	public void complexTest() {
		String alignment = NeedlemanWunsch.getAlignments("BCEFGH", "ABCDFG");
		assertEquals( "emmwmmx", alignment );
	}
	
	@Test
	public void matchFirstTest() {
		String alignment = NeedlemanWunsch.getAlignments("AAA", "AAAA");
		assertEquals( "mmme", alignment);
		
		alignment = NeedlemanWunsch.getAlignments("AAAA", "AAA");
		assertEquals( "mmmx", alignment);
	}
}
