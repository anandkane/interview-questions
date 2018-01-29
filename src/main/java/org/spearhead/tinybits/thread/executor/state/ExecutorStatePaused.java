package org.spearhead.tinybits.thread.executor.state;

import org.spearhead.tinybits.thread.executor.AbstractExecutorState;
import org.spearhead.tinybits.thread.executor.AssignerThread;
import org.spearhead.tinybits.thread.executor.Future;

import java.util.Queue;

public class ExecutorStatePaused extends AbstractExecutorState {
	protected ExecutorStatePaused(Queue<Future> taskQueue) {
		super(taskQueue);
	}

	@Override
	public ExecutorStates getState() {
		return ExecutorStates.PAUSED;
	}

	@Override
	public ExecutorState resume(AssignerThread assignerThread) {
		return new ExecutorStateResumed(this.taskQueue);
	}
}
