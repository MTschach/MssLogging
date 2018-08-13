package de.mss.logging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class LoggingFactoryTest {

   BaseLogger system           = new BaseLogger();
   String     nameSystemLogger = "system";


   @Before
   public void setUp() {
      Set<String> loggers = LoggingFactory.getLoggerNames();

      for (String name : loggers)
         if (!nameSystemLogger.equals(name))
            LoggingFactory.shutdownInstance(name);

      LoggingFactory.createInstance(nameSystemLogger, system);
   }


   @Test
   public void testGetUnknownLogger() {
      BaseLogger logger = LoggingFactory.getLogger("unknown");

      assertNull("Unknown Logger", logger);
   }


   @Test
   public void testGetSystemLogger() {
      LoggingFactory.createInstance("info", new BaseLogger());

      BaseLogger logger = LoggingFactory.getLogger(nameSystemLogger);

      assertEquals("Systemlogger", system, logger);
   }


   @Test
   public void testCreateInstance() {
      BaseLogger logger = new BaseLogger();
      BaseLogger logger1 = LoggingFactory.createInstance("testlogger", logger);

      assertEquals("Logger", logger, logger1);
   }


   @Test
   public void testIsInitialized() {
      assertNotNull("Logger initialized", LoggingFactory.isInitialized(nameSystemLogger));
   }


   @Test
   public void testIsNotInitialized() {
      assertNull("Logger not initialized", LoggingFactory.isInitialized("testlogger"));
   }


   @Test
   public void testShutdonw() {
      String loggername = "testlogger";
      LoggingFactory.createInstance(loggername, new BaseLogger());

      assertNotNull("Logger initialized", LoggingFactory.isInitialized(loggername));

      LoggingFactory.shutdownInstance(loggername);

      assertNull("Logger shut down", LoggingFactory.isInitialized(loggername));
   }
}
