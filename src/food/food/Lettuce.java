package food.food;

import food.food.desc.Preparable;
import food.people.ChefPrepareFood;

public class Lettuce implements Preparable {

	@Override
	public ChefPrepareFood getChefToPrepare() {
		return new ChefPrepareFood(this, "cutting lettuce", "done cutting lettuce", 1500, 500);
	}

}
