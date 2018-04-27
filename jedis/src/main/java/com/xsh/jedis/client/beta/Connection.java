package com.xsh.jedis.client.beta;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.xsh.jedis.client.commands.Command;

public class Connection {

	private Socket socket;

	private InputStream inputStream;

	private OutputStream outputStream;

	private static final byte[][] EMPTY_ARGS = new byte[0][];

	public Connection(String host, int port) throws IOException {
		socket = new Socket(host, port);
		inputStream = socket.getInputStream();
		outputStream = socket.getOutputStream();
	}

	public String sendCommand(Command command) {
		return sendCommand(command, EMPTY_ARGS);
	}
	
	public String sendCommand(Command command, final byte[]... args) {
		// 1.组装命令请求
		StringBuilder resp = new StringBuilder();

		// 1.1请求由几部分数据组成
		resp.append("*").append(args.length + 1).append("\r\n");

		// 1.2第一部分
		resp.append("$").append(command.name().length()).append("\r\n");// 第一1部分数据的长度
		resp.append(command.name()).append("\r\n");// 第一部分的数据值

		// 1.3第2部分
		for (int i = 0; i < args.length; i++) {
			byte[] name = args[i];
			resp.append("$").append(name.length).append("\r\n");// 第2个部分数据的长度
			resp.append(new String(name)).append("\r\n");// 第2部分的数据值
		}

		System.out.println("resp:::" + resp.toString());

		// 3.获取redis的响应
		byte[] response = null;
		try {
			// 2.发生命令到redis-server
			outputStream.write(resp.toString().getBytes());

			response = new byte[1024];
			inputStream.read(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(response);
	}

	public void close() {
		disconnect();
	}

	public void disconnect() {
		if (isConnected()) {
			try {
				outputStream.flush();
				socket.close();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			} finally {
				closeQuietly(socket);
			}
		}
	}

	public boolean isConnected() {
		return socket != null && socket.isBound() && !socket.isClosed() && socket.isConnected()
				&& !socket.isInputShutdown() && !socket.isOutputShutdown();
	}

	public static void closeQuietly(Socket sock) {
		// It's same thing as Apache Commons - IOUtils.closeQuietly()
		if (sock != null) {
			try {
				sock.close();
			} catch (IOException e) {
				// ignored
			}
		}
	}
}
