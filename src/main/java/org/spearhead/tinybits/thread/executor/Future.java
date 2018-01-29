package org.spearhead.tinybits.thread.executor;

public interface Future<T extends Object> {
	T get();
	void set(T result);
	boolean isCompleted();

	void call();
}
