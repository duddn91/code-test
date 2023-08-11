package com.daily.codetest.days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Day4Test {


	@Test
	void solution() {
		String[] target = new String[] {"1XXX2", "1XXX5", "2X3X2", "1X3X1", "2XXX2", "1XXX1"};
		Day4 day4 = new Day4();

		int[] solution = day4.solution(target);
		System.out.println(Arrays.toString(solution));
	}
}
