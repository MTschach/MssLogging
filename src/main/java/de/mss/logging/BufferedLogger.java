package de.mss.logging;

import java.util.logging.Level;

public class BufferedLogger extends BaseLogger {

   private StringBuilder logBuffer  = null;
   private int           bufferSize = 65536;


   public BufferedLogger() {
      super();
      initBuffer();
   }


   public BufferedLogger(int b) {
      super();
      this.bufferSize = b;
      initBuffer();
   }


   public BufferedLogger(Level l) {
      super(l);
      initBuffer();
   }


   public BufferedLogger(Level l, int b) {
      super(l);
      this.bufferSize = b;
      initBuffer();
   }


   public BufferedLogger(String name) {
      super(name);
      initBuffer();
   }


   public BufferedLogger(String name, int b) {
      super(name);
      this.bufferSize = b;
      initBuffer();
   }


   public BufferedLogger(String name, Level l) {
      super(name, l);
      initBuffer();
   }


   public BufferedLogger(String name, Level l, int b) {
      super(name, l);
      this.bufferSize = b;
      initBuffer();
   }


   private void initBuffer() {
      this.logBuffer = new StringBuilder();
   }


   public int getBufferSize() {
      return this.bufferSize;
   }


   public void setBufferSize(int b) {
      this.bufferSize = b;
   }


   public String getLogBuffer() {
      return this.logBuffer.toString();
   }


   public void clearBuffer() {
      initBuffer();
   }


   @Override
   protected void doLog(LogEntry le) {
      String s = le.toString();
      int loglen = s.length();

      if (this.logBuffer.length() + loglen <= this.bufferSize) {
         this.logBuffer.append(s);
         return;
      }

      String nl = System.getProperty("line.separator");

      if (loglen > this.bufferSize) {
         int i = 0;
         do {
            i = s.indexOf(nl, i);
         }
         while (loglen - i > this.bufferSize);
         this.logBuffer = new StringBuilder(s.substring(i + nl.length()));
         return;
      }

      int i = 0;
      do {
         i = this.logBuffer.indexOf(nl, i);
      }
      while (loglen + i > this.bufferSize);

      this.logBuffer = new StringBuilder(this.logBuffer.substring(i + nl.length()));
      this.logBuffer.append(s);
   }
}
