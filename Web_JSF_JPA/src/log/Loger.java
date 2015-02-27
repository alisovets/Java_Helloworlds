package log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Loger {
	public static enum Event {
		INFO, ERROR, FATALERROR, DB_UPDATE, DB_UPDATE_ERROR, DB_CONNECT, DB_CONNECT_ERROR, DB_READ_ERROR, LOGIN_ATTEMPT, LOGIN
	};

	private static volatile Loger instanse;
	private static Log log = LogFactory.getLog(Loger.class);

	private Loger() {
	}

	public static Loger getInstanse() {
		if (instanse != null) {
			return instanse;
		}
		synchronized (Loger.class) {
			if (instanse != null) {
				return instanse;
			}
			instanse = new Loger();
		}

		return instanse;
	}

	
	synchronized public void log(Event event, String where, String message, Throwable tr) {
		switch (event) {
		case INFO:
		case DB_UPDATE:
		case DB_CONNECT:
		case LOGIN_ATTEMPT:
		case LOGIN:
			log.info(where + ": " + message);
			break;
		case DB_CONNECT_ERROR:
		case DB_UPDATE_ERROR:
		case DB_READ_ERROR:
		case FATALERROR:	
		case ERROR:
			if(tr != null)
				log.error(where + ": " + message, tr);
			else
				log.error(where + ": " + message);
			
			break;
		}
	}
	
	
	public void log(Event event, String where, String message) {
		log(event, where, message, null);
	
	}	

}
