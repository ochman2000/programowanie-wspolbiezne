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
	    	  sb.append("\tthread: ");
	    	  sb.append(record.getThreadID());
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
		
		Zasób z = new Zasób(80); //monitor zawieraj¹cy logikê
		Thread[] w¹tki = new Thread[21];
		
		//PIERWSZA GRUPA W¥TKÓW (U¯YWA ZASOBÓW DZIESIÊCIOKROTNIE CZÊŒCIEJ I D£U¯EJ)
		for (int i=0; i<10; i++) {
			//duration = 100ms
			//calls = 100;
			w¹tki[i] = new Klient(z, 100, 100);
		}
		//DRUGA GRUPA W¥TKÓW (SZYBCIUTKO I MALUTKO)
		for (int i=10; i<20; i++) {
			//duration = 10ms
			//calls = 10;
			w¹tki[i] = new Klient(z, 10, 10);
		}
		//TRZECIA GRUPA W¥TKÓW (A W ZASADZIE W¥TEK)
			//duration = 0ms;
			//calls = 15;
		w¹tki[20] = new Serwer(z);

		for (Thread t : w¹tki) { t.start(); }
	}
}