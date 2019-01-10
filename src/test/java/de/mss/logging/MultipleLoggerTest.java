package de.mss.logging;

import org.junit.Test;

public class MultipleLoggerTest {

   @Test
   public void test() throws InterruptedException {
      BufferedLogger bl1 = new BufferedLogger("default", BaseLogger.getLevelDebug(), 100);
      BufferedLogger bl2 = new BufferedLogger("default", BaseLogger.getLevelDebug(), 100);

      MultipleLogger ml = new MultipleLogger("default");
      ml.addLogger(bl1);
      ml.addLogger(bl2);

      ml.logDebug("loggingId", "logging entry");

      Thread.sleep(1000);

      System.out.println(bl1.getLogBuffer());
      System.out.println(bl2.getLogBuffer());
   }

}
