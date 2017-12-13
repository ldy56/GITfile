package com.jnmd.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Test2 {
	private JunitDemo1 jd = new JunitDemo1();
	@Before
	public void setUp() throws Exception {
		jd.clear();
	}
	@Test
	public void testAdd1() {
		assertTrue("失败" ,false);
	}

	@Test
	public void test1() {
		assertNotNull(jd);
	}
	
	@Test
	public void test2() {
		String s1 = new String("hello");
		String s2 = "hello";
		
		assertSame(s1, s2);
	}
	
	@Test(expected=ArithmeticException.class)
	public void testDivide() {
		jd.divide(0);		
	}
}
