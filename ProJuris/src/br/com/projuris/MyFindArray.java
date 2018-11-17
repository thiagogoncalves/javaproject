package br.com.projuris;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MyFindArray implements FindArray {

	public static void main(String[] args) {
		MyFindArray mfa = new MyFindArray();
		
		int[] array1 = { 4, 9, 3, 7, 8 };
		int[] subArray1 = { 3, 7 };
		System.out.println(mfa.findArray(array1, subArray1));

		int[] array2 = { 1, 3, 5 };
		int[] subArray2 = { 1 };
		System.out.println(mfa.findArray(array2, subArray2));
		
		int[] array3 = { 7, 8, 9 };
		int[] subArray3 = { 8, 9, 10 };
		System.out.println(mfa.findArray(array3, subArray3));
		
		int[] array4 = { 4, 9, 3, 7, 8, 3, 7, 1 };
		int[] subArray4 = { 3, 7 };
		System.out.println(mfa.findArray(array4, subArray4));
		
	}

	@Override
	public int findArray(int[] array, int[] subArray) {
		List<Integer> aList = new ArrayList<>();
		for (int i : array) {
			aList.add(i);
		}
		
		List<Integer> subArrList = new ArrayList<>();
		for (int i : subArray) {
			subArrList.add(i);
		}
		
		return Collections.lastIndexOfSubList(aList, subArrList);
	}

}
