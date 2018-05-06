package food.recipe;

import java.util.LinkedList;
import java.util.List;

import food.Entity;
import food.Restaurant;
import food.food.desc.Food;

public abstract class Recipe extends Entity {

	protected final List<Food> ingredients;

	public Recipe(Restaurant restaurant) {
		super(restaurant);
		ingredients = new LinkedList<>();
	}

	public abstract Food make() throws Exception;

}
