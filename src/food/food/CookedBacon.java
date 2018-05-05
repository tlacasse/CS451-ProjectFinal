package food.food;

import java.util.concurrent.Callable;

public class CookedBacon implements Callable<Food>, Food {

	@Override
	public CookedBacon call() throws Exception {
		System.out.println("Bacon Cooked.");
		return this;
	}

}
