package food.people;

import java.util.concurrent.Future;

import food.Program;

public class ChefCheckingStatus extends AbstractChef implements Runnable {

	private final Future<?> future;
	private final String whatWaitingFor;

	public ChefCheckingStatus(Future<?> future, String whatWaitingFor) {
		super("checking " + whatWaitingFor + " status", "done checking " + whatWaitingFor + " status", 100, 50);
		this.future = future;
		this.whatWaitingFor = whatWaitingFor;
	}

	@Override
	public void run() {
		try {
			act();
		} catch (InterruptedException ie) {
		}
		Program.print(this, "\t" + whatWaitingFor + (future.isDone() ? " is ready" : " is not ready"));
	}

}
