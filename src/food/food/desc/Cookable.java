package food.food.desc;

import java.util.concurrent.Callable;

public interface Cookable extends Preparable, Callable<Food> {

	public int averageCookingTime();

}
