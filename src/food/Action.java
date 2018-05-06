package food;

import java.util.Random;

public abstract class Action {

	protected final String startMessage, endMessage;
	protected final int minMakeTime, rangeMakeTime;

	public Action(String startMessage, String endMessage, int minMakeTime, int rangeMakeTime) {
		this.startMessage = startMessage;
		this.endMessage = endMessage;
		this.minMakeTime = minMakeTime;
		this.rangeMakeTime = rangeMakeTime;
	}

	protected final void act() throws InterruptedException {
		Program.print(this, startMessage);
		// waiting -> making the food, walking to station, gathering food, etc
		Thread.sleep(minMakeTime + (new Random()).nextInt(rangeMakeTime));
		Program.print(this, "\t" + endMessage);
	}

}
