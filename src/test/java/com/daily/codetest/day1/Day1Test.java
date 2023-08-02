package com.daily.codetest.day1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/*
str1	str2	answer
FRANCE	french	16384
handshake	shake hands	65536
aa1+aa2	AAAA12	43690
E=M*C^2	e=m*c^2	65536
* */


@SpringBootTest
class Day1Test {

	public static String str1 = "E=M*C^2";
	public static String str2 = "e=m*c^2";

	@Test
	public void 자카드_유사도() {
		char[] charArray1 = str1.toCharArray();
		char[] charArray2 = str2.toCharArray();
		List<String> strList1 = new ArrayList<>();
		List<String> strList2 = new ArrayList<>();
		Set<String> strSet = new HashSet<>();
		List<String> union = new ArrayList<>();
		List<String> intersection = new ArrayList<>();

		split(charArray1, strList1);
		split(charArray2, strList2);

		strSet.addAll(strList1);
		strSet.addAll(strList2);

		for (String word : strSet) {
			List<String> list1 = strList1.stream().filter(word::equals).collect(Collectors.toList());
			List<String> list2 = strList2.stream().filter(word::equals).collect(Collectors.toList());

			int frequency1 = Collections.frequency(list1, word);
			int frequency2 = Collections.frequency(list2, word);

			int min = Math.min(frequency1, frequency2);
			int max = Math.max(frequency1, frequency2);

			for (int i = 0; i < min; i++) {
				intersection.add(word);
			}

			for (int i = 0; i < max; i++) {
				union.add(word);
			}
		}

		System.out.println(union);
		System.out.println(intersection);
		System.out.println(jaccardSimilarity(intersection.size(), union.size()).intValue());
	}

	private void split(char[] charArray, List<String> strList) {
		Pattern pattern = Pattern.compile("^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]*$");
		for (int i = 0; i < charArray.length - 1; i++) {
			if (i == charArray.length) {
				break;
			}
			String str = String.valueOf(charArray[i]) + charArray[i + 1];

			Matcher matcher = pattern.matcher(str);
			if (!matcher.matches() || str.contains(" ")) {
				continue;
			}

			strList.add(str.toLowerCase());
		}
	}

	private Double jaccardSimilarity(int x, int y) {
		if (x == 0 && y == 0) {
			return 65536.0;
		}

		return Math.floor(((double) x / y) * 65536);
	}

	@Test
	public void 정규식_테스트() {
		Pattern pattern = Pattern.compile("^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]*$");
		Matcher matcher1 = pattern.matcher("+a");
		Matcher matcher2 = pattern.matcher("aa1+aa2");
		Matcher matcher3 = pattern.matcher("abcd ef");
		Matcher matcher4 = pattern.matcher("abcdef");

		System.out.println(matcher1.matches());
		System.out.println(matcher2.matches());
		System.out.println(matcher3.matches());
		System.out.println(matcher4.matches());
	}
}


