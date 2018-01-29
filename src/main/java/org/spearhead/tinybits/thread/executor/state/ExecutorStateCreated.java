package org.spearhead.tinybits.thread.executor.state;

import org.spearhead.tinybits.thread.executor.AbstractExecutorState;
import org.spearhead.tinybits.thread.executor.AssignerThread;
import org.spearhead.tinybits.thread.executor.Future;

import java.util.Queue;

public class ExecutorStateCreated extends AbstractExecutorState {

	public ExecutorStateCreated(Queue<Future> taskQueue) {
		super(taskQueue);
	}

	@Override
	public ExecutorStates getState() {
		return ExecutorStates.CREATED;
	}

	@Override
	public ExecutorState start(AssignerThread assignerThread) {
		return new ExecutorStateStarted(this.taskQueue);
	}

	@Override
	public ExecutorState shutDown(AssignerThread assignerThread) {
		return new ExecutorStateShut(this.taskQueue);
	}
}
