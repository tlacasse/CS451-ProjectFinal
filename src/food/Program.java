package food;

import java.util.Scanner;

import food.people.Customer;

public final class Program {

	public static final int COUNT_CHEFS = 10;
	public static final int COUNT_PANS = 4;
	public static final int COUNT_OVENS = 2;

	public static void main(String[] args) throws InterruptedException {
		try (Scanner scan = new Scanner(System.in); Restaurant restaurant = new Restaurant()) {
			while (scan.nextLine().equals("")) {
				restaurant.customers().execute(new Customer());
			}
			System.out.println(restaurant + " SHUTDOWN");
			restaurant.close();
			while (!restaurant.isClosed()) {
				Thread.sleep(1000);
			}
			System.out.println(restaurant + " CLOSED");
		}
	}

	public static void print(Object o, String m) {
		System.out.println(
				(o.toString() + "                                                      ").substring(0, 35) + m);
	}

	private Program() {
	}

}
