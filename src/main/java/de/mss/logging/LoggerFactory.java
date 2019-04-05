package de.mss.logging;

import java.util.HashMap;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggerFactory {

   private static HashMap<String, Logger> loggers = new HashMap<>();


   private LoggerFactory() {}


   public static Logger getLogger(String name) {
      if (!loggers.containsKey(name))
         return null;

      return loggers.get(name);
   }


   public static Logger createInstance(String name, String log4jConfigFile) {
      if (!loggers.containsKey(name))
      {
         Logger l = LogManager.getLogger(name);
         loggers.put(name, l);
      }

      return loggers.get(name);
   }


   public static synchronized boolean isInitialized(String name) {
      return loggers.containsKey(name);
   }


   public static synchronized void shutdownInstance(String name) {
      if (loggers.containsKey(name))
         loggers.remove(name);
   }


   public static Set<String> getLoggerNames() {
      return loggers.keySet();
   }
}
