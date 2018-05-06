package food.food;

import java.util.List;

import food.food.desc.Food;

public class Meal implements Food {

	public final List<Food> ingredients;
	public final String name;

	public Meal(String name, List<Food> ingredients) {
		this.ingredients = ingredients;
		this.name = name;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder(name);
		sb.append(" [");
		for (Food food : ingredients) {
			sb.append(food.getClass().getSimpleName()).append(',');
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');
		return sb.toString();
	}

}
