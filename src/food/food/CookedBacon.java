package food.food;

import java.util.concurrent.Callable;

import food.Program;

public class CookedBacon implements Callable<Food>, Food {

	private Bacon bacon;

	public CookedBacon(Bacon bacon) {
		this.bacon = bacon;
	}

	@Override
	public CookedBacon call() throws Exception {
		Program.print(this, "Bacon Cooked: " + bacon);
		return this;
	}

}
