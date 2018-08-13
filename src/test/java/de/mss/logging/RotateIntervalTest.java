package de.mss.logging;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RotateIntervalTest {

   @Test
   public void testGetHourly() {
      RotateInterval r = RotateInterval.getByName("hourly");

      assertEquals("Name", RotateInterval.HOURLY.getName(), r.getName());
      assertEquals("Diff", RotateInterval.HOURLY.getDifference(), r.getDifference());
   }


   @Test
   public void testGetDaily() {
      RotateInterval r = RotateInterval.getByName("daily");

      assertEquals("Name", RotateInterval.DAILY.getName(), r.getName());
      assertEquals("Diff", RotateInterval.DAILY.getDifference(), r.getDifference());
   }


   @Test
   public void testGetWeekly() {
      RotateInterval r = RotateInterval.getByName("weekly");

      assertEquals("Name", RotateInterval.WEEKLY.getName(), r.getName());
      assertEquals("Diff", RotateInterval.WEEKLY.getDifference(), r.getDifference());
   }


   @Test
   public void testGetSonething() {
      RotateInterval r = RotateInterval.getByName("something totally other");

      assertEquals("Name", RotateInterval.HOURLY.getName(), r.getName());
      assertEquals("Diff", RotateInterval.HOURLY.getDifference(), r.getDifference());
   }


}
