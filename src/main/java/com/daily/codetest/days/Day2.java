package com.daily.codetest.days;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 조이스틱으로 알파벳 이름을 완성하세요. 맨 처음엔 A로만 이루어져 있습니다.
 * ex) 완성해야 하는 이름이 세 글자면 AAA, 네 글자면 AAAA
 *
 * 조이스틱을 각 방향으로 움직이면 아래와 같습니다.
 * ▲ - 다음 알파벳
 * ▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
 * ◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
 * ▶ - 커서를 오른쪽으로 이동 (마지막 위치에서 오른쪽으로 이동하면 첫 번째 문자에 커서)
 *
 * 예를 들어 아래의 방법으로 'JAZ'를 만들 수 있습니다.
 * - 첫 번째 위치에서 조이스틱을 위로 9번 조작하여 J를 완성합니다.
 * - 조이스틱을 왼쪽으로 1번 조작하여 커서를 마지막 문자 위치로 이동시킵니다.
 * - 마지막 위치에서 조이스틱을 아래로 1번 조작하여 Z를 완성합니다.
 * 따라서 11번 이동시켜 'JAZ'를 만들 수 있고, 이때가 최소 이동입니다.
 *
 * 만들고자 하는 이름 name이 매개변수로 주어질 때, 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.
 *
 * 제한 사항
 * - name은 알파벳 대문자로만 이루어져 있습니다.
 * - name의 길이는 1 이상 20 이하입니다.
 *
 * 입출력 예
 * 'JEROEN'	56
 * 'JAN'	23
 */

public class Day2 {

	private static final char A = 'A';

	public static int solution(String name) {
		// 선언부
		int answer = 0;
		Map<Character, Integer> alphabet = new HashMap<>();

		// 1. 알파벳 정의
		IntStream.range(A, A + 26).forEach(value -> alphabet.put((char) value, value));

		// 2. 문자열 문자 배열로 치환 및 계산
		char[] charArray = name.toCharArray();
		for (char ch : charArray) {
			Integer z = alphabet.get('Z') + 1;
			Integer target = alphabet.get(ch);

			if (A == target) {
				continue;
			}

			int up = target - A;
			int down = z - target;
			if (up <= down) {
				answer += up;
			} else {
				answer += down;
			}
		}

		// 문자단위 이동 횟수 증가
		answer += charArray.length - 1;

		// 두번째 인덱스 값에 'A'가 오면 거꾸로 진행했을 때 횟수를 1회 줄일 수 있다.
		if (charArray[1] == A) {
			answer -= 1;
		}

		// 3. 총 조작회수 리턴
		return answer;
	}
}
