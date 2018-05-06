package food.people;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import food.Entity;
import food.Program;
import food.Restaurant;
import food.food.Bacon;
import food.food.desc.Food;

public class Customer extends Entity implements Runnable {

	public Customer(Restaurant restaurant) {
		super(restaurant);
	}

	@Override
	public void run() {
		Program.print(this, "Entered Restaurant");
		Future<Food> future = restaurant.cookOnCooktop(new Bacon());
		Food food = null;
		try {
			food = future.get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Thread failed: " + this);
			e.printStackTrace();
		}
		Program.print(this, "Got food: " + food);
	}

}
