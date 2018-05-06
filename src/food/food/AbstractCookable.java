package food.food;

import food.food.desc.Cookable;

public abstract class AbstractCookable extends AbstractPreparable implements Cookable {

	public AbstractCookable(String startMessage, String endMessage, int minMakeTime, int rangeMakeTime) {
		super(startMessage, endMessage, minMakeTime, rangeMakeTime);
	}

	@Override
	public int averageCookingTime() {
		return minMakeTime + (rangeMakeTime / 2);
	}

}
