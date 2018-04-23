package food;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Program {

	public static final String LOCALHOST = "127.0.0.1";
	public static final int PORT = 4545;

	public static void main(String[] args) throws IOException, InterruptedException {
		final ExecutorService thread = Executors.newSingleThreadExecutor();
		try (Restaurant restaurant = new Restaurant(PORT); Kitchen kitchen = new Kitchen(PORT)) {
			thread.execute(kitchen);
			restaurant.start();
		} finally {
			thread.shutdown();
		}
	}

	public static void join(Thread t) {
		try {
			t.join();
		} catch (InterruptedException ie) {
			System.out.println("Join Failed: " + t);
			ie.printStackTrace();
		}
	}

	private Program() {
	}

}
