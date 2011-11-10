package com.programmingspring.koans;

public class Singleton {

	private String name;
	
	private static final String DEFAULT_NAME = "default name";
    
	// The single instance
	private static final Singleton INSTANCE = new Singleton(DEFAULT_NAME);
    
    private Singleton(String name) {
    	this.name = name;
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

	public String getName() {
		return name;
	}
}
