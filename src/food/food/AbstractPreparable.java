package food.food;

import food.Action;
import food.food.desc.Food;
import food.food.desc.Preparable;

public abstract class AbstractPreparable extends Action implements Preparable {

	public AbstractPreparable(String startMessage, String endMessage, int minMakeTime, int rangeMakeTime) {
		super(startMessage, endMessage, minMakeTime, rangeMakeTime);
	}

	@Override
	public Food call() throws InterruptedException {
		act();
		return this;
	}

}
