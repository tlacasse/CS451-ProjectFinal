package food;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import food.cooking.Bacon;

public final class Restaurant implements AutoCloseable {

	private final ScheduledExecutorService cooking;

	public Restaurant() {
		cooking = Executors.newScheduledThreadPool(8);
		cooking.scheduleAtFixedRate(new Bacon(), 0, 1, TimeUnit.SECONDS);
	}

	@Override
	public void close() throws Exception {
		cooking.shutdown();
	}

	public boolean isClosed() {
		return cooking.isTerminated();
	}

}
