package de.meisterschueler.alignment;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.meisterschueler.alignment.NeedlemanWunsch;


public class NeedlemanWunschTest {

	@Test
	public void emptySequencesTest() {
		String alignment = NeedlemanWunsch.getAlignments("", "AAA");
		assertEquals("iii", alignment);
		
		alignment = NeedlemanWunsch.getAlignments("AAA", "");
		assertEquals("ddd", alignment);
		
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
	public void AAA_AZA_midm_Test() {
		String alignment = NeedlemanWunsch.getAlignments("AAA", "AZA");
		//assertEquals( "midm", alignment);	// 1. Wahl
		assertEquals( "mimd", alignment);	// 2. Wahl (auch nicht falsch, aber unschön)
	}
	
	@Test
	public void ABC_AZC_mwm_Test() {
		String alignment = NeedlemanWunsch.getAlignments("ABC", "AZC");
		assertEquals( "mwm", alignment);
	}
	
	@Test
	public void AAA_AABA_mmim_Test() {
		String alignment = NeedlemanWunsch.getAlignments("AAA", "AABA");
		assertEquals( "mmim", alignment);
	}
	
	@Test
	public void AABA_AAA_mmdm_Test() {
		String alignment = NeedlemanWunsch.getAlignments("AABA", "AAA");
		assertEquals( "mmdm", alignment);
	}

	@Test
	public void complexTest() {
		String alignment = NeedlemanWunsch.getAlignments("BCEFGH", "ABCDFG");
		assertEquals( "immwmmd", alignment );
	}
	
	@Test
	public void matchFirstTest() {
		String alignment = NeedlemanWunsch.getAlignments("AAA", "AAAA");
		assertEquals( "mmmi", alignment);
		
		alignment = NeedlemanWunsch.getAlignments("AAAA", "AAA");
		assertEquals( "mmmd", alignment);
	}
}
