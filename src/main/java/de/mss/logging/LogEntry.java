package de.mss.logging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LogEntry {

   private String            uniqueLoggingId = null;
   private Throwable         throwable       = null;
   private ArrayList<String> msgList         = null;
   private LogableEntry      logEntry        = null;

   protected String          fieldSeparator  = " ";
   protected String          dateFormat      = "yyyy-MM-dd HH:mm:ss.SSS";


   private static final String LINE_BREAK = System.getProperty("line.separator");


   public LogEntry(String u, Throwable t, LogableEntry le) {
      setLogEntry(le);
      setThrowable(t);
      setUniqueLoggingId(u);
   }


   public LogEntry(String u, Throwable t, List<String> l) {
      setMessageList(l);
      setThrowable(t);
      setUniqueLoggingId(u);
   }


   public LogEntry(String u, Throwable t, String msg) {
      setMessageList(msg);
      setThrowable(t);
      setUniqueLoggingId(u);
   }


   public LogEntry(String u, Throwable t, String... msgs) {
      setMessageList(msgs);
      setThrowable(t);
      setUniqueLoggingId(u);
   }


   public void setLogEntry(LogableEntry le) {
      this.logEntry = le;
   }


   public void setMessageList(List<String> l) {
      this.msgList = new ArrayList<>();
      for (String s : l)
         this.msgList.add(s);
   }


   public void setMessageList(String s) {
      this.msgList = new ArrayList<>();
      this.msgList.add(s);
   }


   public void setMessageList(String... s) {
      this.msgList = new ArrayList<>();
      for (String msg : s)
         this.msgList.add(msg);
   }


   public void setThrowable(Throwable t) {
      this.throwable = t;
   }


   public void setUniqueLoggingId(String u) {
      this.uniqueLoggingId = u;
   }


   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();

      SimpleDateFormat sdf = new SimpleDateFormat(this.dateFormat);

      sb.append(sdf.format(new java.util.Date()));

      if (this.uniqueLoggingId != null)
         sb.append(this.fieldSeparator + this.uniqueLoggingId);

      if (this.msgList != null) {
         for (String line : this.msgList) {
            sb.append(this.fieldSeparator + line.replaceAll("\r", "\\r").replaceAll("\n", "\\n"));
         }
      }

      if (this.logEntry != null)
         sb.append(this.logEntry.toString());

      if (this.throwable != null) {

         for (StackTraceElement line : this.throwable.getStackTrace())
            sb.append(LINE_BREAK + this.uniqueLoggingId + this.fieldSeparator + line.toString());
      }

      sb.append(LINE_BREAK);

      return sb.toString();
   }
}
