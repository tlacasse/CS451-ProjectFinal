package food;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import food.agents.SocketSide;

public final class Kitchen extends SocketSide implements Runnable, AutoCloseable {

	private final ScheduledExecutorService cooking;
	private final ExecutorService chefs;
	private final List<ExecutorService> pools;

	public Kitchen(int port) throws IOException {
		super(port);
		pools = new LinkedList<>();
		pools.add(cooking = Executors.newScheduledThreadPool(8));
		pools.add(chefs = Executors.newFixedThreadPool(10));
	}

	@Override
	protected void connect(int port) throws IOException {
		if (socket == null || socket.isClosed()) {
			socket = new Socket(Program.LOCALHOST, port);
		}
	}

	@Override
	public void close() throws IOException {
		super.close();
		for (ExecutorService pool : pools) {
			pool.shutdown();
		}
	}

	public boolean isClosed() {
		for (ExecutorService pool : pools) {
			if (!pool.isTerminated()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void run() {
		try {
			for (;;) {
				final byte mode = readByte();
				switch (mode) {
				default:
					System.out.println(mode);
					return;
				}
			}
		} catch (IOException ioe) {
			System.out.println("!!! Thread Failed: + " + this);
			ioe.printStackTrace();
		}
	}

}
