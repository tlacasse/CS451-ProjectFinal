package start;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedPool {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(7);
		for (int i = 0; i < 10; i++) {
			executor.execute(new Worker("" + i));
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Finished all threads");
	}

	private static class Worker implements Runnable {

		private String command;

		public Worker(String s) {
			this.command = s;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " Start. Command = " + command);
			processCommand();
			System.out.println(Thread.currentThread().getName() + " End.");
		}

		private void processCommand() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			return this.command;
		}

	}

}
