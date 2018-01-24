package org.spearhead.tinybits.thread;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Objective is to have two threads print values alternately (possibly without using synchronization.
 */
public class AlternatingThreadDemo {

	public static void main(String[] args) {
		AtomicBoolean switchh = new AtomicBoolean(true);
		new Thread(new AlternatingThread("1", true, switchh)).start();
		new Thread(new AlternatingThread("2", false, switchh)).start();
	}
}

class AlternatingThread implements Runnable {
	private String printIt;
	private boolean valueToCheck;
	private AtomicBoolean switchh;

	public AlternatingThread(String printIt, boolean valueToCheck, AtomicBoolean switchh) {
		this.printIt = printIt;
		this.valueToCheck = valueToCheck;
		this.switchh = switchh;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			while (switchh.get() != valueToCheck) ; // Just wait for value to change
			System.out.print(printIt);
			switchh.set(!valueToCheck);
		}
	}
}
