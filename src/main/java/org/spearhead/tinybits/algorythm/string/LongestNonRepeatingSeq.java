package org.spearhead.tinybits.algorythm.string;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class LongestNonRepeatingSeq {
	public static String getLongestNonRepeatingSeq(char[] chars) {
		if (ArrayUtils.isEmpty(chars)) {
			return "";
		}
		int[] indexes = new int[26];
		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = -1;
		}
		int start = -1, end = -1, lastRepeatIndex = -1;

		chars = removeConsecutiveDuplicates(chars);
		for (int i = 0; i < chars.length; i++) {
			int at = chars[i] - 'a';
			int ns = indexes[at];
			if (ns != -1) {
				int ne = i;
				if (!retainOld(lastRepeatIndex, start, end, ns, ne)) {
					start = ns < lastRepeatIndex ? lastRepeatIndex : ns;
					end = ne;
				}
				lastRepeatIndex = i;
			}

			indexes[at] = i;
		}

		if (start == -1) {
			start = 0;
			end = chars.length;
		} else {
			if (!retainOld(lastRepeatIndex, start, end, end, chars.length)) {
				start = end < lastRepeatIndex ? lastRepeatIndex : end;
				end = chars.length;
			}
		}

		return String.valueOf(Arrays.copyOfRange(chars, start, end));
	}

	public static String getLongestNonRepeatingSeq(String s) {
		if (StringUtils.isEmpty(s)) {
			return "";
		}

		return getLongestNonRepeatingSeq(s.toCharArray());
	}

	private static char[] removeConsecutiveDuplicates(char[] chars) {
		if (chars.length == 1) {
			return chars;
		}

		char[] chars1 = new char[chars.length];
		int dex = 0;
		char last = chars[0];
		for (int i = 1; i < chars.length; i++) {
			if (last == chars[i]) {
				continue;
			}
			chars1[dex++] = last;
			last = chars[i];
		}
		chars1[dex++] = last;
		return Arrays.copyOf(chars1, dex);
	}

	private static boolean retainOld(int lastRepeatIndex, int s, int e, int ns, int ne) {
		if (ns < lastRepeatIndex) ns = lastRepeatIndex;

		if (s == -1) {
			return false;
		}
		if (ne - ns > e - s) {
			return false;
		}

		if (ns < e) {
			return true;
		}
		return true;
	}
}
