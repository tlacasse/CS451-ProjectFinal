package food.food;

import food.Action;
import food.food.desc.Cookable;
import food.food.desc.Food;

public abstract class AbstractCookable extends Action implements Cookable {

	public AbstractCookable(String startMessage, String endMessage, int minMakeTime, int rangeMakeTime) {
		super(startMessage, endMessage, minMakeTime, rangeMakeTime);
	}

	@Override
	public Food call() throws InterruptedException {
		act();
		return this;
	}

	@Override
	public int averageCookingTime() {
		return minMakeTime + (rangeMakeTime / 2);
	}

}
