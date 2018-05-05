package food;

public final class Program {

	public static final int COUNT_CHEFS = 10;
	public static final int COUNT_PANS = 4;
	public static final int COUNT_OVENS = 2;

	public static void main(String[] args) throws InterruptedException {
		try (Restaurant restaurant = new Restaurant()) {
			
			System.out.println(restaurant + " SHUTDOWN");
			restaurant.close();
			while (!restaurant.isClosed()) {
				Thread.sleep(1000);
			}
			System.out.println(restaurant + " CLOSED");
		}
	}

	private Program() {
	}

}
