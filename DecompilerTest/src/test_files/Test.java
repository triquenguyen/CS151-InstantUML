package test_files;

import java.util.ArrayList;
import java.util.Date;

public class Test {

   private ArrayList<Developer> developers;

   Test() {
      developers = new ArrayList<>();
   }

   /**
    * Where the tests are
    *
    * @param args
    */
   public static void main(String[] args) {

      Test test = new Test();

      test.addIntern("John", "Doe", "1234",
              new Date("07/02/2001"), 20);

      test.addSDE("Jane", "Doe", "4321",
              new Date("07/02/2000"), Level.I,
              new Education(Degree.BS, Major.CS));

      test.addPartTimer("Bob", "Rick", "5555",
              new Date("07/02/2002"), 17, 25);

      // - Test 1 (Print all method, checking if info is correct) -
      System.out.println
              ("Test 1 (Print all method, checking if info is correct):\n");
      test.printAll();

      // - Test 2 (Changing Developer Level, then single print) -
      System.out.println
              ("Test 2 (Changing Developer Level, then single print)\n");
      test.changeLevel("Jane", "Doe", Level.II);

      test.printDeveloperInfo("Jane", "Doe");

      // - Test 3 (Change Hourly rate) -
      System.out.println("\nTest 3 (Change Hourly rate)\n");
      test.changeHourlyRate("Bob", "Rick", 20);
      test.printDeveloperInfo("Bob", "Rick");

      // - Test 4 (Failed and Successful Removal) -
      System.out.println("\nTest 4 (Failed and Successful Removal)\n");
      test.removeDeveloper("John", "Dough","1234");
      test.printDeveloperInfo("John", "Doe");
      test.removeDeveloper("John", "Doe","1234");
      test.printDeveloperInfo("John", "Doe");

      // - Test 5 (Change Level and Change Hourly rate fail) -
      System.out.println
              ("\nTest 5 (Change Level and Change Hourly rate fail)\n");

      // Will fail because not instanceof right class
      System.out.println(test.changeHourlyRate("Jane", "Doe", 25));
      System.out.println(test.changeLevel("Bob", "Rick", Level.III));

      // Monthly salary should not change
      test.printAll();

   }

   public void addIntern(String firstName, String lastName, String id,
                         Date birthDate, double hourlyRate) {
      Intern developer = new Intern(firstName, lastName, id, birthDate,
              hourlyRate);
      developers.add(developer);
   }

   public void addSDE(String firstName, String lastName, String id,
                      Date birthDate, Level level, Education education) {
      SDE developer = new SDE(firstName, lastName, id, birthDate, level,
              education);
      developers.add(developer);
   }

   public void addPartTimer(String firstName, String lastName, String id,
                            Date birthDate, double hourlyRate,
                            int hoursWorkedPerWeek) {
      PartTime developer = new PartTime(firstName, lastName, id, birthDate,
              hourlyRate, hoursWorkedPerWeek);
      developers.add(developer);
   }

   /**
    *
    *
    * @param firstName
    * @param lastName
    * @return
    */
   public boolean removeDeveloper(String firstName, String lastName, String id) {

      for (int i = 0; i < developers.size(); i++) {

         // Remove if first name, last name, and id match
         if (developers.get(i).getFirstName().equals(firstName)
                 && developers.get(i).getLastName().equals(lastName)
                 && developers.get(i).getId().equals(id)) {
            developers.remove(i);
            return true;
         }

      }
      return false;
   }

   public boolean changeHourlyRate(String firstName, String lastName,
                                   double hourlyRate) {

      // Find the first developer with the same first and last name
      for (Developer d : developers) {
         if (d.getFirstName().equals(firstName) &&
                 d.getLastName().equals(lastName) && d instanceof Intern dev) {

            // If the instanceof went through, dev is the cast
            dev.setHourlyRate(hourlyRate);
            return true;
         }
      }
      return false;
   }

   public boolean changeLevel(String firstName, String lastName,
                              Level level) {

      // Find the first developer with the same first and last name
      for (Developer d : developers) {
         if (d.getFirstName().equals(firstName) &&
                 d.getLastName().equals(lastName) && d instanceof SDE dev) {

            // If the instanceof went through, dev is the cast
            dev.setLevel(level);
            return true;
         }
      }
      return false;
   }

   public void printDeveloperInfo(String firstName, String lastName) {

      // Find the first developer with the same first and last name
      for (Developer d : developers) {
         if (d.getFirstName().equals(firstName) &&
                 d.getLastName().equals(lastName)) {
            System.out.println(d);
            return;
         }
      }

         // If not in list, "out" instead of "err" so it is not below all output
         System.out.println("Developer Not in list");
   }

   public void printAll() {
      for (Developer d : developers) {
         System.out.printf("%n%s%n", d);
      }
   }

}
