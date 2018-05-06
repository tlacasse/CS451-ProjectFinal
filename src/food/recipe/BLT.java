package food.recipe;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

import food.Entity;
import food.Restaurant;
import food.food.Bacon;
import food.food.Meal;
import food.food.desc.Food;
import food.people.ChefCheckingStatus;
import food.people.ChefGetCookedFood;
import food.people.ChefSetFoodToCook;

public class BLT extends Entity {

	private List<Food> ingredients;

	public BLT(Restaurant restaurant) {
		super(restaurant);
		ingredients = new LinkedList<>();
	}

	public Food get() throws InterruptedException, ExecutionException {
		Future<Food> bacon = restaurant.setChefToSetFoodToCook(
				new ChefSetFoodToCook(restaurant, new Bacon(), "putting bacon on pan", "done putting bacon on pan"))
				.get();

		ScheduledFuture<?> statusChecking = restaurant.setChefToCheckCookingStatus(2000, 2000,
				new ChefCheckingStatus(bacon, "bacon"));

		Future<Food> cookedBacon = restaurant.setChefToGetFood(Bacon.AVERAGE_COOKING_TIME,
				new ChefGetCookedFood(bacon, "cooked bacon"));
		ingredients.add(cookedBacon.get());
		statusChecking.cancel(false);

		return new Meal("BLT", ingredients);
	}

}
