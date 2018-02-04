package org.spearhead.tinybits.algorythm.string;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LongestNonRepeatingSeqTest {

	@Test
	public void testSimplest() {
		String result;
		result = LongestNonRepeatingSeq.getLongestNonRepeatingSeq("qwert");
		assertEquals("qwert", result);
		result = LongestNonRepeatingSeq.getLongestNonRepeatingSeq("asda");
		assertEquals("asd", result);
		result = LongestNonRepeatingSeq.getLongestNonRepeatingSeq("asdaf");
		assertEquals("asd", result);
		result = LongestNonRepeatingSeq.getLongestNonRepeatingSeq("asdafg");
		assertEquals("asd", result);
		result = LongestNonRepeatingSeq.getLongestNonRepeatingSeq("asdafgh");
		assertEquals("afgh", result);
		result = LongestNonRepeatingSeq.getLongestNonRepeatingSeq("asdafghjakl");
		assertEquals("afghj", result);
		result = LongestNonRepeatingSeq.getLongestNonRepeatingSeq("asdafaghjakl");
		assertEquals("aghj", result);
		result = LongestNonRepeatingSeq.getLongestNonRepeatingSeq("asdafghjaklpoiu");
		assertEquals("aklpoiu", result);
	}

	@Test
	public void testComplex() {
		String result;
		result = LongestNonRepeatingSeq.getLongestNonRepeatingSeq("aas");
		assertEquals("as", result);
		result = LongestNonRepeatingSeq.getLongestNonRepeatingSeq("aaas");
		assertEquals("as", result);
		result = LongestNonRepeatingSeq.getLongestNonRepeatingSeq("aaass");
		assertEquals("as", result);
		result = LongestNonRepeatingSeq.getLongestNonRepeatingSeq("waedftaedaty");
		assertEquals("aedft", result);
	}
}