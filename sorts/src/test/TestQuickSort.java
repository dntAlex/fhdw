package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import quickSort.QuickSort;
import util.Int;
import util.MyComparable;
import util.StopSignal;
import buffer.Buffer;

public class TestQuickSort {

	Buffer<MyComparable> buffer;
	
	Int oneNegative;
	Int zero;
	Int ten;
	Int fourtyTwo;
	Int big;
	
	@Before
	public void setup() {
		this.buffer = new Buffer<>();
		
		this.oneNegative = Int.create(-1);
		this.zero = Int.create(0);
		this.ten = Int.create(10);
		this.fourtyTwo = Int.create(42);
		this.big = Int.create(1082741);
	}
	
	@Test
	public void testEmpty() {
		buffer.put(StopSignal.getInstance());
		QuickSort qs = QuickSort.create(buffer);
		assertEquals(StopSignal.getInstance(), qs.getSmallestElement());
	}
	
	@Test
	public void testSimple() {
		buffer.put(this.zero);
		buffer.put(StopSignal.getInstance());
		
		QuickSort qs = QuickSort.create(buffer);
		assertEquals(zero, qs.getSmallestElement());
		assertEquals(StopSignal.getInstance(), qs.getSmallestElement());
	}
	
	@Test
	public void testLessSimple() {
		buffer.put(this.fourtyTwo);
		buffer.put(this.ten);
		buffer.put(StopSignal.getInstance());
		
		QuickSort qs = QuickSort.create(buffer);
		assertEquals(ten, qs.getSmallestElement());
		assertEquals(fourtyTwo, qs.getSmallestElement());
		assertEquals(StopSignal.getInstance(), qs.getSmallestElement());
	}

	public void test() {
		buffer.put(this.big);
		buffer.put(this.ten);
		buffer.put(this.zero);
		buffer.put(this.fourtyTwo);
		buffer.put(this.oneNegative);
		buffer.put(StopSignal.getInstance());
		
		QuickSort qs = QuickSort.create(buffer);
		assertEquals(oneNegative, qs.getSmallestElement());
		assertEquals(zero, qs.getSmallestElement());
		assertEquals(fourtyTwo, qs.getSmallestElement());
		assertEquals(big, qs.getSmallestElement());
		assertEquals(StopSignal.getInstance(), qs.getSmallestElement());
	}

}
