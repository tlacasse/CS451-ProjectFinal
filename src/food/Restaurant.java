package food;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import food.agents.SocketSide;

public class Restaurant implements AutoCloseable {

	private final ServerSocket server;
	private final ExecutorService customers;

	private final List<Client> clients;

	public Restaurant(int port) throws IOException {
		server = new ServerSocket(port);
		clients = new LinkedList<>();
		customers = Executors.newCachedThreadPool();
	}

	public void start() throws IOException {
		Client c = new Client();
		clients.add(c);
		try (Scanner scan = new Scanner(System.in)) {
			String line;
			while (!(line = scan.nextLine()).equals("")) {

			}
		}
	}

	@Override
	public void close() throws IOException {
		for (Client client : clients) {
			client.close();
		}
		server.close();
		customers.shutdown();
	}

	/////////////////////////////////////////////////////////////////////////

	private class Client extends SocketSide {

		public Client() throws IOException {
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
