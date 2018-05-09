package food.food;

public class Chicken extends AbstractCookable {

	public Chicken() {
		super("chicken", 10000, 3000);
	}

	public static final int AVERAGE_COOKING_TIME = (new Chicken()).averageCookingTime();

}
