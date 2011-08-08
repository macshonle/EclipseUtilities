package edu.utsa.collections;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T>, Iterable<T> {
	private T[] array;
	private int pos;

	public ArrayIterator(T[] array) {
		this.array = array;
		this.pos = 0;
	}

	@Override
	public boolean hasNext() {
		return pos < array.length;
	}

	@Override
	public T next() {
		return array[pos++];
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Cannot remove from an array");
	}

	@Override
	public Iterator<T> iterator() {
		return this;
	}
}
