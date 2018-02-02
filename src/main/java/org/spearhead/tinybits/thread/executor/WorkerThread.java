package org.spearhead.tinybits.thread.executor;

import org.apache.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public class WorkerThread extends Thread {
	private static Logger logger = Logger.getLogger(WorkerThread.class);
	private static final Callable voidCallable = () -> {
		logger.trace("Do nothing callable invoked in " + Thread.currentThread().getName() + " thread.");
		return null;
	};

	private Callable callable = voidCallable;
	private final AtomicBoolean shutDownSignalled;
	private final Queue<WorkerThread> workerQueue;

	public WorkerThread(String name, final AtomicBoolean shutDownSignalled, final Queue<WorkerThread> workerQueue) {
		this.setName(name);
		this.shutDownSignalled = shutDownSignalled;
		this.workerQueue = workerQueue;
		this.workerQueue.add(this);
		logger.debug("Added " + this.getName() + " to worker queue");
	}

	public WorkerThread setFuture(Future future) {
		callable = () -> {
			future.call();
			workerQueue.add(this);
			logger.debug("Added " + this.getName() + " back to worker queue");
			callable = voidCallable;
			return null;
		};
		return this;
	}

	@Override
	public void run() {
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			logger.debug(this.getName() + " interrupted from initial sleep.");
		}

		while (!shutDownSignalled.get()) {
			try {
				callable.call();
				sleep(10000);
			} catch (InterruptedException e) {
				logger.debug(this.getName() + " interrupted.");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
