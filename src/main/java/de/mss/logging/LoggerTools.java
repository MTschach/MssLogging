package de.mss.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggerTools {

   private LoggerTools() {}


   public static Logger getDefaultLogger() {
      return getLogger("default");
   }


   public static Logger getLogger(String name) {
      return LogManager.getLogger(name);
   }


   public static void trace(String loggingId, String msg) {
      getDefaultLogger().trace(formatLoggingId(loggingId) + " " + msg);
   }


   public static void trace(String loggingId, String... msgs) {
      String[] m = new String[msgs.length + 1];
      m[0] = formatLoggingId(loggingId);
      int i = 1;
      for (String s : msgs)
         m[i++ ] = s;

      getDefaultLogger().trace(m);
   }


   public static void trace(String loggingId, Throwable e) {
      getDefaultLogger().trace(formatLoggingId(loggingId) + " " + e);
   }


   public static void trace1(String loggingId, String msg) {
      getDefaultLogger().trace(formatLoggingId(loggingId) + " " + msg);
   }


   public static void trace2(String loggingId, String msg) {
      getDefaultLogger().trace(formatLoggingId(loggingId) + " " + msg);
   }


   private static String formatLoggingId(String loggingId) {
      return loggingId == null ? "<>" : "<" + loggingId + ">";
   }
}
