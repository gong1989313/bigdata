package algorithms;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		int[] s = { 66, 13, 51, 76, 81, 26, 57, 69, 23 };
		quickSort(s, 0, s.length - 1);
		System.out.println("排序结果：" + Arrays.toString(s));
	}

	public static void quickSort(int[] s, int l, int r) {
		int i = l, j = r, x = s[l];
		while (i < j) {
			while (i < j && s[j] >= x) {
				j--;
			}
			if (i < j) {
				s[i] = s[j];
			}
			while (i < j && s[i] <= x) {
				i++;
			}
			if (i < j) {
				s[j] = s[i];
			}
			//此时i==j,下面s[i]或者s[j]都可以，j-1,j+1也ok  
			s[i] = x;
			if (l < i)
				quickSort(s, l, i - 1);
			if (r > i)
				quickSort(s, i + 1, r);
		}
	}
}
