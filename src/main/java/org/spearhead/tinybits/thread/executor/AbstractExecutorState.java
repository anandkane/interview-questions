package org.spearhead.tinybits.thread.executor;

import org.spearhead.tinybits.thread.executor.state.ExecutorState;

import java.util.Queue;

public abstract class AbstractExecutorState implements ExecutorState {
	protected Queue<Future> taskQueue;

	protected AbstractExecutorState(Queue<Future> taskQueue) {
		this.taskQueue = taskQueue;
	}
}
