package food.food;

import java.util.concurrent.Callable;

public interface Cookable extends Food, Callable<Food> {

}
