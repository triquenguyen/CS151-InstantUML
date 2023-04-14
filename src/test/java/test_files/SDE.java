package test_files;

import java.util.Date;

enum Level {

   I(1), II(1.5), III(2);

   private final double multiplier;

   Level(double multiplier) {
      this.multiplier = multiplier;
   }

   public double getMultiplier() {
      return multiplier;
   }

}

public class SDE extends Developer {

   private Level level;
   private Education education;

   public SDE() {
      this.level = null;
      this.education = null;
   }

   public SDE(String firstName, String lastName, String id, Date birthDate,
              Level level, Education education) {
      super(firstName, lastName, id, birthDate);
      this.level = level;
      this.education = education;
   }

   public Level getLevel() {
      return level;
   }

   public void setLevel(Level level) {
      this.level = level;
   }

   public Education getEducation() {
      return education;
   }

   public void setEducation(Education education) {
      this.education = education;
   }

   @Override
   public double monthlyEarning() {
      return STOCKS_PER_MONTH + DEV_MONTHLY_SALARY * level.getMultiplier();
   }

   @Override
   public String toString() {
      return String.format("%s%nMonthly Salary: %f", super.toString(),
              monthlyEarning());
   }
}
