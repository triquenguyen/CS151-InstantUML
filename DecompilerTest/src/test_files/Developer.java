package test_files;

import java.util.Date;

public abstract class Developer implements DeveloperInfo {

   private String firstName;
   private String lastName;
   private String id;
   private Date birthDate;

   protected Developer() {
      firstName = null;
      lastName = null;
      id = null;
      birthDate = null;
   }

   protected Developer(String firstName, String lastName, String id,
                    Date birthDate) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.id = id;
      this.birthDate = birthDate;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Date getBirthDate() {
      return birthDate;
   }

   public void setBirthDate(Date birthDate) {
      this.birthDate = birthDate;
   }

   @Override
   public String toString() {
      return String.format
              ("ID Employee number: %s %nEmployee name: %s %s %nBirth date: %s"
                      , id, firstName, lastName, birthDate);
   }

   public abstract double monthlyEarning();

}
