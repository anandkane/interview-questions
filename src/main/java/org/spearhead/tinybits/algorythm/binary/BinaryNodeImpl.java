package org.spearhead.tinybits.algorythm.binary;

class BinaryNodeImpl<T extends Comparable<T>> implements BinaryNode<T> {
	T value;
	BinaryNode<T> left = (node) -> {
		if (node == null) {
			throw new IllegalArgumentException("Node cannot be null");
		}
		left = node;
		return true;
	};

	BinaryNode<T> right = (node) -> {
		if (node == null) {
			throw new IllegalArgumentException("Node cannot be null");
		}
		right = node;
		return true;
	};

	BinaryNodeImpl(T value) {
		if (value == null) {
			throw new IllegalArgumentException("Value cannot be null");
		}
		this.value = value;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("BinaryNodeImpl{").append("value=").append(value).append(", left=")
				.append(left.getValue()).append(", right=").append(right.getValue()).append('}').toString();
	}

	@Override
	public BinaryNode<T> getLeft() {
		return left;
	}

	@Override
	public BinaryNode<T> getRight() {
		return right;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public boolean add(BinaryNode<T> node) {
		if (node == null) {
			throw new IllegalArgumentException("Node cannot be null");
		}

		int compare = node.getValue().compareTo(value);
		if (compare == 0) {
			return false;
		}

		return compare > 0 ? right.add(node) : left.add(node);
	}
}
