package food.recipe;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

import food.Restaurant;
import food.food.Bacon;
import food.food.Bread;
import food.food.Lettuce;
import food.food.Meal;
import food.food.Tomato;
import food.food.desc.Food;
import food.people.ChefCheckingStatus;
import food.people.ChefGetCookedFood;
import food.people.ChefPrepareFood;
import food.people.ChefSetFoodToCook;

public class BLT extends Recipe {

	public BLT(Restaurant restaurant) {
		super(restaurant);
	}

	@Override
	public Food make() throws InterruptedException, ExecutionException {
		Future<Food> bread = restaurant
				.setChefToGetFood(new ChefPrepareFood(new Bread(), "getting bread", "done getting bread", 200, 100));
		ingredients.add(bread.get());

		Future<Food> bacon = restaurant.setChefToSetFoodToCook(
				new ChefSetFoodToCook(restaurant, new Bacon(), "putting bacon on pan", "done putting bacon on pan"))
				.get();

		ScheduledFuture<?> statusChecking = restaurant.setChefToCheckCookingStatus(2000, 2000,
				new ChefCheckingStatus(bacon, "bacon"));

		Future<Food> cookedBacon = restaurant.setChefToGetFood(Bacon.AVERAGE_COOKING_TIME,
				new ChefGetCookedFood(bacon, "cooked bacon"));
		ingredients.add(cookedBacon.get());
		statusChecking.cancel(false);

		Future<Food> lettuce = restaurant.setChefToGetFood(
				new ChefPrepareFood(new Lettuce(), "cutting lettuce", "done cutting lettuce", 1500, 500));
		Future<Food> tomato = restaurant.setChefToGetFood(
				new ChefPrepareFood(new Tomato(), "cutting tomato slices", "done cutting tomato slices", 1500, 500));
		ingredients.add(lettuce.get());
		ingredients.add(tomato.get());

		return new Meal("BLT", ingredients);
	}

}
