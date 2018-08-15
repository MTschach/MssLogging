package de.mss.logging;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RotatingFileLoggerTest {

   @Test
   public void testFilenameHourly() {
      RotatingFileLogger f = new RotatingFileLogger(".", "logfile", RotateInterval.HOURLY);
      String filename = f.getCurrentLogfileName();
      assertNotNull("Filename not null", filename);
      filename = filename.substring(filename.indexOf(File.separator) + 1);

      Pattern p = Pattern.compile("logfile_[0-9]{4}-[0-9]{2}-[0-9]{2}_[0-9]{2}");
      Matcher m = p.matcher(filename);
      assertTrue("Filename", m.matches());
   }


   @Test
   public void testFilenameDaily() {
      RotatingFileLogger f = new RotatingFileLogger(".", "logfile", RotateInterval.DAILY);
      String filename = f.getCurrentLogfileName();
      assertNotNull("Filename not null", filename);
      filename = filename.substring(filename.indexOf(File.separator) + 1);

      Pattern p = Pattern.compile("logfile_[0-9]{4}-[0-9]{2}-[0-9]{2}");
      Matcher m = p.matcher(filename);
      assertTrue("Filename", m.matches());
   }


   @Test
   public void testFilenameWeekly() {
      RotatingFileLogger f = new RotatingFileLogger(".", "logfile", RotateInterval.WEEKLY);
      String filename = f.getCurrentLogfileName();
      assertNotNull("Filename not null", filename);
      filename = filename.substring(filename.indexOf(File.separator) + 1);

      Pattern p = Pattern.compile("logfile_[0-9]{4}_KW[0-9]{2}");
      Matcher m = p.matcher(filename);
      assertTrue("Filename", m.matches());
   }

}
