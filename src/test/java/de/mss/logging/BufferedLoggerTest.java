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
      BufferedLogger bl = new BufferedLogger("default", 2);

      bl.logError(null, "logentry1");
      bl.logError(null, "logentry2");
      bl.logError(null, "logentry3");
      bl.logError(null, "logentry4");

      System.out.println(bl.getLogBuffer());
   }


   @Test
   public void testAppendLongMessage() {
      BufferedLogger bl = new BufferedLogger("default", 2);
      String nl = System.getProperty("line.separator");

      bl
            .logError(
                  null,
                  "very long message very 1"
                        + nl
                        + "very long message very 2"
                        + nl
                        + "very long message very 3"
                        + nl
                        + "very long message very 4"
                        + nl
                        + "very long message very 5");

      System.out.println(bl.getLogBuffer());
   }

}
