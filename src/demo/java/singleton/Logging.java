package demo.java.singleton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logging {

	
	  private static Logging uniqueInstance;// Static member holds only one instance of the Logging.class
      private String log;// String for message log file.
      private String console;// String for message log console.
      
      //Private constructor : This will prevent anybody else to instantiate the Logging class.
      private Logging()
      {
              log = new String();
              console = new String();
      }
	
   // Static method used as pseudo-constructor (use of the keyword "synchronized" for the multithreaded).
      public static synchronized Logging getInstance()
      {              
              //first check if instance is available
              if (uniqueInstance == null) {
                  //if instance not available, enter synchronized block to create
                  synchronized (Logging.class) {
                      if (uniqueInstance == null) {
                    	  uniqueInstance = new Logging();
                      }
                  }
              }
              return uniqueInstance;
              
              
              
      }
      
      // A method to add a log message.
      public boolean addLog(String log)
      {
              this.log= log+"\n";
             
              
              Date date = new Date() ;
      		SimpleDateFormat dateFormatFic = new SimpleDateFormat("yyyy-MM-dd") ;
      		SimpleDateFormat dateFormatLigneFic = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
      		File file = new File("C:\\Erreur.Log\\"+dateFormatFic.format(date) + ".txt") ; 
      		this.console+="["+dateFormatLigneFic.format(date)+"] "+log+"\n";

      		boolean b;
      		if(file.exists()){
      			try {
      				b=true;
      				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
      				bw.append(System.getProperty("line.separator"));
      				bw.append(String.format("[Date: %s]  - [%s] - [%s] ", dateFormatLigneFic.format(date),"Logging", this.log));
      				bw.flush();
      				bw.close();
      			} catch (IOException e) {
      				b=false;
      				e.printStackTrace();
      			}
      		}
      		else{
      			//System.out.println(file.getAbsolutePath() + " does not exists"+ System.getenv("ProgramFiles"));
      			try {
      				b=true;
      				file.createNewFile();
      				addLog(this.log);

      			} catch (IOException e) {
      				// TODO Auto-generated catch block
      				b=false;
      				e.printStackTrace();
      			}
      		}        

      		return b;  
              
              
      }
      
      //Method that returns all log messages in the console.
      public String afficherLog()
      {
              return console;
      }
      
	
	public static String getStackTraceString(Throwable e, String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(e.toString());
		sb.append("\n");

		StackTraceElement[] stack = e.getStackTrace();
		if (stack != null) {
			for (StackTraceElement stackTraceElement : stack) {
				sb.append(indent);
				sb.append("\tat ");
				sb.append(stackTraceElement.toString());
				sb.append("\n");
			}
		}

		Throwable[] suppressedExceptions = e.getSuppressed();
		// Print suppressed exceptions indented one level deeper.
		if (suppressedExceptions != null) {
			for (Throwable throwable : suppressedExceptions) {
				sb.append(indent);
				sb.append("\tSuppressed: ");
				sb.append(getStackTraceString(throwable, indent + "\t"));
			}
		}

		Throwable cause = e.getCause();
		if (cause != null) {
			sb.append(indent);
			sb.append("Caused by: ");
			sb.append(getStackTraceString(cause, indent));
		}

		return sb.toString();
	}
	
}

