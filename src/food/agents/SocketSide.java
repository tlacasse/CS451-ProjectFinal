package food.agents;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class SocketSide implements AutoCloseable {

	protected Socket socket;

	private final InputStream in;
	private final OutputStream out;
	private final DataInputStream reader;
	private final DataOutputStream writer;

	public SocketSide(int port) throws IOException {
		connect(port);
		if (socket != null) {
			in = socket.getInputStream();
			out = socket.getOutputStream();
			reader = new DataInputStream(in);
			writer = new DataOutputStream(out);
		} else {
			in = null;
			out = null;
			reader = null;
			writer = null;
		}
	}

	public SocketSide() throws IOException {
		this(-1);
	}

	protected abstract void connect(int port) throws IOException;

	@Override
	public void close() throws IOException {
		reader.close();
		writer.close();
		if (socket != null && !socket.isClosed()) {
			socket.close();
		}
	}

	public void flush() throws IOException {
		writer.flush();
	}

	// writing

	public void writeInt(int x) throws IOException {
		writer.writeInt(x);
	}

	public void writeByte(byte x) throws IOException {
		writer.writeByte(x);
	}

	// reading, blocks until data exists

	public int readInt() throws IOException {
		return reader.readInt();
	}

	public byte readByte() throws IOException {
		return reader.readByte();
	}

}
