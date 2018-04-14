package concurrent;

import org.junit.Test;

public class MashMapTest {
	

	@Test
	public void test() {
		System.out.println(highestOneBit(5));
		System.out.println(highestOneBit(124));
	}
	
	//通过Integer.highestOneBit算出比指定整数小的最大的2^N值
	public static int highestOneBit(int i) {
		  i |= (i >>  1);
		  i |= (i >>  2);
		  i |= (i >>  4);
		  i |= (i >>  8);
		  i |= (i >> 16);
		  return i - (i >>> 1);
		}

	//头插法
	/*void transfer(Entry[] newTable, boolean rehash) {
		int newCapacity = newTable.length;
		for (Entry<K, V> e : table) {
			while (null != e) {
				Entry next = e.next;
				if (rehash) {
					e.hash = null == e.key ? 0 : hash(e.key);
				}
				int i = indexFor(e.hash, newCapacity);
				e.next = newTable[i];
				newTable[i] = e;
				e = next;
			}
		}
	}*/
}
