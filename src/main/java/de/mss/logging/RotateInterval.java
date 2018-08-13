package de.mss.logging;


public enum RotateInterval {
   HOURLY("hourly", 3600),
   DAILY("daily", 24 * 3600),
   WEEKLY("weekly", 7 * 24 * 3600);

   private String name = null;
   private long   diff = 0l;


   private RotateInterval(String n, long d) {
      this.name = n;
      this.diff = d;
   }


   public String getName() {
      return this.name;
   }


   public long getDifference() {
      return this.diff;
   }


   public static RotateInterval getByName(String n) {
      for (RotateInterval r : RotateInterval.values())
         if (r.getName().equals(n))
            return r;

      return RotateInterval.HOURLY;
   }
}
