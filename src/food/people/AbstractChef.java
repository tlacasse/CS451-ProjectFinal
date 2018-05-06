package food.people;

import java.util.concurrent.ConcurrentLinkedQueue;

import food.Action;
import food.Program;

public abstract class AbstractChef extends Action {

	private static final ConcurrentLinkedQueue<Integer> IDS;

	static {
		IDS = new ConcurrentLinkedQueue<>();
		for (int i = 0; i < Program.COUNT_CHEFS; i++) {
			IDS.offer('A' + i);
		}
	}

	public AbstractChef(String startMessage, String endMessage, int minMakeTime, int rangeMakeTime) {
		super(startMessage, endMessage, minMakeTime, rangeMakeTime);
	}

}
