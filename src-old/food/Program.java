package food;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import food.Config.Key;

public final class Program {

	public static final String LOCALHOST = "127.0.0.1";
	public static final int PORT = 4545;

	public static void main(String[] args) throws IOException, InterruptedException {
		try (Scanner scan = new Scanner(System.in)) {
			final Config config = Config.create(scan, CONFIG_KEYS);
			final ExecutorService thread = Executors.newSingleThreadExecutor();
			try (Restaurant restaurant = new Restaurant(PORT); Kitchen kitchen = new Kitchen(PORT, config)) {
				thread.execute(kitchen);
				restaurant.start();
			} finally {
				thread.shutdown();
			}
		}
	}

	public static final Key COUNT_CHEFS, COUNT_OVENS, COUNT_PANS;
	public static final List<Key> CONFIG_KEYS;

	static {
		CONFIG_KEYS = new LinkedList<>();
		CONFIG_KEYS.add(COUNT_CHEFS = new Key("Number of Chefs", 10));
		CONFIG_KEYS.add(COUNT_OVENS = new Key("Number of Ovens", 2));
		CONFIG_KEYS.add(COUNT_PANS = new Key("Number of Pans", 4));
	}

	public static void join(Thread t) {
		try {
			t.join();
		} catch (InterruptedException ie) {
			System.out.println("Join Failed: " + t);
			ie.printStackTrace();
		}
	}

	public static boolean isYes(String line) {
		if (line == null) {
			return false;
		}
		if (line.equals("")) {
			return false;
		}
		line = line.toLowerCase();
		if (line.charAt(0) == 'y') {
			return true;
		}
		return false;
	}

	private Program() {
	}

}
