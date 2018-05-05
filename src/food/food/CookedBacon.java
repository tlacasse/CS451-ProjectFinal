package food.food;

import java.util.concurrent.Callable;

public class CookedBacon implements Callable<Food>, Food {

	private Bacon bacon;

	public CookedBacon(Bacon bacon) {
		this.bacon = bacon;
	}

	@Override
	public CookedBacon call() throws Exception {
		System.out.println("Bacon Cooked: " + bacon);
		return this;
	}

}
