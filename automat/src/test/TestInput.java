package test;

import static org.junit.Assert.*;
import i_o.CharElement;
import i_o.EndElement;
import i_o.IO_Element;

import org.junit.Before;
import org.junit.Test;

public class TestInput {

	public EndElement root;
	
	public IO_Element in0;
	public IO_Element in1;
	public IO_Element in2;
	public IO_Element in3;
	public IO_Element in4;
	
	@Before
	public void setup() {
		this.root = new EndElement();

		this.in0 = new CharElement(root, 'a');
		this.in1 = new CharElement(root, 'b');
		this.in2 = new CharElement(in0, 'c');
		this.in3 = new CharElement(in1, '0');
		this.in4 = new CharElement(in3, '1');
	}
	
	@Test
	public void test() {
		assertEquals("", root.resolve());
		assertEquals("a", in0.resolve());
		assertEquals("b", in1.resolve());
		assertEquals("ac", in2.resolve());
		assertEquals("b0", in3.resolve());
		assertEquals("b01", in4.resolve());
	}

}
