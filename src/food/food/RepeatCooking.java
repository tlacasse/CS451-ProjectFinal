package food.food;

import food.Entity;
import food.Restaurant;

public class RepeatCooking extends Entity implements Runnable {

	private final Class<? extends Cookable> example;

	public <F extends Cookable> RepeatCooking(Restaurant restaurant, Class<F> example) {
		super(restaurant);
		this.example = example;
	}

	@Override
	public void run() {
		try {
			restaurant.cookInOven(example.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("Thread failed: " + this);
			e.printStackTrace();
		}
	}

}
