package de.mss.logging;

import java.util.HashMap;
import java.util.Set;

public class LoggingFactory {

   private static HashMap<String, BaseLogger> loggers = new HashMap<>();


   private LoggingFactory() {}


   public static BaseLogger getLogger(String name) {
      if (!loggers.containsKey(name))
         return null;

      return loggers.get(name);
   }


   public static BaseLogger createInstance(String name, BaseLogger l) {
      if (!loggers.containsKey(name))
         loggers.put(name, l);

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
