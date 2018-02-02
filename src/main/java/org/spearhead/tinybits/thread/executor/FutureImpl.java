package org.spearhead.tinybits.thread.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public class FutureImpl<T extends Object> implements Future<T> {
	private AtomicBoolean completed = new AtomicBoolean(false);
	private Callable<T> callable;
	private T result;
	private String name;

	public FutureImpl(String name, Callable<T> callable) {
		this.name = name;
		this.callable = callable;
	}

	@Override
	public T get() {
		while (!completed.get()) ;
		return result;
	}

	@Override
	public void set(T result) {
		this.result = result;
		this.completed.set(true);
	}

	@Override
	public boolean isCompleted() {
		return completed.get();
	}

	public void call() {
		try {
			result = callable.call();
			completed.set(true);
		} catch (Exception e) {
			completed.set(true);
		}
	}
}
