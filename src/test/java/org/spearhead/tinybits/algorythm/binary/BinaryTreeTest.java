package org.spearhead.tinybits.algorythm.binary;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class BinaryTreeTest {

	@Test
	public void testBasicAdd() {
		BinaryTree<Integer> tree = new BinaryTree<>(100);
		tree.add(101);
		tree.add(99);
	}

	@Test
	public void testBreadthIterationBasic() {
		BinaryTree<Integer> tree = new BinaryTree<>(100);
		tree.add(102);
		tree.add(101);
		tree.add(98);

		BinaryTree.BinaryIterator<Integer> iterator = tree.iterator().breadthFirst();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	@Test
	public void testLevelIterator() {
		BinaryTree<Integer> tree = new BinaryTree<>(100);
		tree.add(102);
		tree.add(101);
		tree.add(98);
		tree.add(96);
		tree.add(23);
		tree.add(234);

		Iterator<List<Integer>> iterator = tree.iterator().levelIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}