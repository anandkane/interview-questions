package org.spearhead.tinybits.thread.executor;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class AssignerThread extends Thread {
	private Queue<Future> taskQueue;
	private Queue<WorkerThread> workerQueue;
	private AtomicBoolean shutDownSignalled;

	public AssignerThread(Queue<Future> taskQueue, Queue<WorkerThread> workerQueue, AtomicBoolean shutDownSignalled) {
		this.taskQueue = taskQueue;
		this.workerQueue = workerQueue;
		this.shutDownSignalled = shutDownSignalled;
	}

	@Override
	public void run() {
		try {
			workerQueue.forEach((workerThread) -> workerThread.start());

			while (!shutDownSignalled.get()) {
				Future poll;
				while ((poll = taskQueue.poll()) == null) {
					sleep(1000);
					continue;
				}
				WorkerThread worker;
				while ((worker = workerQueue.poll()) == null) {
					sleep(100);
					continue;
				}
				worker.setFuture(poll).interrupt();
			}
		} catch (InterruptedException e) {
		}
	}

	public void shutDown() {
		shutDownSignalled.set(true);
	}
}
