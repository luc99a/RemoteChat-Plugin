package org.altervista.l99creations.RemoteChat.config;

public class ConfigElement {
	
	private String name;
	private String path;
	private DataType type;
	
	public ConfigElement(String name, String path, DataType type) {
		this.name = name;
		this.path = path;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public DataType getType() {
		return type;
	}

}
