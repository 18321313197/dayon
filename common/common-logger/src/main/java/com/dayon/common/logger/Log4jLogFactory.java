package com.dayon.common.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jLogFactory {
	private static Logger logger =LogManager.getLogger();
	private Log4jLogFactory(){}
	public static Logger getLogger(){
		return logger;
	}
}
