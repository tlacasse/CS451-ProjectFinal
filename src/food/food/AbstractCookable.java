package food.food;

import food.Action;
import food.Restaurant;
import food.food.desc.Cookable;
import food.food.desc.Food;
import food.people.ChefSetFoodToCook;

public abstract class AbstractCookable extends Action implements Cookable {

	private final String name;

	public AbstractCookable(String name, int minMakeTime, int rangeMakeTime) {
		super(name + " placed on pan", name + " is done cooking", minMakeTime, rangeMakeTime);
		this.name = name;
	}

	@Override
	public Food call() throws InterruptedException {
		act();
		return this;
	}

	@Override
	public int averageCookingTime() {
		return minMakeTime + (rangeMakeTime / 2);
	}

	@Override
	public ChefSetFoodToCook getChefToCook(Restaurant restaurant) {
		return new ChefSetFoodToCook(restaurant, this, "putting " + name + " on pan",
				"done putting " + name + " on pan");
	}

}
