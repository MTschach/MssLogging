package de.mss.logging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class LoggingFactoryTest {

   static BaseLogger system           = new BaseLogger();
   String            nameSystemLogger = "system";


   @Before
   public void setUp() {
      Set<String> loggers = LoggerFactory.getLoggerNames();

      for (String name : loggers)
         if (!nameSystemLogger.equals(name))
            LoggerFactory.shutdownInstance(name);

      LoggerFactory.createInstance(nameSystemLogger, system);
   }


   @Test
   public void testGetUnknownLogger() {
      BaseLogger logger = LoggerFactory.getLogger("unknown");

      assertNull("Unknown Logger", logger);
   }


   @Test
   public void testGetSystemLogger() {
      LoggerFactory.createInstance("info", new BaseLogger());

      BaseLogger logger = LoggerFactory.getLogger(nameSystemLogger);

      assertEquals("Systemlogger", system, logger);
   }


   @Test
   public void testCreateInstance() {
      BaseLogger logger = new BaseLogger();
      BaseLogger logger1 = LoggerFactory.createInstance("testlogger", logger);

      assertEquals("Logger", logger, logger1);
   }


   @Test
   public void testIsInitialized() {
      assertNotNull("Logger initialized", LoggerFactory.isInitialized(nameSystemLogger));
   }


   @Test
   public void testIsNotInitialized() {
      assertFalse("Logger not initialized", LoggerFactory.isInitialized("testlogger"));
   }


   @Test
   public void testShutdown() {
      String loggername = "testlogger";
      LoggerFactory.createInstance(loggername, new BaseLogger());

      assertTrue("Logger initialized", LoggerFactory.isInitialized(loggername));

      LoggerFactory.shutdownInstance(loggername);

      assertFalse("Logger shut down", LoggerFactory.isInitialized(loggername));
   }
}
