package org.spearhead.tinybits.algorythm.binary;

import java.util.ArrayList;
import java.util.Iterator;

public interface BinaryNode<T extends Comparable<T>> extends Iterable<BinaryNode<T>> {
	default boolean hashLeft() {
		return true;
	}

	default BinaryNode<T> getLeft() {
		return null;
	}

	default boolean hasRight() {
		return true;
	}

	default BinaryNode<T> getRight() {
		return null;
	}

	default boolean isLeaf() {
		return true;
	}

	default T getValue() {
		return null;
	}

	boolean add(BinaryNode<T> node);

	@Override
	default Iterator<BinaryNode<T>> iterator() {
		Iterator<BinaryNode<T>> iterator = new Iterator<BinaryNode<T>>() {
			private Iterator<BinaryNode<T>> valueIterator;
			ArrayList<BinaryNode<T>> objects = new ArrayList<>(2);

			{
				objects.add(getLeft());
				objects.add(getRight());
				objects.removeIf(c -> c == null || c instanceof TerminalNode);

				valueIterator = objects.iterator();
			}

			@Override
			public boolean hasNext() {
				return valueIterator.hasNext();
			}

			@Override
			public BinaryNode<T> next() {
				return valueIterator.next();
			}
		};

		return iterator;
	}
}
