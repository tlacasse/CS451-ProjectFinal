package food.food;

import java.util.Random;

import food.Program;
import food.food.desc.Food;
import food.food.desc.Preparable;

public abstract class AbstractPreparable implements Preparable {

	protected final String startMessage, endMessage;
	protected final int minMakeTime, rangeMakeTime;

	public AbstractPreparable(String startMessage, String endMessage, int minMakeTime, int rangeMakeTime) {
		this.startMessage = startMessage;
		this.endMessage = endMessage;
		this.minMakeTime = minMakeTime;
		this.rangeMakeTime = rangeMakeTime;
	}

	@Override
	public Food call() throws InterruptedException {
		Program.print(this, startMessage);
		// waiting -> making the food
		Thread.sleep(minMakeTime + (new Random()).nextInt(rangeMakeTime));
		Program.print(this, endMessage);
		return this;
	}

}
