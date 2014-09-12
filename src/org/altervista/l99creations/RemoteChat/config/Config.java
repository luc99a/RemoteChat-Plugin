package org.altervista.l99creations.RemoteChat.config;

import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
	
	private String name;
	private FileConfiguration file;
	private ArrayList<ConfigElement> elements;
	
	public Config(String name, FileConfiguration file) {
		this.name = name;
		this.file = file;
		elements = new ArrayList<ConfigElement>();
	}
	
	public String getName() {
		return name;
	}
	
	public void addElement(ConfigElement element) {
		elements.add(element);
	}
	
	public Object get(String name) throws ConfigUnregisteredElementException {
		ConfigElement element = getElementByName(name);
		return file.get(element.getPath());
	}
	
	public int getInt(String name) throws ConfigTypeException {
		ConfigElement element = getElementByName(name);
		if (element.getType() != DataType.INT) throw new ConfigTypeException();
		return file.getInt(element.getPath());
	}
	
	public double getDouble(String name) throws ConfigTypeException {
		ConfigElement element = getElementByName(name);
		if (element.getType() != DataType.DOUBLE) throw new ConfigTypeException();
		return file.getDouble(element.getPath());
	}
	
	public boolean getBoolean(String name) throws ConfigTypeException {
		ConfigElement element = getElementByName(name);
		if (element.getType() != DataType.BOOLEAN) throw new ConfigTypeException();
		return file.getBoolean(element.getPath());
	}
	
	public String getString(String name) throws ConfigTypeException {
		ConfigElement element = getElementByName(name);
		if (element.getType() != DataType.STRING) throw new ConfigTypeException();
		return file.getString(element.getPath());
	}
	
	public FileConfiguration getConfigFile() {
		return file;
	}
	
	private ConfigElement getElementByName(String name) throws ConfigUnregisteredElementException {
		for (ConfigElement element : elements) {
			if (name.equals(element.getName())) {
				return element;
			}
		}
		throw new ConfigUnregisteredElementException();
	}

}
