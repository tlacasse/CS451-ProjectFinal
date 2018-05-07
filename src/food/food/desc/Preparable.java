package food.food.desc;

import food.people.ChefPrepareFood;

public interface Preparable extends Food {

	public ChefPrepareFood getChefToPrepare();

}
