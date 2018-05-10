package food.people;

import food.Action;

public abstract class AbstractChef extends Action {

	public AbstractChef(String startMessage, String endMessage, int minMakeTime, int rangeMakeTime) {
		super(startMessage, endMessage, minMakeTime, rangeMakeTime);
	}

}
