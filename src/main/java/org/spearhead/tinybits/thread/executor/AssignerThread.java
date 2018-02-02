package org.spearhead.tinybits.thread.executor;

import org.apache.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class AssignerThread extends Thread {
	private static Logger logger = Logger.getLogger(AssignerThread.class);
	private Queue<Future> taskQueue;
	private Queue<WorkerThread> workerQueue;
	volatile private AtomicBoolean shutDownSignalled;

	public AssignerThread(Queue<Future> taskQueue, Queue<WorkerThread> workerQueue, AtomicBoolean shutDownSignalled) {
		this.taskQueue = taskQueue;
		this.workerQueue = workerQueue;
		this.shutDownSignalled = shutDownSignalled;
	}

	@Override
	public void run() {
		try {
			workerQueue.forEach((workerThread) -> {
				workerThread.start();
			});

			while (!shutDownSignalled.get()) {
				Future poll;
				while ((poll = taskQueue.poll()) == null) {
					sleep(1000);
				}
				logger.debug("Popped future " + poll + " for execution");

				WorkerThread worker;
				while ((worker = workerQueue.poll()) == null) {
					sleep(100);
				}
				logger.debug("Removed worker thread " + worker.getName() + " from worker queue");
				worker.setFuture(poll).interrupt();
			}
		} catch (InterruptedException e) {
			logger.debug("Assigner thread interrupted.");
		}
	}

	public void shutDown() {
		shutDownSignalled.set(true);
	}
}
