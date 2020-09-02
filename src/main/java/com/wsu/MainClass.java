package com.wsu;

import java.util.logging.Logger;

/**Class to provide the entry into the java application*/
public class MainClass {
	
	/**The class logger*/
	private static final Logger logger = Logger.getLogger("MainClass");
	
	/**Constructor*/
	public MainClass() {	
	}
	
	/**Method for starting the java application*/
	public static void main(String[] args) {
		logger.info("Entering Main Class");
    }
	
	public boolean beTrue() {
		return true;
	}
	
	public String isMainClass() {
		return "MainClass";
	}

}
