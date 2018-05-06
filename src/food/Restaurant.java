package food;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import food.food.Cookable;
import food.food.Food;
import food.people.Customer;

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

	public void addCustomer(Customer customer) {
		customers.execute(customer);
	}

	public Future<Food> cookOnCooktop(Cookable cookable) {
		return cooktop.submit(cookable);
	}

}
