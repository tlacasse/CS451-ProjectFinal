package food.food;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import food.Program;

public class Bacon implements Runnable {

	private Future<Food> future;

	public Bacon(ScheduledExecutorService cooktop) {
		future = cooktop.schedule(new CookedBacon(this), 10, TimeUnit.SECONDS);
	}

	@Override
	public void run() {
		Program.print(this, "Bacon Placed on Pan");
		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("!!! Failed: " + this);
			e.printStackTrace();
		}
	}

}
