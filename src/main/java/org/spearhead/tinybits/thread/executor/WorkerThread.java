package org.spearhead.tinybits.thread.executor;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public class WorkerThread extends Thread {
	private Callable callable = () -> null;
	private AtomicBoolean shutDownSignalled;
	private Queue<WorkerThread> workerQueue;

	public WorkerThread(AtomicBoolean shutDownSignalled, Queue<WorkerThread> workerQueue) {
		this.shutDownSignalled = shutDownSignalled;
		this.workerQueue = workerQueue;
		this.workerQueue.add(this);
	}

	public WorkerThread setFuture(Future future) {
		callable = () -> {
			future.call();
			return null;
		};
		return this;
	}

	@Override
	public void run() {
		try {
			while (!shutDownSignalled.get()) {
				callable.call();
				callable = () -> null;
				workerQueue.add(this);
				yield();
			}
		} catch (InterruptedException e) {
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
