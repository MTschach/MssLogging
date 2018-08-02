package de.mss.logging;

import java.util.logging.Logger;

public class LoggingFactory {
   
   private static BaseLogger logger = null;
   
   
   private LoggingFactory() {
   }
   
   
   public static BaseLogger getLogger() {
      if (logger != null)
         return logger;
      
      return new BaseLogger();
   }
   
   
   public static BaseLogger createInstance(BaseLogger l) {
      logger = l;
      
      return logger;
   }
   
   
   public static synchronized boolean isInitialized() {
      return (logger != null);
   }
   
   
   public static synchronized void shutdownInstance() {
      if (logger != null) {
         logger = null;
      }
   }
}
