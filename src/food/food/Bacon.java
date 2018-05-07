package food.food;

import food.Restaurant;
import food.food.desc.Cookable;
import food.people.ChefSetFoodToCook;

public class Bacon extends AbstractCookable implements Cookable {

	public Bacon() {
		super("bacon placed on pan", "bacon is done cooking", 5000, 2000);
	}

	public static final int AVERAGE_COOKING_TIME = (new Bacon()).averageCookingTime();

	@Override
	public ChefSetFoodToCook getChefToCook(Restaurant restaurant) {
		return new ChefSetFoodToCook(restaurant, this, "putting bacon on pan", "done putting bacon on pan");
	}

}
