package food.food.desc;

import java.util.concurrent.Callable;

public interface Preparable extends Food, Callable<Food> {

}
