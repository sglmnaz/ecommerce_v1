package com.synclab.ecommerce.utility;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {
	
	private static final Logger log = Logger.getLogger("log");
	
	public static void print(int level,String message) {
		switch (level) {
		case 0:
			log.log(Level.FINE,message);
			break;
		case 1:
			log.log(Level.WARNING,message);
			break;
		case 2:
			log.log(Level.SEVERE,message);
			break;
		}
		
	}

}
