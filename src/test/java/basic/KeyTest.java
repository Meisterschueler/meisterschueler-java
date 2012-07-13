package basic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exception.TranspositionException;


public class KeyTest {

	@Test
	public void testTransposeByPitch() {
		assertEquals( Key.Des, Key.C.transposeByPitch(1) );
		assertEquals( Key.E, Key.F.transposeByPitch(-1) );
	}
	
	@Test
	public void testTransposeByAccidental() {
		assertEquals( Key.G, Key.C.transposeByAccidental(1) );
		assertEquals( Key.F, Key.C.transposeByAccidental(-1) );
		assertEquals( Key.Ges, Key.Cis.transposeByAccidental(-13) );
	}
	
	@Test(expected=TranspositionException.class)
	public void testTranspositionException() {
		Key.Eis.transposeByAccidental(1);
	}
}
