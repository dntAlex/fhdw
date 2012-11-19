package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import util.ComparableCollection;
import util.Int;
import util.MyComparable;
import bubbleSort.BubbleSortManager;
import buffer.Buffer;

public class TestBubble {

	Buffer<MyComparable> buffer0;
	ComparableCollection sorted;
	
	@Before
	public void setup() {
		buffer0 =  new Buffer<MyComparable>();
		sorted = new ComparableCollection();
		
		buffer0.put(Int.create(23));
		buffer0.put(Int.create(100));
		buffer0.put(Int.create(42));
		buffer0.put(Int.create(3));
		buffer0.put(Int.create(989));
		buffer0.put(Int.create(984));
		buffer0.put(Int.create(1));
	}
	
	@Test
	public void testSimple() {
		assertEquals(true, true);
	}
	
	@Test
	public void test() {
		ComparableCollection result = BubbleSortManager.getInstance().startSort(buffer0);
		
		assertEquals(sorted, result);
//		System.out.println(result);
	}

}
