package com.jnmd.junit;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Test3 {
	private JunitDemo1 jd = new JunitDemo1();
	private int param;
	private int result;
	
	public Test3(int param , int result) {
		this.param = param;
		this.result = result;
	}
	
	@Parameters
	public static Collection getParams() {
		return Arrays.asList(new Object[][]{
			{4 , 16},
			{0 , 0},
			{-4 , 16}
		});
	}
	
	@Before
	public void setUp() throws Exception {
		jd.clear();
	}

	@Test
	public void testSquare() {
		jd.square(param);
		assertEquals(result, jd.getResult());
	}

}
