package org.spearhead.tinybits.thread.executor;

import java.util.concurrent.Callable;

public interface Executor {
	void start();

	void shutDown();

	void pause();

	void resume();

	<T extends Object> Future<T> submit(Callable<T> callable);
}
