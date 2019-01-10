package de.mss.logging;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class MultipleLogger extends BaseLogger {

   private List<BaseLogger> loggerList = null;

   public MultipleLogger() {
      super();
      initList();
   }


   public MultipleLogger(Level l) {
      super(l);
      initList();
   }


   public MultipleLogger(String name) {
      super(name);
      initList();
   }


   public MultipleLogger(String name, Level l) {
      super(name, l);
      initList();
   }


   private void initList() {
      this.loggerList = new ArrayList<>();
   }


   public void addLogger(BaseLogger b) {
      this.loggerList.add(b);
   }


   @Override
   public void log(String uniqueLoggingId, Level level, String msg) {
      for (BaseLogger b : this.loggerList)
         new Thread(new LoggerThread(b, uniqueLoggingId, level, msg)).start();
   }


   @Override
   public void log(String uniqueLoggingId, Level level, String... msgs) {
      for (BaseLogger b : this.loggerList)
         new Thread(new LoggerThread(b, uniqueLoggingId, level, msgs)).start();
   }


   @Override
   public void log(String uniqueLoggingId, Level level, Throwable e) {
      for (BaseLogger b : this.loggerList)
         new Thread(new LoggerThread(b, uniqueLoggingId, level, e)).start();
   }


   @Override
   public void log(String uniqueLoggingId, Level level, Throwable e, String msg) {
      for (BaseLogger b : this.loggerList)
         new Thread(new LoggerThread(b, uniqueLoggingId, level, e, msg)).start();
   }


   @Override
   public void log(String uniqueLoggingId, Level level, Throwable e, String... msgs) {
      for (BaseLogger b : this.loggerList)
         new Thread(new LoggerThread(b, uniqueLoggingId, level, e, msgs)).start();
   }


   protected class LoggerThread implements Runnable {

      private BaseLogger logger = null;
      private Level        loggingLevel = null;
      private String       loggingId = null;
      private List<String> msgs      = null;
      private String       msg       = null;
      private Throwable    exception = null;


      public LoggerThread(BaseLogger b, String l, Level level, String m) {
         this.logger = b;
         this.loggingLevel = level;
         this.loggingId = l;
         this.msg = m;
      }


      public LoggerThread(BaseLogger b, String l, Level level, String... ms) {
         this.logger = b;
         this.loggingLevel = level;
         this.loggingId = l;
         this.msgs = new ArrayList<>();
         for (String s : ms)
            this.msgs.add(s);
      }


      public LoggerThread(BaseLogger b, String l, Level level, Throwable t) {
         this.logger = b;
         this.loggingLevel = level;
         this.loggingId = l;
         this.exception = t;
      }


      public LoggerThread(BaseLogger b, String l, Level level, Throwable t, String m) {
         this.logger = b;
         this.loggingLevel = level;
         this.loggingId = l;
         this.exception = t;
         this.msg = m;
      }


      public LoggerThread(BaseLogger b, String l, Level level, Throwable t, String... ms) {
         this.logger = b;
         this.loggingLevel = level;
         this.loggingId = l;
         this.msgs = new ArrayList<>();
         this.exception = t;
         for (String s : ms)
            this.msgs.add(s);
      }


      @Override
      public void run() {
         if (this.exception == null) {
            if (this.msgs == null) {
               this.logger.log(this.loggingId, this.loggingLevel, this.msg);
            }
            else {
               this.logger.log(this.loggingId, this.loggingLevel, this.msgs.toArray(new String[this.msgs.size()]));
            }
         }
         else {
            if (this.msgs == null) {
               this.logger.log(this.loggingId, this.loggingLevel, this.exception, this.msg);
            }
            else {
               this.logger.log(this.loggingId, this.loggingLevel, this.exception, this.msgs.toArray(new String[this.msgs.size()]));
            }
         }
      }

   }
}
