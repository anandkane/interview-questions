package org.spearhead.tinybits.thread.executor;

import java.util.concurrent.Callable;

public class CallableWrapper<T extends Object> implements Callable<T> {
	private Callable<T> callable;
	private Future<T> future = new FutureImpl(callable);

	public CallableWrapper(Callable<T> callable) {
		this.callable = callable;
	}

	@Override
	public T call() {
		try {
			T result = callable.call();
//			future.
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Future getFuture() {
		return future;
	}
}
