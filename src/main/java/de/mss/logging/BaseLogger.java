package de.mss.logging;

import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseLogger extends Logger {

   PrintStream out = new PrintStream(
         new BufferedOutputStream(
               new FileOutputStream(FileDescriptor.out),
               1024),
         true);


   public BaseLogger() {
      super(null, null);
   }


   public BaseLogger(String name) {
      super(name, null);
   }


   public BaseLogger(Level l) {
      super(null, null);
      setLevel(l);
   }


   public BaseLogger(String name, Level l) {
      super(name, null);
      setLevel(l);
   }


   protected void doLog(LogEntry le) {
      out.println(le.toString());
   }


   public static Level getLevelAll() {
      return Level.ALL;
   }


   public static Level getLevelDebug() {
      return Level.FINE;
   }


   public static Level getLevelError() {
      return Level.SEVERE;
   }


   public static Level getLevelInfo() {
      return Level.INFO;
   }


   public static Level getLevelNull() {
      return Level.OFF;
   }


   public static Level getLevelVerbose() {
      return Level.FINER;
   }


   public static Level getLevelVerbose2() {
      return Level.FINEST;
   }


   public static Level getLevelWarning() {
      return Level.WARNING;
   }


   public void log(String uniqueLoggingId, Level level, String msg) {
      if (!isLoggable(level))
         return;

      doLog(new LogEntry(uniqueLoggingId, null, msg));
   }


   public void log(String uniqueLoggingId, Level level, String... msgs) {
      if (!isLoggable(level))
         return;

      doLog(new LogEntry(uniqueLoggingId, null, msgs));
   }


   public void log(String uniqueLoggingId, Level level, Throwable e) {
      if (!isLoggable(level))
         return;

      doLog(new LogEntry(uniqueLoggingId, e, (String)null));
   }


   public void log(String uniqueLoggingId, Level level, Throwable e, String msg) {
      if (!isLoggable(level))
         return;

      doLog(new LogEntry(uniqueLoggingId, e, msg));
   }


   public void log(String uniqueLoggingId, Level level, Throwable e, String... msgs) {
      if (!isLoggable(level))
         return;

      doLog(new LogEntry(uniqueLoggingId, e, msgs));
   }


   public void logAll(String uniqueLoggingId, String msg) {
      log(uniqueLoggingId, Level.ALL, msg);
   }


   public void logAll(String uniqueLoggingId, String... msgs) {
      log(uniqueLoggingId, Level.ALL, msgs);
   }


   public void logAll(String uniqueLoggingId, Throwable e) {
      log(uniqueLoggingId, Level.ALL, e);
   }


   public void logAll(String uniqueLoggingId, Throwable e, String msg) {
      log(uniqueLoggingId, Level.ALL, e, msg);
   }


   public void logAll(String uniqueLoggingId, Throwable e, String... msgs) {
      log(uniqueLoggingId, Level.ALL, e, msgs);
   }


   public void logDebug(String uniqueLoggingId, String msg) {
      log(uniqueLoggingId, Level.FINE, msg);
   }


   public void logDebug(String uniqueLoggingId, String... msgs) {
      log(uniqueLoggingId, Level.FINE, msgs);
   }


   public void logDebug(String uniqueLoggingId, Throwable e) {
      log(uniqueLoggingId, Level.FINE, e);
   }


   public void logDebug(String uniqueLoggingId, Throwable e, String msg) {
      log(uniqueLoggingId, Level.FINE, e, msg);
   }


   public void logDebug(String uniqueLoggingId, Throwable e, String... msgs) {
      log(uniqueLoggingId, Level.FINE, e, msgs);
   }


   public void logError(String uniqueLoggingId, String msg) {
      log(uniqueLoggingId, Level.SEVERE, msg);
   }


   public void logError(String uniqueLoggingId, String... msgs) {
      log(uniqueLoggingId, Level.SEVERE, msgs);
   }


   public void logError(String uniqueLoggingId, Throwable e) {
      log(uniqueLoggingId, Level.SEVERE, e);
   }


   public void logError(String uniqueLoggingId, Throwable e, String msg) {
      log(uniqueLoggingId, Level.SEVERE, e, msg);
   }


   public void logError(String uniqueLoggingId, Throwable e, String... msgs) {
      log(uniqueLoggingId, Level.SEVERE, e, msgs);
   }


   public void logInfo(String uniqueLoggingId, String msg) {
      log(uniqueLoggingId, Level.INFO, msg);
   }


   public void logInfo(String uniqueLoggingId, String... msgs) {
      log(uniqueLoggingId, Level.INFO, msgs);
   }


   public void logInfo(String uniqueLoggingId, Throwable e) {
      log(uniqueLoggingId, Level.INFO, e);
   }


   public void logInfo(String uniqueLoggingId, Throwable e, String msg) {
      log(uniqueLoggingId, Level.INFO, e, msg);
   }


   public void logNull(String uniqueLoggingId, String msg) {
      log(uniqueLoggingId, Level.OFF, msg);
   }


   public void logNull(String uniqueLoggingId, String... msgs) {
      log(uniqueLoggingId, Level.OFF, msgs);
   }


   public void logNull(String uniqueLoggingId, Throwable e) {
      log(uniqueLoggingId, Level.OFF, e);
   }


   public void logNull(String uniqueLoggingId, Throwable e, String msg) {
      log(uniqueLoggingId, Level.OFF, e, msg);
   }


   public void logNull(String uniqueLoggingId, Throwable e, String... msgs) {
      log(uniqueLoggingId, Level.OFF, e, msgs);
   }


   public void logInfo(String uniqueLoggingId, Throwable e, String... msgs) {
      log(uniqueLoggingId, Level.INFO, e, msgs);
   }


   public void logVerbose(String uniqueLoggingId, String msg) {
      log(uniqueLoggingId, Level.FINER, msg);
   }


   public void logVerbose(String uniqueLoggingId, String... msgs) {
      log(uniqueLoggingId, Level.FINER, msgs);
   }


   public void logVerbose(String uniqueLoggingId, Throwable e) {
      log(uniqueLoggingId, Level.FINER, e);
   }


   public void logVerbose(String uniqueLoggingId, Throwable e, String msg) {
      log(uniqueLoggingId, Level.FINER, e, msg);
   }


   public void logVerbose(String uniqueLoggingId, Throwable e, String... msgs) {
      log(uniqueLoggingId, Level.FINER, e, msgs);
   }


   public void logVerbose2(String uniqueLoggingId, String msg) {
      log(uniqueLoggingId, Level.FINEST, msg);
   }


   public void logVerbose2(String uniqueLoggingId, String... msgs) {
      log(uniqueLoggingId, Level.FINEST, msgs);
   }


   public void logVerbose2(String uniqueLoggingId, Throwable e) {
      log(uniqueLoggingId, Level.FINEST, e);
   }


   public void logVerbose2(String uniqueLoggingId, Throwable e, String msg) {
      log(uniqueLoggingId, Level.FINEST, e, msg);
   }


   public void logVerbose2(String uniqueLoggingId, Throwable e, String... msgs) {
      log(uniqueLoggingId, Level.FINEST, e, msgs);
   }


   public void logWarning(String uniqueLoggingId, String msg) {
      log(uniqueLoggingId, Level.WARNING, msg);
   }


   public void logWarning(String uniqueLoggingId, String... msgs) {
      log(uniqueLoggingId, Level.WARNING, msgs);
   }


   public void logWarning(String uniqueLoggingId, Throwable e) {
      log(uniqueLoggingId, Level.WARNING, e);
   }


   public void logWarning(String uniqueLoggingId, Throwable e, String msg) {
      log(uniqueLoggingId, Level.WARNING, e, msg);
   }


   public void logWarning(String uniqueLoggingId, Throwable e, String... msgs) {
      log(uniqueLoggingId, Level.WARNING, e, msgs);
   }
}
