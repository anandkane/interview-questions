package org.spearhead.tinybits.thread.executor.state;

import org.spearhead.tinybits.thread.executor.AbstractExecutorState;
import org.spearhead.tinybits.thread.executor.Future;
import org.spearhead.tinybits.thread.executor.FutureImpl;

import java.util.Queue;
import java.util.concurrent.Callable;

public class ExecutorStateStarted extends AbstractExecutorState {

	protected ExecutorStateStarted(Queue<Future> taskQueue) {
		super(taskQueue);
	}

	@Override
	public ExecutorStates getState() {
		return ExecutorStates.STARTED;
	}

	@Override
	public Future submit(Callable<?> callable) {
		FutureImpl future = new FutureImpl("", callable);
		taskQueue.add(future);
		return future;
	}
}
