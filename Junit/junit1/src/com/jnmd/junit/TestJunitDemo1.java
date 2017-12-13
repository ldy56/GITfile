package com.jnmd.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestJunitDemo1 {
	private JunitDemo1 jd = new JunitDemo1();

	@Before
	public void setUp() throws Exception {
		jd.clear();
	}

	@Test
	public void testAdd() {
		jd.add(5);
		assertEquals(15, jd.getResult());
	}

	@Test
	public void testSubstract() {
		jd.substract(5);
		assertEquals(-5, jd.getResult());
	}

	@Test
	public void testDivide() {
		jd.divide(2);
		assertEquals(0, jd.getResult());
	}

	@Test
	public void testSquare() {
		jd.square(3);
		assertEquals(9, jd.getResult());
	}

}
