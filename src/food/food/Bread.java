package food.food;

import food.food.desc.OvenCookable;

public class Bread extends AbstractPreparable implements OvenCookable {

	public Bread() {
		super("Bread placed in oven", "Bread is done cooking", 7500, 5000);
	}

}
