package de.mss.logging;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;

public class RotatingFileLogger extends SimpleFileLogger {

   protected Long           timestampNextLogfile = null;
   protected RotateInterval interval             = RotateInterval.HOURLY;


   public RotatingFileLogger() {
      super();
      initLogFile();
   }


   public RotatingFileLogger(String name) {
      super(name);
      initLogFile();
   }


   public RotatingFileLogger(Level l) {
      super(l);
      initLogFile();
   }


   public RotatingFileLogger(RotateInterval i) {
      super();
      interval = i;
      initLogFile();
   }


   public RotatingFileLogger(String name, RotateInterval i) {
      super(name);
      interval = i;
      initLogFile();
   }


   public RotatingFileLogger(String name, Level l, RotateInterval i) {
      super(name, l);
      interval = i;
      initLogFile();
   }


   public RotatingFileLogger(String logDir, String filename) {
      super(logDir, filename);
      initLogFile();
   }


   public RotatingFileLogger(String name, String logDir, String filename) {
      super(name, logDir, filename);
      initLogFile();
   }


   public RotatingFileLogger(Level l, String logDir, String filename) {
      super(l, logDir, filename);
      initLogFile();
   }


   public RotatingFileLogger(String name, Level l, String logDir, String filename) {
      super(name, l, logDir, filename);
      initLogFile();
   }


   public RotatingFileLogger(String logDir, String filename, RotateInterval i) {
      super(logDir, filename);
      interval = i;
      initLogFile();
   }


   public RotatingFileLogger(String name, String logDir, String filename, RotateInterval i) {
      super(name, logDir, filename);
      interval = i;
      initLogFile();
   }


   public RotatingFileLogger(Level l, String logDir, String filename, RotateInterval i) {
      super(l, logDir, filename);
      interval = i;
      initLogFile();
   }


   public RotatingFileLogger(String name, Level l, String logDir, String filename, RotateInterval i) {
      super(name, l, logDir, filename);
      interval = i;
      initLogFile();
   }


   protected void calculateNextFilechange() {
      GregorianCalendar gc = new GregorianCalendar();

      switch (interval) {
         case HOURLY:
            gc.set(GregorianCalendar.HOUR, gc.get(GregorianCalendar.HOUR) + 1);
            gc.set(GregorianCalendar.MINUTE, 0);
            gc.set(GregorianCalendar.SECOND, 0);
            gc.set(GregorianCalendar.MILLISECOND, 0);
            break;

         case WEEKLY:
            gc.set(GregorianCalendar.HOUR, 0);
            gc.set(GregorianCalendar.MINUTE, 0);
            gc.set(GregorianCalendar.SECOND, 0);
            gc.set(GregorianCalendar.MILLISECOND, 0);
            gc.set(GregorianCalendar.DAY_OF_MONTH, gc.get(GregorianCalendar.DAY_OF_MONTH) + 7 - gc.get(GregorianCalendar.DAY_OF_WEEK));
            break;

         case DAILY:
            gc.set(GregorianCalendar.HOUR, 0);
            gc.set(GregorianCalendar.MINUTE, 0);
            gc.set(GregorianCalendar.SECOND, 0);
            gc.set(GregorianCalendar.MILLISECOND, 0);
            gc.set(GregorianCalendar.DAY_OF_MONTH, gc.get(GregorianCalendar.DAY_OF_MONTH) + 1);
            break;
      }

      timestampNextLogfile = gc.getTime().getTime();
   }


   @Override
   public String getCurrentLogfileName() {
      return logDir + File.separator + modifyFilename(filename);
   }


   @Override
   protected boolean hasFilenameChanged() {
      return new java.util.Date().getTime() > timestampNextLogfile.longValue();
   }


   @Override
   protected void initLogFile() {
      if (interval == null)
         return;
      super.initLogFile();
      calculateNextFilechange();
   }


   protected String modifyFilename(String filename) {
      String base = "";
      String ext = "";
      if (filename.lastIndexOf('.') < 0)
         base = filename;
      else {
         base = filename.substring(0, filename.lastIndexOf('.'));
         ext = filename.substring(filename.lastIndexOf('.'));
      }

      SimpleDateFormat sdf = null;

      switch (interval) {
         case HOURLY:
            sdf = new SimpleDateFormat("yyyy-MM-dd_hh");
            break;

         case DAILY:
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            break;

         case WEEKLY:
            sdf = new SimpleDateFormat("yyyy_'KW'ww");
            break;
      }

      return base + "_" + sdf.format(new java.util.Date()) + ext;
   }
}
