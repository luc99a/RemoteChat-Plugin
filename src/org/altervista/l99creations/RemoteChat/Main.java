package org.altervista.l99creations.RemoteChat;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.altervista.l99creations.RemoteChat.config.Config;
import org.altervista.l99creations.RemoteChat.config.ConfigElement;
import org.altervista.l99creations.RemoteChat.config.ConfigurationManager;
import org.altervista.l99creations.RemoteChat.config.DataType;
import org.altervista.l99creations.RemoteChat.logger.RemoteChatLogger;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private static Logger logger = Logger.getLogger("Minecraft");
	
	private static ConfigurationManager configurationManager = new ConfigurationManager();
	private RemoteChatServer server;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		configurationManager.registerConfig(new Config("Config", getConfig()));
		configurationManager.getConfigByName("Config").addElement(new ConfigElement("Server Port", "port", DataType.INT));
		configurationManager.getConfigByName("Config").addElement(new ConfigElement("Username", "username", DataType.STRING));
		configurationManager.getConfigByName("Config").addElement(new ConfigElement("Password", "password", DataType.STRING));
		String username = configurationManager.getConfigByName("Config").getString("Username");
		String password = configurationManager.getConfigByName("Config").getString("Password");
		int port = configurationManager.getConfigByName("Config").getInt("Server Port");
		File pluginFolder = getDataFolder();
		File logFolder = new File(pluginFolder, "Logs");
		if (!pluginFolder.exists()) pluginFolder.mkdir();
		if (!logFolder.exists()) logFolder.mkdir();
		if (username.equals("RemoteChat") || password.equals("RemoteChat")) {
			severe("You haven't changed server credentials. This could cause some spam in your chat if someone know you are using this plugin");
		}
		try {
			server = new RemoteChatServer(port, username, password, new RemoteChatLogger(logFolder));
			info("RemoteChat server started on port " + port);
			info("Take a look at PHP API to implement some features on your website");
		} catch (IOException e) {
			severe("Cannot create the server: IOException");
			e.printStackTrace();
			severe("Disabling RemoteChat");
			disable();
		}
		server.start();
	}
	
	@Override
	public void onDisable() {
		info("RemoteChat is disabled");
		warning("If you implemented some features on your website using RemoteChat and its PHP API they won't work");
	}
	
	public RemoteChatServer getRemoteChatServer() {
		return server;
	}
	
	public void disable() {
		getServer().getPluginManager().disablePlugin(this);
	}
	
	public static void severe(String s) {
		logger.severe("[RemoteChat] " + s);
	}
	
	public static void warning(String s) {
		logger.warning("[RemoteChat] " + s);
	}
	
	public static void info(String s) {
		logger.info("[RemoteChat] " + s);
	}

}
