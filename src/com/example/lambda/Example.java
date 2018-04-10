package com.example.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Example {

	public static void main(String[] args) {
		
		// Example1
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		};
		new Thread(r1).start();
		
		//Runnable r = () -> {System.out.println(Thread.currentThread().getName());};
		Runnable r = () -> System.out.println(Thread.currentThread().getName());
		new Thread(r).start();
		
		// Example2
		List<Integer> list1 = Arrays.asList(1, 9, 4, -3);
		Comparator<Integer> c1 = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Collections.sort(list1, c1);
		System.out.println(list1);
		
		List<Integer> list2 = Arrays.asList(1, 9, 4, -3);
		Collections.sort(list2, (o1, o2) -> {return o1 - o2;});
		Collections.sort(list2, (o1, o2) -> o1 - o2);
		System.out.println(list2);
		
		// Example3
		Test t1 = new Test() {
			@Override
			public String test(int i, int j) {
				return Integer.toString(i) + Integer.toString(j);
			}
		};
		System.out.println(t1.test(1, 2));
		
		Test t2 = (i, j) -> {return Integer.toString(i) + Integer.toString(j);};
		System.out.println(t2.test(1, 2));
	}

	interface Test {
		public String test(int i, int j);
	}
}
