package food;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

import food.agents.SocketSide;

public class Restaurant implements AutoCloseable {

	private final ServerSocket server;

	private KitchenClient kitchen;

	public Restaurant(int port) throws IOException {
		server = new ServerSocket(port);
	}

	public void start() throws IOException {
		kitchen = new KitchenClient();
		try (Scanner scan = new Scanner(System.in)) {
			while (scan.nextLine().equals("")) {
				kitchen.writeByte((byte) 0);
				kitchen.flush();
			}
		}
	}

	@Override
	public void close() throws IOException {
		kitchen.close();
		server.close();
	}

	/////////////////////////////////////////////////////////////////////////

	private class KitchenClient extends SocketSide {

		public KitchenClient() throws IOException {
			super();
		}

		@Override
		protected void connect(int port) throws IOException {
			if (socket == null || socket.isClosed()) {
				socket = server.accept();
			}
		}

	}

}
