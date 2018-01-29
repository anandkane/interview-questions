package org.spearhead.tinybits.thread.executor.state;

import org.spearhead.tinybits.thread.executor.AbstractExecutorState;
import org.spearhead.tinybits.thread.executor.AssignerThread;
import org.spearhead.tinybits.thread.executor.Future;

import java.util.Queue;

public class ExecutorStateShut extends AbstractExecutorState {

	protected ExecutorStateShut(Queue<Future> taskQueue) {
		super(taskQueue);
	}

	@Override
	public ExecutorStates getState() {
		return ExecutorStates.SHUT;
	}

	@Override
	public ExecutorState shutDown(AssignerThread assignerThread) {
		throw new IllegalStateException("Executor has been already shut down.");
	}
}
