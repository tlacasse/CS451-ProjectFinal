package food.food;

import food.food.desc.Preparable;
import food.people.ChefPrepareFood;

public class Tomato implements Preparable {

	@Override
	public ChefPrepareFood getChefToPrepare() {
		return new ChefPrepareFood(this, "cutting tomato slices", "done cutting tomato slices", 1500, 500);
	}

}
