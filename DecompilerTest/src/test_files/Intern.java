package test_files;

import java.util.Date;

public class Intern extends Developer {

   private double hourlyRate;

   public Intern() {
      this.hourlyRate = 0;
   }

   public Intern(String firstName, String lastName, String id, Date birthDate,
                 double hourlyRate) {
      super(firstName, lastName, id, birthDate);
      this.hourlyRate = hourlyRate;
   }

   public double getHourlyRate() {
      return hourlyRate;
   }

   public void setHourlyRate(double hourlyRate) {
      this.hourlyRate = hourlyRate;
   }

   @Override
   public double monthlyEarning() {
      return hourlyRate * INTERN_MONTHLY_HOURS_WORKED;
   }

   @Override
   public String toString() {
      return String.format("%s%nMonthly Salary: %f", super.toString(),
              monthlyEarning());
   }
}
