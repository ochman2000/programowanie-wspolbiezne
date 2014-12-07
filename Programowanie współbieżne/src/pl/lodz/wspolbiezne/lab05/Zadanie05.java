package pl.lodz.wspolbiezne.lab05;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Zadanie05 {
	public static void main(String[] args) {

		Logger.getGlobal().setUseParentHandlers(false);
	    Handler conHdlr = new ConsoleHandler();
	    conHdlr.setFormatter(new Formatter() {
	      public String format(LogRecord record) {
	    	  StringBuilder sb = new StringBuilder();
	    	  sb.append("[");
	    	  sb.append(record.getLevel());
	    	  sb.append("] ");
	    	  final Calendar cal = Calendar.getInstance();
	    	  cal.setTimeInMillis(record.getMillis());
	    	  sb.append(new SimpleDateFormat("HH:mm:ss:SSS").format(cal.getTime()));
	    	  sb.append("\tmethod: ");
	    	  sb.append(record.getSourceMethodName());
	    	  sb.append("()\t");
	    	  sb.append(record.getMessage());
	    	  sb.append("\n");
	    	  return sb.toString();
	      }
	    });
	    Logger.getGlobal().addHandler(conHdlr);
		Logger.getGlobal().setLevel(Level.FINE);
		
		Zasób b = new Zasób(4);
		Klient p = new Klient(b);
		Serwer c = new Serwer(b);

		p.start();
		c.start();
	}
}