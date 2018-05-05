package food.food;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bacon implements Runnable {

	private Future<Food> future;

	public Bacon(ScheduledExecutorService cooktop) {
		future = cooktop.schedule(new CookedBacon(this), 10, TimeUnit.SECONDS);
	}

	@Override
	public void run() {
		System.out.println("Bacon Placed on Pan: " + this);
		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("!!! Failed: " + this);
			e.printStackTrace();
		}
	}

}
