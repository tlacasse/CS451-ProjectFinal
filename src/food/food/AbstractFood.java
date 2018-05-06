package food.food;

import java.util.Random;
import java.util.concurrent.Callable;

import food.Program;
import food.food.desc.Food;

public abstract class AbstractFood implements Callable<Food>, Food {

	private final String startMessage, endMessage;
	private final int minMakeTime, rangeMakeTime;

	public AbstractFood(String startMessage, String endMessage, int minMakeTime, int rangeMakeTime) {
		this.startMessage = startMessage;
		this.endMessage = endMessage;
		this.minMakeTime = minMakeTime;
		this.rangeMakeTime = rangeMakeTime;
	}

	@Override
	public Food call() throws InterruptedException {
		final Random rand = new Random();
		Program.print(this, startMessage);
		Thread.sleep(minMakeTime + rand.nextInt(rangeMakeTime)); // cooking
		Program.print(this, endMessage);
		return this;
	}

}
