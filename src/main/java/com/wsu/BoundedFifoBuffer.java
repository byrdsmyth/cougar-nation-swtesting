package com.wsu;
import java.io.IOException;
import java.io.Serializable;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.stream.*;
import java.util.stream.DoubleStream.Builder;
import java.util.Iterator;

public class BoundedFifoBuffer extends AbstractCollection
implements Builder, Serializable {

	/** Serialization version */
	private static final long serialVersionUID = 5603722811189451017L;

	/** Underlying storage array */
	private transient Object[] elements;
	/** Array index of first (oldest) buffer element */
	private transient int start = 0;

	private transient int end = 0;

	private transient boolean full = false;
	
	/** Capacity of the buffer */
	private final int maxElements;
	/**
	69 * Constructs a new <code>BoundedFifoBuffer</code> big enough to hold 32
	70 * elements.
	71 */
	public BoundedFifoBuffer() {
		this(32);
	}
	/**
	77 * Constructs a new <code>BoundedFifoBuffer</code> big enough to hold the
	78 * specified number of elements.
	79 *
	80 * @param size
	81 * the maximum number of elements for this fifo
	82 * @throws IllegalArgumentException
	83 * if the size is less than 1
	84 */
	public BoundedFifoBuffer(final int size) {
	if (size <= 0) {
	throw new IllegalArgumentException(
	"The size must be greater than 0");
	}
	this.elements = new Object[size];
	this.maxElements = this.elements.length;
	}

	public BoundedFifoBuffer(final Collection coll) {
		this(coll.size());
		this.addAll(coll);
	}

	public int size() {
		int size = 0;
		
		if (this.end < this.start) {
			size = this.maxElements - this.start + this.end;
		} else if (this.end == this.start) {
			size = this.full ? this.maxElements : 0;
		} else {
			size = this.end - this.start;
		}
			return size;
		}
	/**
	130 * Returns true if this buffer is empty; false otherwise.
	131 *
	132 * @return true if this buffer is empty
	133 */
		public boolean isEmpty() {
			return this.size() == 0;
		}
	/**
	139 * Returns true if this collection is full and no new elements can be added.
	140 *
	141 * @return <code>true</code> if the collection is full
	 */
		public boolean isFull() {
			return this.size() == this.maxElements;
		}
	/**
	148 * Adds the given element to this buffer.
	149 *
	150 * @param element
	151 * the element to add
	152 * @return true, always
	153 * @throws NullPointerException
	154 * if the given element is null
	155 * @throws BufferOverflowException
	156 * if this buffer is full
	157 */
	public boolean add(final Object element) {
		if (null == element) {
			throw new NullPointerException("Attempted to add null object to buffer");
		}
		if (this.full) {
			throw new BufferOverflowException();
		}
		this.elements[this.end++] = element;
		if (this.end >= this.maxElements) {
			this.end = 0;
		}
		if (this.end == this.start) {
			this.full = true;
		}
		return true;
	}

	public Object get() {
		if (this.isEmpty()) {
			throw new BufferUnderflowException();
		}
		return this.elements[this.start];
	}
	public Object remove() {
		if (this.isEmpty()) {
			throw new BufferUnderflowException();
	}
		final Object element = this.elements[this.start];
	
			if (null != element) {
				this.elements[this.start++] = null;
	
			if (this.start >= this.maxElements) {
				this.start = 0;
			}

			this.full = false;
		}
		return element;
	}
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void accept(double t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public DoubleStream build() {
		// TODO Auto-generated method stub
		return null;
	}

}
