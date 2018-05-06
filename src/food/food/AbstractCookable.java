package food.food;

import java.util.Random;
import java.util.concurrent.Callable;

import food.Program;

public abstract class AbstractCookable implements Callable<Food>, Cookable {

	private final String startMessage, endMessage;

	public AbstractCookable(String startMessage, String endMessage) {
		this.startMessage = startMessage;
		this.endMessage = endMessage;
	}

	@Override
	public Food call() throws InterruptedException {
		final Random rand = new Random();
		Program.print(this, startMessage);
		Thread.sleep(1000 + rand.nextInt(1000)); // cooking
		Program.print(this, endMessage);
		return this;
	}

}
