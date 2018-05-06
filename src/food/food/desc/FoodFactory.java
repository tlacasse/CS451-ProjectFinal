package food.food.desc;

public interface FoodFactory<F extends Food> {

	public F getFood();

}
