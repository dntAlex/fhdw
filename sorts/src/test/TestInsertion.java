package test;

import static org.junit.Assert.assertEquals;
import insertionSort.InsertionManager;

import org.junit.Before;
import org.junit.Test;

import util.Int;
import util.MyComparable;
import util.StopSignal;
import buffer.Buffer;

public class TestInsertion {

	Buffer<MyComparable> buffer;
	
	Int oneNegative;
	Int zero;
	Int ten;
	Int fourtyTwo;
	Int big;
	
	@Before
	public void setup() {
		this.buffer = new Buffer<MyComparable>();
		
		this.oneNegative = Int.create(-1);
		this.zero = Int.create(0);
		this.ten = Int.create(10);
		this.fourtyTwo = Int.create(42);
		this.big = Int.create(1082741);
	}
	
	@Test
	public void testEmpty() {
		InsertionManager mng = InsertionManager.create(buffer);
		assertEquals(StopSignal.getInstance(), mng.getSmallestRemaining());
	}
	
	@Test
	public void testSimple() {
		buffer.put(this.zero);
		
		InsertionManager mng = InsertionManager.create(buffer);
		assertEquals(zero, mng.getSmallestRemaining());
		assertEquals(StopSignal.getInstance(), mng.getSmallestRemaining());
	}
	
	@Test
	public void testLessSimple() {
		buffer.put(this.fourtyTwo);
		buffer.put(this.ten);
		
		InsertionManager mng = InsertionManager.create(buffer);
		assertEquals(ten, mng.getSmallestRemaining());
		assertEquals(fourtyTwo, mng.getSmallestRemaining());
		assertEquals(StopSignal.getInstance(), mng.getSmallestRemaining());
	}

	@Test
	public void test() {
		buffer.put(this.big);
		buffer.put(this.ten);
		buffer.put(this.zero);
		buffer.put(this.fourtyTwo);
		buffer.put(this.oneNegative);
		
		InsertionManager mng = InsertionManager.create(buffer);
		assertEquals(oneNegative, mng.getSmallestRemaining());
		assertEquals(zero, mng.getSmallestRemaining());
		assertEquals(ten, mng.getSmallestRemaining());
		assertEquals(fourtyTwo, mng.getSmallestRemaining());
		assertEquals(big, mng.getSmallestRemaining());
		assertEquals(StopSignal.getInstance(), mng.getSmallestRemaining());
	}
	
}
