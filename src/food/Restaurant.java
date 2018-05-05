package food;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Restaurant implements AutoCloseable {

	private final List<ExecutorService> pools;
	private final ExecutorService customers, chefs;
	private final ScheduledExecutorService cooktop, ovens;

	public Restaurant() {
		pools = new LinkedList<>();
		pools.add(customers = Executors.newCachedThreadPool());
		pools.add(chefs = Executors.newFixedThreadPool(Program.COUNT_CHEFS));
		pools.add(cooktop = Executors.newScheduledThreadPool(Program.COUNT_PANS));
		pools.add(ovens = Executors.newScheduledThreadPool(Program.COUNT_OVENS));
	}

	public ExecutorService customers() {
		return customers;
	}

	public ExecutorService chefs() {
		return chefs;
	}

	public ScheduledExecutorService cooktop() {
		return cooktop;
	}

	public ScheduledExecutorService ovens() {
		return ovens;
	}

	@Override
	public void close() {
		for (ExecutorService pool : pools) {
			pool.shutdown();
		}
	}

	public boolean isClosed() {
		for (ExecutorService pool : pools) {
			if (!pool.isTerminated()) {
				return false;
			}
		}
		return true;
	}

}
