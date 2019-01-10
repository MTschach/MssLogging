package de.mss.logging;

import java.util.logging.Level;

public class BufferedLogger extends BaseLogger {

   private StringBuilder logBuffer  = null;
   private int           bufferedLines = 100;


   public BufferedLogger() {
      super();
      initBuffer();
   }


   public BufferedLogger(int b) {
      super();
      this.bufferedLines = b;
      initBuffer();
   }


   public BufferedLogger(Level l) {
      super(l);
      initBuffer();
   }


   public BufferedLogger(Level l, int b) {
      super(l);
      this.bufferedLines = b;
      initBuffer();
   }


   public BufferedLogger(String name) {
      super(name);
      initBuffer();
   }


   public BufferedLogger(String name, int b) {
      super(name);
      this.bufferedLines = b;
      initBuffer();
   }


   public BufferedLogger(String name, Level l) {
      super(name, l);
      initBuffer();
   }


   public BufferedLogger(String name, Level l, int b) {
      super(name, l);
      this.bufferedLines = b;
      initBuffer();
   }


   private void initBuffer() {
      this.logBuffer = new StringBuilder();
   }


   public int getBufferSize() {
      return this.bufferedLines;
   }


   public void setBufferSize(int b) {
      this.bufferedLines = b;
   }


   public String getLogBuffer() {
      return this.logBuffer.toString();
   }


   public void clearBuffer() {
      initBuffer();
   }


   @Override
   protected void doLog(LogEntry le) {
      //      String s = le.toString();
      //      int loglen = s.length();
      //
      //      if (this.logBuffer.length() + loglen <= this.bufferedLines) {
      //         this.logBuffer.append(s);
      //         return;
      //      }
      //
      //      String nl = System.getProperty("line.separator");
      //
      //      if (loglen > this.bufferedLines) {
      //         int i = 0;
      //         do {
      //            i = s.indexOf(nl, i);
      //         }
      //         while (loglen - i > this.bufferedLines);
      //         this.logBuffer = new StringBuilder(s.substring(i + nl.length()));
      //         return;
      //      }
      //
      //      int i = 0;
      //      do {
      //         i = this.logBuffer.indexOf(nl, i);
      //      }
      //      while (loglen + i > this.bufferedLines);
      //
      //      this.logBuffer = new StringBuilder(this.logBuffer.substring(i + nl.length()));
      //      this.logBuffer.append(s);
      String nl = System.getProperty("line.separator");
      String[] bufferdLines = this.logBuffer.toString().split(nl);
      String[] linesLogentry = le.toString().split(nl);

      if (linesLogentry.length >= this.bufferedLines) {
         this.logBuffer = new StringBuilder();
         for (int i = linesLogentry.length - this.bufferedLines; i < linesLogentry.length; i++ )
            this.logBuffer.append(linesLogentry[i] + nl);

         return;
      }

      if ((bufferdLines.length + linesLogentry.length) > this.bufferedLines) {
         this.logBuffer = new StringBuilder();
         for (int i = bufferdLines.length + linesLogentry.length - this.bufferedLines; i < bufferdLines.length; i++ )
            this.logBuffer.append(bufferdLines[i] + nl);
         for (String s : linesLogentry)
            this.logBuffer.append(s + nl);

         return;
      }

      for (String s : linesLogentry)
         this.logBuffer.append(s + nl);
   }
}
