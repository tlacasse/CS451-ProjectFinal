package food.people;

import java.util.Random;

import food.Entity;
import food.Program;
import food.Restaurant;
import food.food.desc.Food;
import food.recipe.BLT;
import food.recipe.BaconChickenSandwich;
import food.recipe.Recipe;

public class Customer extends Entity implements Runnable {

	public Customer(Restaurant restaurant) {
		super(restaurant);
	}

	@Override
	public void run() {
		Program.print(this, "entered Restaurant");
		try {
			Food meal = pickMeal().make();
			Program.print(this, "got food: " + meal);
		} catch (Exception e) {
			System.out.println("Customer Failed: " + e);
			e.printStackTrace();
		}
	}

	private Recipe pickMeal() {
		switch ((new Random()).nextInt(2)) {
		case 0:
			return new BLT(restaurant);
		case 1:
			return new BaconChickenSandwich(restaurant);
		}
		return null;
	}

}
