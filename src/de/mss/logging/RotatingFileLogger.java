package de.mss.logging;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class RotatingFileLogger extends SimpleFileLogger {
   protected Long timestampNextLogfile       = null;
   protected RotateInterval interval         = RotateInterval.HOURLY;

   public RotatingFileLogger() {
      super();
   }
   
   
   public RotatingFileLogger(RotateInterval i) {
      super();
      interval = i;
   }
   
   
   public RotatingFileLogger(String logDir, String filename) {
      super(logDir, filename);
   }
   
   
   public RotatingFileLogger(String logDir, String filename, RotateInterval i) {
      super(logDir, filename);
      interval = i;
   }
   
   
   protected void calculateNextFilechange() {
      GregorianCalendar gc = new GregorianCalendar();
      
      if (interval == RotateInterval.HOURLY) {
         gc.set(GregorianCalendar.HOUR, gc.get(GregorianCalendar.HOUR) + 1);
         gc.set(GregorianCalendar.MINUTE, 0);
         gc.set(GregorianCalendar.SECOND, 0);
         gc.set(GregorianCalendar.MILLISECOND, 0);
      }
      else {
         gc.set(GregorianCalendar.HOUR, 0);
         gc.set(GregorianCalendar.MINUTE, 0);
         gc.set(GregorianCalendar.SECOND, 0);
         gc.set(GregorianCalendar.MILLISECOND, 0);
         gc.set(GregorianCalendar.DAY_OF_MONTH, gc.get(GregorianCalendar.DAY_OF_MONTH) + 1);
      }
      
      timestampNextLogfile = gc.getTime().getTime();
   }
   
   
   @Override
   protected String getCurrentLogfileName () {
      return logDir + File.separator + modifyFilename(filename);
   }
   
   
   @Override
   protected boolean hasFilenameChanged() {
      return new java.util.Date().getTime() > timestampNextLogfile.longValue();
   }
   
   
   @Override
   protected void initLogFile() {
      super.initLogFile();
      calculateNextFilechange();
   }
   protected String modifyFilename (String filename) {
      String base = "";
      String ext = "";
      if (filename.lastIndexOf('.') < 0)
         base = filename;
      else {
         base = filename.substring(0, filename.lastIndexOf('.'));
         ext  = filename.substring(filename.lastIndexOf('.'));
      }
      
      SimpleDateFormat sdf = null;
      
      if (interval == RotateInterval.HOURLY)
            sdf = new SimpleDateFormat("yyyy-MM-dd_hh");
      else
            sdf = new SimpleDateFormat("yyyy-MM-dd");
      
      return base + "_" + sdf.format(new java.util.Date()) + ext;
   }
}
