package food.people;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import food.food.desc.Food;

public class ChefGetCookedFood extends AbstractChef implements Callable<Food> {

	private final Future<Food> future;

	public ChefGetCookedFood(Future<Food> future, String whatGetting) {
		super("start getting " + whatGetting, "done getting " + whatGetting, 500, 300);
		this.future = future;
	}

	@Override
	public Food call() throws InterruptedException, ExecutionException {
		act();
		return future.get();
	}

}
