package com.daily.codetest.days;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 메리는 여름을 맞아 무인도로 여행을 가기 위해 지도를 보고 있습니다. 지도에는 바다와 무인도들에 대한 정보가 표시돼 있습니다.
 * 지도는 1 x 1크기의 사각형들로 이루어진 직사각형 격자 형태이며, 격자의 각 칸에는 'X' 또는 1에서 9 사이의 자연수가 적혀있습니다.
 * 지도의 'X'는 바다를 나타내며, 숫자는 무인도를 나타냅니다. 이때, 상, 하, 좌, 우로 연결되는 땅들은 하나의 무인도를 이룹니다.
 * 지도의 각 칸에 적힌 숫자는 식량을 나타내는데, 상, 하, 좌, 우로 연결되는 칸에 적힌 숫자를 모두 합한 값은 해당 무인도에서 최대 며칠동안 머물 수 있는지를 나타냅니다.
 * 어떤 섬으로 놀러 갈지 못 정한 메리는 우선 각 섬에서 최대 며칠씩 머물 수 있는지 알아본 후 놀러갈 섬을 결정하려 합니다.
 *
 * 지도를 나타내는 문자열 배열 maps가 매개변수로 주어질 때,
 * 각 섬에서 최대 며칠씩 머무를 수 있는지 배열에 오름차순으로 담아 return 하는 solution 함수를 완성해주세요.
 * 만약 지낼 수 있는 무인도가 없다면 -1을 배열에 담아 return 해주세요.
 *
 */
public class Day4 {

	public int[] solution(String[] maps) {
		// 1. 인접한 행과 열을 추출해야하므로 반복문을 돌리며 집합을 뽑아내야된다. 자료구조를 뭐로 하지....
		StringBuilder sb = new StringBuilder();
		int length = maps[0].length();
		for (String map : maps) {
			sb.append(map);
		}

		LinkedList<Region> regions = new LinkedList<>();
		String[] split = sb.toString().split("");
		for (int i = 0; i < split.length; i++) {
			if ("X".equals(split[i])) {
				continue;
			}
			regions.add(new Region(i / length, i % length, Integer.parseInt(split[i])));
		}

		if (regions.isEmpty()) {
			return new int[] {-1};
		}

		List<Area> areas = new ArrayList<>();
		for (int i = 0; i < regions.size(); i++) {
			Region region = regions.get(i);
			Area tempArea = new Area();
			if (areas.isEmpty()) {
				tempArea.regions.add(region);
				tempArea.sumPrice(region.value);
				areas.add(tempArea);
				continue;
			}

			boolean isAdjoin = false;
			for (Area area : areas) {
				isAdjoin = area.regions.stream().anyMatch(r -> r.isAdjoin(region));
				if (isAdjoin) {
					area.regions.add(region);
					area.sumPrice(region.value);
					break;
				}
			}

			if (!isAdjoin) {
				tempArea.regions.add(region);
				tempArea.sumPrice(region.value);
				areas.add(tempArea);
			}
		}
		// TODO: 행으로 진행되다 보니 다른 객체에 담기는 이슈 발생, 객체 각각 진행 시켜 담기보다는 전체를 돌면서 서로 이어줘야 할거같다.... linkedlist 를 사용해볼까... 어지럽다
		return areas.stream().sorted(Comparator.comparingInt(o -> o.price)).mapToInt(area -> area.price).toArray();
	}

	class Area{
		int price = 0;
		ArrayList<Region> regions = new ArrayList<>();

		public void sumPrice(int value) {
			this.price += value;
		}

	}

	class Region {
		int x = 0;
		int y = 0;
		int value = 0;

		Region(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}

		public boolean isAdjoin(Region region) {
			if ((this.x == region.x && Math.abs(this.y - region.y) == 1) || (this.y == region.y && Math.abs(this.x - region.x) == 1)) {
				return true;
			}

			return false;
		}
	}
}
