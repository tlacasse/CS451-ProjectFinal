package food.recipe;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import food.Restaurant;
import food.food.Bacon;
import food.food.Bread;
import food.food.Chicken;
import food.food.Lettuce;
import food.food.Meal;
import food.food.Onion;
import food.food.desc.Food;
import food.people.ChefCheckingStatus;
import food.people.ChefGetCookedFood;

public class BaconChickenSandwich extends Recipe {

	public BaconChickenSandwich(Restaurant restaurant) {
		super(restaurant);
	}

	@Override
	public Food make() throws InterruptedException, ExecutionException {
		Future<Food> bread = restaurant.setChefToGetFood((new Bread()).getChefToPrepare());
		ingredients.add(bread.get());

		Future<Food> chicken = restaurant.setChefToSetFoodToCook((new Chicken()).getChefToCook(restaurant)).get();
		Future<Food> bacon = restaurant
				.setChefToSetFoodToCook(Chicken.AVERAGE_COOKING_TIME - Bacon.AVERAGE_COOKING_TIME,
						(new Bacon()).getChefToCook(restaurant))
				.get();

		ScheduledFuture<?> chickenStatus = restaurant.setChefToCheckCookingStatus(1000, 2000,
				new ChefCheckingStatus(chicken, "chicken"));
		ScheduledFuture<?> baconStatus = restaurant.setChefToCheckCookingStatus(
				Chicken.AVERAGE_COOKING_TIME - Bacon.AVERAGE_COOKING_TIME + 1000, 2000,
				new ChefCheckingStatus(chicken, "bacon"));

		Future<Food> cookedChicken = restaurant.setChefToGetFood(Chicken.AVERAGE_COOKING_TIME,
				new ChefGetCookedFood(chicken, "cooked chicken"));
		Future<Food> cookedBacon = restaurant.setChefToGetFood(Chicken.AVERAGE_COOKING_TIME,
				new ChefGetCookedFood(bacon, "cooked bacon"));

		boolean second = false;
		for (int i = 0;; i = second ? i : 1 - i) {
			try {
				Future<Food> check = i == 0 ? cookedChicken : cookedBacon;
				ingredients.add(check.get(500, TimeUnit.MILLISECONDS));
				(i == 0 ? chickenStatus : baconStatus).cancel(false);
				if (second)
					break;
				second = true;
			} catch (TimeoutException e) {
			}
		}

		Future<Food> onion = restaurant.setChefToSetFoodToCook((new Onion()).getChefToCook(restaurant)).get();
		Future<Food> cookedOnion = restaurant.setChefToGetFood(new ChefGetCookedFood(onion, "cooked onion"));
		ingredients.add(cookedOnion.get());

		Future<Food> lettuce = restaurant.setChefToGetFood((new Lettuce()).getChefToPrepare());
		ingredients.add(lettuce.get());

		return new Meal("Bacon & Chicken Sandwich", ingredients);
	}

}
