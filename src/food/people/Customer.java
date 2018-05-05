package food.people;

import food.Program;

public class Customer implements Runnable {

	@Override
	public void run() {
		Program.print(this, "Entered Restaurant");
	}

}
