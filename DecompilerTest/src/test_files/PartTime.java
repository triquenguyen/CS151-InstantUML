package test_files;

import java.util.Date;

public class PartTime extends Intern {

   private int hoursWorkedPerWeek;

   public PartTime() {
      hoursWorkedPerWeek = 0;
   }

   public PartTime(String firstName, String lastName, String id, Date birthDate,
                   double hourlyRate, int hoursWorkedPerWeek) {
      super(firstName, lastName, id, birthDate, hourlyRate);
      this.hoursWorkedPerWeek = hoursWorkedPerWeek;
   }

   public Integer getHoursWorkedPerWeek() {
      return hoursWorkedPerWeek;
   }

   public void setHoursWorkedPerWeek(Integer hoursWorkedPerWeek) {
      this.hoursWorkedPerWeek = hoursWorkedPerWeek;
   }

   @Override
   public double monthlyEarning() {
      return getHourlyRate() * 4 * hoursWorkedPerWeek;
   }

   @Override
   public String toString() {
      return String.format("ID Employee number: %s %nEmployee name: " +
                      "%s %s %nBirth date: %s %nHours worked per month: %d" +
                      "%nMonthly Salary: %f"
              , getId(), getFirstName(), getLastName(), getBirthDate(),
              4 * hoursWorkedPerWeek, monthlyEarning());
   }
}
