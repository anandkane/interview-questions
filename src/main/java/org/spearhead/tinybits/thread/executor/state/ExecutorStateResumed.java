package org.spearhead.tinybits.thread.executor.state;

import org.spearhead.tinybits.thread.executor.Future;

import java.util.Queue;

public class ExecutorStateResumed extends ExecutorStateStarted {
	protected ExecutorStateResumed(Queue<Future> taskQueue) {
		super(taskQueue);
	}

	@Override
	public ExecutorStates getState() {
		return ExecutorStates.RESUMED;
	}
}
