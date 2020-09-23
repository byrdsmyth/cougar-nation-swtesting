package com.wsu;

import static org.junit.Assert.*;
import org.junit.Test;
import org.easymock.EasyMock;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;
import java.util.logging.Logger;

import org.junit.Test;

public class BoundedFifoBufferTest {

	@Test
	public void testSize() {
		BoundedFifoBuffer buffer = new BoundedFifoBuffer();
		buffer.add(new Object());
		assertEquals(1, buffer.size());
		buffer.add(new Object());
		assertEquals(2, buffer.size());
		buffer.remove();
		assertEquals(1, buffer.size());
	}
	
	@Test
	public void testIsEmpty() {	
		BoundedFifoBuffer buffer = new BoundedFifoBuffer(1);
		assertTrue(buffer.isEmpty());	
		buffer.add(new Object());
		assertFalse(buffer.isEmpty());
		buffer.remove();
		assertTrue(buffer.isEmpty());
	}
	
	@Test
	public void testIsFull() {
		BoundedFifoBuffer buffer = new BoundedFifoBuffer(2);
		buffer.add(new Object());
		assertFalse(buffer.isFull());
		buffer.add(new Object());
		assertTrue(buffer.isFull());
	}
	
	@Test
	public void testAddObject() {
		//Tested with testIsEmpty(), testIsFull(), and testRemove(), testSize();
	}
	
	@Test
	public void testGet() {
		BoundedFifoBuffer buffer = new BoundedFifoBuffer(2);
		buffer.add(new Object());
		assertNotNull(buffer.get());
	}
	
	@Test
	public void testRemove() {
		BoundedFifoBuffer buffer = new BoundedFifoBuffer(2);
		buffer.add(new Object());
		assertFalse(buffer.isEmpty());
		buffer.remove();
		assertTrue(buffer.isEmpty());
	}

}
