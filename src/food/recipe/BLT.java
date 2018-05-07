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

public class BLT extends Recipe {

	public BLT(Restaurant restaurant) {
		super(restaurant);
	}

	@Override
	public Food make() throws InterruptedException, ExecutionException {
		Future<Food> bread = restaurant.setChefToGetFood((new Bread()).getChefToPrepare());
		ingredients.add(bread.get());

		Future<Food> bacon = restaurant.setChefToSetFoodToCook((new Bacon()).getChefToCook(restaurant)).get();

		ScheduledFuture<?> statusChecking = restaurant.setChefToCheckCookingStatus(2000, 2000,
				new ChefCheckingStatus(bacon, "bacon"));

		Future<Food> cookedBacon = restaurant.setChefToGetFood(Bacon.AVERAGE_COOKING_TIME,
				new ChefGetCookedFood(bacon, "cooked bacon"));
		ingredients.add(cookedBacon.get());
		statusChecking.cancel(false);

		Future<Food> lettuce = restaurant.setChefToGetFood((new Lettuce()).getChefToPrepare());
		Future<Food> tomato = restaurant.setChefToGetFood((new Tomato()).getChefToPrepare());
		ingredients.add(lettuce.get());
		ingredients.add(tomato.get());

		return new Meal("BLT", ingredients);
	}

}
