package org.spearhead.tinybits.thread.executor;

import org.junit.Test;

import java.util.concurrent.Callable;

public class ExecutorTest {
	@Test
	public void testBasic() {
		Callable<Integer> add = () -> 3 + 2;
		Executor executor = new SimpleExecutor();
		executor.start();
		Future<Integer> future = executor.submit(add);
		Integer result = future.get();
		System.out.println(result);

		executor.shutDown();
	}
}
