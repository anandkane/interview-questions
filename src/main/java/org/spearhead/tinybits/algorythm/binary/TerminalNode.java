package org.spearhead.tinybits.algorythm.binary;

public class TerminalNode<T extends Comparable<T>> implements BinaryNode<T> {

	@Override
	public boolean hashLeft() {
		return false;
	}

	@Override
	public boolean hasRight() {
		return false;
	}

	@Override
	public boolean add(BinaryNode<T> node) {
		throw new UnsupportedOperationException("Cannot add children to the terminal node");
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public T getValue() {
		return null;
	}
}
