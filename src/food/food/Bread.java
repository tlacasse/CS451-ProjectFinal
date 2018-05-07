package food.food;

import food.food.desc.Preparable;
import food.people.ChefPrepareFood;

public class Bread implements Preparable {

	@Override
	public ChefPrepareFood getChefToPrepare() {
		return new ChefPrepareFood(this, "getting bread", "done getting bread", 200, 100);
	}

}
