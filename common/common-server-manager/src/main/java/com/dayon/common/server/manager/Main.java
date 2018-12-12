package com.dayon.common.server.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	private static Logger logger=LogManager.getLogger();
	public static void main(String[] args) throws ClassNotFoundException {
		
		logger.info(args.length);
		System.out.println("-------------------------");
		for (String arg : args) {
			logger.info(arg);
		}
		System.out.println("-------------------------");
		for (Object item : System.getProperties().keySet()) {
			
			logger.info(item+" = "+System.getProperty(item.toString()));
		}
		System.out.println("-------------------------");
		for (Object item : System.getenv().keySet()) {
			logger.info(item+" = "+System.getenv(item.toString()));
		}
		System.out.println("-------------------------");
		String path=System.getProperty("java.class.path");
		logger.info(path);
		
	}
}
