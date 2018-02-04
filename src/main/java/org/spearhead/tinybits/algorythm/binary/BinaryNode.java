package org.spearhead.tinybits.algorythm.binary;

public interface BinaryNode<T extends Comparable<T>> {

	default BinaryNode<T> getLeft() {
		return null;
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
}
