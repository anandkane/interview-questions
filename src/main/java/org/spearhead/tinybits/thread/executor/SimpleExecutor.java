package org.spearhead.tinybits.thread.executor;

import org.spearhead.tinybits.thread.executor.state.ExecutorState;
import org.spearhead.tinybits.thread.executor.state.ExecutorStateCreated;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleExecutor implements Executor {
	public static final int WORKER_COUNT = 5;
	private final AssignerThread assignerThread;
	private ConcurrentLinkedQueue<Future> taskQueue = new ConcurrentLinkedQueue();
	private Queue<WorkerThread> workerQueue = new ArrayDeque<>(WORKER_COUNT);
	private ExecutorState state = new ExecutorStateCreated(this.taskQueue);
	private AtomicBoolean shutDownSignalled = new AtomicBoolean(true);

	public SimpleExecutor() {
		for (int i = 0; i < WORKER_COUNT - 1; i++) {
			new WorkerThread(shutDownSignalled, workerQueue);
		}
		assignerThread = new AssignerThread(taskQueue, workerQueue, shutDownSignalled);
		assignerThread.start();
	}

	@Override
	public void start() {
		state = state.start(assignerThread);
	}

	@Override
	public void shutDown() {
		state = state.shutDown(assignerThread);
	}

	@Override
	public void pause() {
		state = state.pause(assignerThread);
	}

	@Override
	public void resume() {
		state = state.resume(assignerThread);
	}

	@Override
	public <T> Future<T> submit(Callable<T> callable) {
		Future<T> future = state.submit(callable);
		assignerThread.interrupt();

		return future;
	}
}
