package food.food;

import food.food.desc.CooktopCookable;

public class Bacon extends AbstractCookable implements CooktopCookable {

	public Bacon() {
		super("Bacon placed on pan", "Bacon is done cooking", 2000, 1000);
	}

}
