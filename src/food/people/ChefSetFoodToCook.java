package food.people;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import food.Restaurant;
import food.food.desc.Cookable;
import food.food.desc.Food;

public class ChefSetFoodToCook extends AbstractChef implements Callable<Future<Food>> {

	private final Cookable food;
	private final Restaurant restaurant;

	public ChefSetFoodToCook(Restaurant restaurant, Cookable food, String startMessage, String endMessage) {
		super(startMessage, endMessage, 500, 300);
		this.restaurant = restaurant;
		this.food = food;
	}

	@Override
	public Future<Food> call() throws InterruptedException {
		act();
		return restaurant.cookFood(food);
	}

}
