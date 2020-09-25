package com.runningsnail.demos.unittest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author yongjie created on 2020/9/3.
 */
public class JVMUnitTest {

	private static JVMUnit jvmUnit;

	@BeforeClass
	public static void beforeClass() {
		jvmUnit = new JVMUnit();
		System.out.println("beforeClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("setUp");
	}

	@Test
	public void calculateApp() {
		jvmUnit.calculateApp();
		Assert.assertTrue(false);
	}

	@Test
	public void calculateTimes() {
		int result = jvmUnit.calculateTimes(2);
		Assert.assertEquals(2, result);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
	}
}