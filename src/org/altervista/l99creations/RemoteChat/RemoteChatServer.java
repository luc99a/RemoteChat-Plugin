package org.altervista.l99creations.RemoteChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.altervista.l99creations.RemoteChat.logger.ILogger;
import org.bukkit.Bukkit;

public class RemoteChatServer implements Runnable {
	
	private String username;
	private String password;
	private Thread serverThread;
	private ServerSocket server;
	private boolean running = false;
	
	private ILogger logger;
	
	public RemoteChatServer(int port, String username, String password, ILogger logger) throws IOException {
		this.username = username;
		this.password = password;
		serverThread = new Thread(this, "RemoteChatServerThread");
		server = new ServerSocket(port);
		this.logger = logger;
	}
	
	public synchronized void start() {
		running = true;
		serverThread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			serverThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isRunning() {
		return running;
	}

	public void run() {
		while (running) {
			Socket socket;
			BufferedReader reader;
			char[] buffer = new char[1024];
			try {
				socket = server.accept();
				socket.setSoTimeout(2000);
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				reader.read(buffer);
				String s = String.copyValueOf(buffer);
				processString(s);
				reader.close();
				socket.close();
			} catch (SocketTimeoutException ex) {	
				logger.log("Socket timeout");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void processString(String s) {
		logger.log(s.trim());
		String credentialsString = s.split("@")[0];
		String[] credentials = credentialsString.split(":");
		if (credentials.length != 2) return;
		String username = credentials[0];
		String password = credentials[1];
		if (username.equals(this.username) && password.equals(this.password)) {
			int credentialsLength = credentialsString.length();
			Bukkit.broadcastMessage(s.substring(credentialsLength + 1).trim());
		}
	}

}
