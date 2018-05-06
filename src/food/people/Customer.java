package food.people;

import java.util.concurrent.ExecutionException;

import food.Entity;
import food.Program;
import food.Restaurant;
import food.food.desc.Food;
import food.recipe.BLT;

public class Customer extends Entity implements Runnable {

	public Customer(Restaurant restaurant) {
		super(restaurant);
	}

	@Override
	public void run() {
		Program.print(this, "entered Restaurant");
		try {
			Food meal = (new BLT(restaurant)).make();
			Program.print(this, "got food: " + meal);
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Customer Failed: " + e);
			e.printStackTrace();
		}

	}

}
