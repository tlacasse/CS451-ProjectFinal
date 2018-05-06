package food.people;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import food.food.desc.Food;
import food.food.desc.SimplePreparable;

public class ChefPrepareFood extends AbstractChef implements Callable<Food> {

	private final SimplePreparable food;

	public ChefPrepareFood(SimplePreparable food, String startMessage, String endMessage, int minMakeTime,
			int rangeMakeTime) {
		super(startMessage, endMessage, minMakeTime, rangeMakeTime);
		this.food = food;
	}

	@Override
	public Food call() throws InterruptedException, ExecutionException {
		act();
		return food;
	}

}
