package algorithms;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		int[] arr = { 66, 13, 51, 76, 81, 26, 57, 69, 23 };
		quickSort(arr, 0, arr.length - 1);
		System.out.println("排序结果：" + Arrays.toString(arr));
	}

	public static void quickSort(int[] arr, int left, int right) {
		int i = left, j = right;
		int x = arr[0];
		while (i < j) {
			while (arr[i] < x && i < right) {
				i++;
			}
			if (arr[i] > x) {
				swap(arr, arr[i], x);
			}
			while (arr[j] > x && j >= 0) {
				j--;
			}
			if (arr[j] < x) {
				swap(arr, arr[j], x);
			}
		}
	}

	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
