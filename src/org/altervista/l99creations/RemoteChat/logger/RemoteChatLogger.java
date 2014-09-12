package org.altervista.l99creations.RemoteChat.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.altervista.l99creations.RemoteChat.Main;

public class RemoteChatLogger implements ILogger {
	
	private File logFile;
	
	private boolean loaded = true;
	
	public RemoteChatLogger(File folder) {
		createLogFile(folder);
	}

	public void log(String s) {
		if (!loaded) return;
		try {
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(logFile, true));
			fileWriter.append(s);
			fileWriter.newLine();
			fileWriter.close();
		} catch (IOException e) {
			Main.warning("Failed when logging: " + s);
		}
	}
	
	private void createLogFile(File folder) {
		logFile = new File(folder, "log.txt");
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				Main.warning("Cannot create logger file");
				Main.warning("Stopping logger");
				Main.severe("Stack trace:");
				e.printStackTrace();
				loaded = false;
			}
		}
	}

}
