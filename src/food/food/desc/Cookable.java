package food.food.desc;

import java.util.concurrent.Callable;

import food.Restaurant;
import food.people.ChefSetFoodToCook;

public interface Cookable extends Food, Callable<Food> {

	public int averageCookingTime();

	public ChefSetFoodToCook getChefToCook(Restaurant restaurant);

}
