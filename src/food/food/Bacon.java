package food.food;

public class Bacon extends AbstractCookable {

	public Bacon() {
		super("bacon", 5000, 2000);
	}

	public static final int AVERAGE_COOKING_TIME = (new Bacon()).averageCookingTime();

}
