package algorithms;

import org.junit.Test;

public class binarySearchTest {

	@Test
	public void test() {
		int[] arr = {1,3,5,6,7,8,9,11,14};
		int index = binarySearch(arr, 11);
		System.out.println("result index:"+index);
	}

	private int binarySearch(int[] arr, int key) {
		int l, h, mid;
		l =0;
		h = arr.length-1;
		int index = -1;
		mid = (l + h)/2;
		while(l < h) {
			if(key < arr[mid]) {
				h = mid - 1;
				mid = (l + h)/2;
			}else if(key > arr[mid]) {
				l = mid + 1;
				mid = (l + h)/2;
			}else {
				index = mid;
				break;
			}
		}
		return index;
	}
}
