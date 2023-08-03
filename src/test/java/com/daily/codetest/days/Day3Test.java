package com.daily.codetest.days;

import org.junit.jupiter.api.Test;

class Day3Test {

	@Test
	void solution() {
		int[][] target = new int[][] {{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};
		System.out.println(Day3.interceptSystem(target));
	}
}
