package com.wsu;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MainClassTest {
	public static MainClass mainClass;
	
	@BeforeClass
	public static void constructMain() {
		mainClass = new MainClass();
	}

	@Test
	public void testBeTrue() {
		assertTrue(mainClass.beTrue());
	}
	
	@Test
	public void testIsMainClass() {
		assertEquals("MainClass", mainClass.isMainClass());
	}

}
