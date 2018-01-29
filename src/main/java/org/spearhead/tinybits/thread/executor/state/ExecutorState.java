package org.spearhead.tinybits.thread.executor.state;

import org.spearhead.tinybits.thread.executor.AssignerThread;
import org.spearhead.tinybits.thread.executor.Future;

import java.util.concurrent.Callable;

public interface ExecutorState {

	enum ExecutorStates {
		CREATED, STARTED, STOPPED, PAUSED, RESUMED, SHUT;
	}

	ExecutorStates getState();

	default Future submit(Callable<? extends Object> callable) {
		throw new IllegalStateException(new StringBuilder().append("Cannot submit task in ")
				.append(getState().name()).append(".").toString());
	}

	default ExecutorState start(AssignerThread assignerThread) {
		throw new IllegalStateException(new StringBuilder().append("Cannot start executor in ")
				.append(getState().name()).append(".").toString());
	}

	default ExecutorState stop(AssignerThread assignerThread) {
		throw new IllegalStateException(new StringBuilder().append("Cannot stop executor in ")
				.append(getState().name()).append(".").toString());
	}

	default ExecutorState pause(AssignerThread assignerThread) {
		throw new IllegalStateException(new StringBuilder().append("Cannot pause executor in ")
				.append(getState().name()).append(".").toString());
	}

	default ExecutorState resume(AssignerThread assignerThread) {
		throw new IllegalStateException(new StringBuilder().append("Cannot resume executor in ")
				.append(getState().name()).append(".").toString());
	}

	default ExecutorState shutDown(AssignerThread assignerThread) {
		assignerThread.shutDown();
		return new ExecutorStateShut(null);
	}
}
