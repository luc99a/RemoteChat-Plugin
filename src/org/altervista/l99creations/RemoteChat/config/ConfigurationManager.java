package org.altervista.l99creations.RemoteChat.config;

import java.util.ArrayList;

public class ConfigurationManager {

	private ArrayList<Config> configs;
	
	public ConfigurationManager() {
		configs = new ArrayList<Config>();
	}
	
	public void registerConfig(Config config) {
		configs.add(config);
	}
	
	public Config getConfigByName(String name) {
		for (Config config : configs) {
			if (name.equals(config.getName())) return config;
		}
		return null;
	}
	
}
