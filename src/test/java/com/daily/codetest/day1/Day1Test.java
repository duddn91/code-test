package com.daily.codetest.day1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
*
* str1	str2	answer
FRANCE	french	16384
handshake	shake hands	65536
aa1+aa2	AAAA12	43690
E=M*C^2	e=m*c^2	65536
* */


@SpringBootTest
class Day1Test {

	public static String str1 = "handshake";
	public static String str2 = "shake hands";

	@Test
	public void 자카드_유사도() {
		char[] charArray1 = str1.toCharArray();
		char[] charArray2 = str2.toCharArray();
		List<String> strList1 = new ArrayList<>();
		List<String> strList2 = new ArrayList<>();
		List<String> 합집합 = new ArrayList<>();
		List<String> 교집합 = new ArrayList<>();

		split(charArray1, strList1);
		split(charArray2, strList2);

		합집합 = Stream.concat(strList1.stream(), strList2.stream()).collect(Collectors.toList());

		for (String word : strList1) {
			List<String> collect = strList2.stream().filter(word::equals).collect(Collectors.toList());
			if (collect.size() == 0) {
				continue;
			}
			int frequency1 = Collections.frequency(strList1, word);
			int frequency2 = Collections.frequency(strList2, word);

			int min = Math.min(frequency1, frequency2);

			for (int i = 0; i < min; i++) {
				교집합.add(word);
				합집합.remove(word);
			}
		}
		System.out.println(합집합);
		System.out.println(교집합);
		System.out.println(Math.floor(zakard(교집합.size(), 합집합.size())));
	}

	private void split(char[] charArray, List<String> strList) {
		Pattern pattern = Pattern.compile("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]");
		for (int i = 0; i < charArray.length - 1; i++) {
			if (i == charArray.length) {
				break;
			}
			String str = String.valueOf(charArray[i]) + charArray[i + 1];

			Matcher matcher = pattern.matcher(str);
			if (matcher.matches()) {
				continue;
			}

			strList.add(str.toLowerCase());
		}
	}

	private double zakard(int 교집합, int 합집합) {
		double z = (double) 교집합 / 합집합;
		return z == 0 ? 65536 : (z * 65536);
	}
}


