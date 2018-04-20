package com.example.stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example {

	public static void main(String[] args) {

		List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
		integerList.stream().filter(i -> i % 2 == 0)
				// .filter(j -> j == 4)
				.forEach(i -> System.out.println(i));

		Map<String, Integer> map = new HashMap<String, Integer>() {
			{
				put("key1", 1);
				put("key2", 2);
				put("key3", 3);
			}
		};
		map.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).forEach(System.out::println);

	}
}
