package food.food;

import food.food.desc.Cookable;

public class Bacon extends AbstractCookable implements Cookable {

	public Bacon() {
		super("bacon placed on pan", "bacon is done cooking", 5000, 2000);
	}

	public static final int AVERAGE_COOKING_TIME = (new Bacon()).averageCookingTime();

}
