package de.mss.logging;

import org.junit.Test;

public class BufferedLoggerTest {

   @Test
   public void testAppend() {
      BufferedLogger bl = new BufferedLogger("default", 100);

      bl.logError(null, "logentry1");
      bl.logError(null, "logentry2");

      System.out.println(bl.getLogBuffer());
   }


   @Test
   public void testAppendWithCut() {
      BufferedLogger bl = new BufferedLogger("default", 100);

      bl.logError(null, "logentry1");
      bl.logError(null, "logentry2");
      bl.logError(null, "logentry3");
      bl.logError(null, "logentry4");

      System.out.println(bl.getLogBuffer());
   }


   @Test
   public void testAppendLongMessage() {
      BufferedLogger bl = new BufferedLogger("default", 100);
      String nl = System.getProperty("line.separator");

      bl
            .logError(
                  null,
                  "very long message very"
                        + nl
                        + "very long message very"
                        + nl
                        + "very long message very"
                        + nl
                        + "very long message very"
                        + nl
                        + "very long message very");

      System.out.println(bl.getLogBuffer());
   }

}
