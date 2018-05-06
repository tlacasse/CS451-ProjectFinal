package food;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import food.food.desc.Cookable;
import food.food.desc.Food;
import food.people.AbstractChef;
import food.people.ChefCheckingStatus;
import food.people.ChefSetFoodToCook;
import food.people.Customer;

public class Restaurant implements AutoCloseable {

	private final List<ExecutorService> pools;
	private final ExecutorService customers, cooktop;
	private final ScheduledExecutorService chefs;

	public Restaurant() {
		pools = new LinkedList<>();
		pools.add(customers = Executors.newCachedThreadPool());
		pools.add(chefs = Executors.newScheduledThreadPool(Program.COUNT_CHEFS));
		pools.add(cooktop = Executors.newFixedThreadPool(Program.COUNT_PANS));
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

	public ScheduledFuture<?> setChefToCheckCookingStatus(int delay, int interval, ChefCheckingStatus chef) {
		return chefs.scheduleAtFixedRate(chef, delay, interval, TimeUnit.MILLISECONDS);
	}

	public <C extends AbstractChef & Callable<Food>> Future<Food> setChefToGetFood(C chef) {
		return setChefToGetFood(0, chef);
	}

	// ChefPrepareFood || ChefGetCookedFood
	public <C extends AbstractChef & Callable<Food>> Future<Food> setChefToGetFood(int delay, C chef) {
		return chefs.schedule(chef, delay, TimeUnit.MILLISECONDS);
	}

	public Future<Future<Food>> setChefToSetFoodToCook(ChefSetFoodToCook chef) {
		return setChefToSetFoodToCook(0, chef);
	}

	public Future<Future<Food>> setChefToSetFoodToCook(int delay, ChefSetFoodToCook chef) {
		return chefs.schedule(chef, delay, TimeUnit.MILLISECONDS);
	}

	public Future<Food> cookFood(Cookable food) {
		return cooktop.submit(food);
	}

}
