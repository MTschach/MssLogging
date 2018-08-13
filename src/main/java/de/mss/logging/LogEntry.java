package de.mss.logging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LogEntry {

   private String uniqueLoggingId      = null;
   private Throwable throwable         = null;
   private ArrayList<String> msgList   = null;
   
   protected String fieldSeparator     = " ";
   protected String dateFormat         = "yyyy-MM-dd HH:mm:ss.SSS";
   
   
   private static final String LINE_BREAK = System.getProperty("line.separator");
   
   
   public LogEntry (String u, Throwable t, List<String> l) {
      setMessageList(l);
      setThrowable(t);
      setUniqueLoggingId(u);
   }
   
   
   public LogEntry (String u, Throwable t, String msg) {
      setMessageList(msg);
      setThrowable(t);
      setUniqueLoggingId(u);
   }
   
   
   public LogEntry (String u, Throwable t, String... msgs) {
      setMessageList(msgs);
      setThrowable(t);
      setUniqueLoggingId(u);
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
   
   
   public void setThrowable (Throwable t) {
      this.throwable = t;
   }
   
   
   public void setUniqueLoggingId(String u) {
      this.uniqueLoggingId = u;
   }
   
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
      
      sb.append(fieldSeparator + sdf.format(new java.util.Date()));
      
      if (this.uniqueLoggingId != null)
         sb.append(fieldSeparator + this.uniqueLoggingId);
      
      if (this.throwable != null) {
         
         for (StackTraceElement line : this.throwable.getStackTrace())
            sb.append(fieldSeparator + line.toString() + LINE_BREAK);
      }
      
      if (this.msgList != null) {
         for (String line : msgList) {
            sb.append(fieldSeparator + line);
         }
      }
      
      return sb.toString();
   }
}
