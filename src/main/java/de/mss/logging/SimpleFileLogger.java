package de.mss.logging;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;

public class SimpleFileLogger extends BaseLogger {

   protected BufferedOutputStream out            = null;
   protected String               logDir         = ".";
   protected String               filename       = "logfile.log";
   protected String               currentLogfile = null;

   protected static final int     BUFFER_SIZE    = 65535;


   public SimpleFileLogger() {
      super();
      initLogFile();
   }


   public SimpleFileLogger(Level l) {
      super(l);
      initLogFile();
   }


   public SimpleFileLogger(String name) {
      super(name);
      initLogFile();
   }


   public SimpleFileLogger(String name, Level l) {
      super(name, l);
      initLogFile();
   }


   public SimpleFileLogger(String logDir, String filename) {
      super();
      this.logDir = logDir;
      this.filename = filename;
      initLogFile();
   }


   public SimpleFileLogger(String name, String logDir, String filename) {
      super(name);
      this.logDir = logDir;
      this.filename = filename;
      initLogFile();
   }


   public SimpleFileLogger(Level l, String logDir, String filename) {
      super(l);
      this.logDir = logDir;
      this.filename = filename;
      initLogFile();
   }


   public SimpleFileLogger(String name, Level l, String logDir, String filename) {
      super(name, l);
      this.logDir = logDir;
      this.filename = filename;
      initLogFile();
   }


   @Override
   protected void doLog(LogEntry le) {
      try {
         if (hasFilenameChanged()) {
            out.flush();
            out.close();
            out = null;
            initLogFile();
         }
         out.write(le.toString().getBytes());
      }
      catch (IOException e) {
         super.logError(null, e, "Could not write to LogFile " + currentLogfile);
      }
   }


   protected boolean hasFilenameChanged() {
      return false;
   }


   protected void initLogFile() {
      try {
         currentLogfile = getCurrentLogfileName();
         out = new BufferedOutputStream(new FileOutputStream(currentLogfile, true), BUFFER_SIZE);
      }
      catch (FileNotFoundException e) {
         super.logError(null, e, "Could not create LogFile " + currentLogfile);
      }
   }


   public String getCurrentLogfileName() {
      return logDir + File.separator + filename;
   }
}
