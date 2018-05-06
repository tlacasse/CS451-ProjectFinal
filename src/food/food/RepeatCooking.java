package food.food;

import food.Entity;
import food.Restaurant;
import food.food.desc.OvenCookable;

//fix to allow not just oven
@Deprecated
public class RepeatCooking extends Entity implements Runnable {

	private final Class<? extends OvenCookable> example;

	public <F extends OvenCookable> RepeatCooking(Restaurant restaurant, Class<F> example) {
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
