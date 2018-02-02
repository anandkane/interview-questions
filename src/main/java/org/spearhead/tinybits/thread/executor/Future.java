package org.spearhead.tinybits.thread.executor;

public interface Future<T extends Object> {
	T get();

	void set(T result);

	boolean isCompleted();

	default String getName() {
		return "Anonymous task " + this.toString();
	}

	void call();
}
