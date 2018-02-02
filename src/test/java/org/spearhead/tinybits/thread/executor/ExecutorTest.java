package org.spearhead.tinybits.thread.executor;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.concurrent.Callable;

public class ExecutorTest {
	private static Logger logger = Logger.getLogger(ExecutorTest.class);
	@Test
	public void testBasic() {
		Callable<Integer> add = () -> {
			logger.info("Invoked addition callable");
			return 3 + 2;
		};
		Executor executor = new SimpleExecutor();
		executor.start();
		Future<Integer> future = executor.submit(add);
		Integer result = future.get();
		logger.info("Result = " + result);

		executor.shutDown();
	}
}
