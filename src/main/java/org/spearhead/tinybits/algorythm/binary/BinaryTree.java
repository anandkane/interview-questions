package org.spearhead.tinybits.algorythm.binary;

import java.util.*;

public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {
	private BinaryIterator<T> binaryIterator = new BinaryIterator<T>() {
		@Override
		public BinaryIterator<T> primary() {
			return new DepthFirstBinaryIterator<>();
		}

		@Override
		public BinaryIterator<T> depthFirst() {
			return new DepthFirstBinaryIterator<>();
		}

		@Override
		public BinaryIterator<T> breadthFirst() {
			return new BreadthFirstBinaryIterator();
		}

		@Override
		public Iterator<List<T>> levelIterator() {
			return new LevelIterator();
		}

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public T next() {
			return null;
		}
	};

	private BinaryNode<T> root;

	public BinaryTree(T value) {
		this.root = new BinaryNodeImpl<>(value);
	}

	public BinaryTree(T value, BinaryIterator<T> iterator) {
		this.root = new BinaryNodeImpl<>(value);
		this.binaryIterator = iterator;
	}

	public boolean add(T value) {
		return root.add(new BinaryNodeImpl<>(value));
	}

	@Override
	public BinaryIterator<T> iterator() {
		return binaryIterator.primary();
	}

	public interface BinaryIterator<T> extends Iterator<T> {
		default BinaryIterator<T> primary() {
			throw new UnsupportedOperationException("Cannot determine primary on actual iterator. Call this method on" +
					" BinaryIterator returned by BinaryTree.iterator");
		}

		BinaryIterator<T> depthFirst();

		BinaryIterator<T> breadthFirst();

		Iterator<List<T>> levelIterator();
	}

	private class LevelIterator implements Iterator<List<T>> {
		private BinaryNode<T> node = BinaryTree.this.root;
		private Deque<BinaryNode<T>> queue = new LinkedList<>();

		{
			queue.add(node);
		}

		@Override
		public boolean hasNext() {
			return !queue.isEmpty();
		}

		@Override
		public List<T> next() {
			List<T> returnMe = new ArrayList<>(queue.size());
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				BinaryNode<T> next = queue.pop();
				if (!next.getLeft().isLeaf()) {
					queue.add(next.getLeft());
				}
				if (!next.getRight().isLeaf()) {
					queue.add(next.getRight());
				}

				returnMe.add(next.getValue());
			}

			return returnMe;
		}
	}

	private class BreadthFirstBinaryIterator<E extends Comparable<E>> implements BinaryIterator<T> {
		private BinaryNode<T> node = BinaryTree.this.root;
		private Deque<BinaryNode<T>> queue = new LinkedList<>();

		{
			queue.add(node);
		}

		@Override
		public boolean hasNext() {
			return !queue.isEmpty();
		}

		@Override
		public T next() {
			BinaryNode<T> returnMe = queue.pop();
			if (!returnMe.getLeft().isLeaf()) {
				queue.add(returnMe.getLeft());
			}
			if (!returnMe.getRight().isLeaf()) {
				queue.add(returnMe.getRight());
			}

			return returnMe.getValue();
		}

		@Override
		public BinaryIterator<T> depthFirst() {
			return new DepthFirstBinaryIterator<>();
		}

		@Override
		public BinaryIterator<T> breadthFirst() {
			return this;
		}

		@Override
		public Iterator<List<T>> levelIterator() {
			return new LevelIterator();
		}
	}

	private class DepthFirstBinaryIterator<E extends Comparable<E>> implements BinaryIterator<T> {

		private BinaryNode<T> node = BinaryTree.this.root;
		//		private Deque<Iterator<BinaryNode<T>>> stack = new LinkedList<>();
		private Deque<BinaryNode<T>> stack = new LinkedList<>();
		private boolean left = true;

		{
			stack.push(node);
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			BinaryNode<T> returnMe = stack.peek();
			BinaryNode<T> next = left ? returnMe.getLeft() : returnMe.getRight();
			left = !left;

			if (next != null) {
				stack.push(next);
			}

			return returnMe.getValue();
		}

		@Override
		public BinaryIterator<T> depthFirst() {
			return this;
		}

		@Override
		public BinaryIterator<T> breadthFirst() {
			return new BreadthFirstBinaryIterator();
		}

		@Override
		public Iterator<List<T>> levelIterator() {
			return new LevelIterator();
		}
	}
}