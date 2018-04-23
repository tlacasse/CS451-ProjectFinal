package food;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import food.agents.SocketSide;

public final class Kitchen extends SocketSide implements Runnable, AutoCloseable {

	private final ScheduledExecutorService cooking;

	public Kitchen(int port) throws IOException {
		super(port);
		cooking = Executors.newScheduledThreadPool(8);
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
		cooking.shutdown();
	}

	public boolean isClosed() {
		return cooking.isTerminated();
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
