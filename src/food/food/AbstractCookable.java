package food.food;

import java.util.Random;
import java.util.concurrent.Callable;

import food.Program;

public abstract class AbstractCookable implements Callable<Food>, Cookable {

	private final String startMessage, endMessage;
	private final int minCookTime, rangeCookTime;

	public AbstractCookable(String startMessage, String endMessage, int minCookTime, int rangeCookTime) {
		this.startMessage = startMessage;
		this.endMessage = endMessage;
		this.minCookTime = minCookTime;
		this.rangeCookTime = rangeCookTime;
	}

	@Override
	public Food call() throws InterruptedException {
		final Random rand = new Random();
		Program.print(this, startMessage);
		Thread.sleep(minCookTime + rand.nextInt(rangeCookTime)); // cooking
		Program.print(this, endMessage);
		return this;
	}

}
