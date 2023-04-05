package test_files;

enum Degree {

   MS, PHD, BS

}

enum Major {

   CS, SOFTWARE_ENGINEERING, APPLIED_MATH, ELECTRICAL_ENGINEERING, PHYSICS,
   DATA_SCIENCE

}

public class Education {

   private Degree degree;
   private Major major;

   public Education() {
      degree = null;
      major = null;
   }

   public Education(Degree degree, Major major) {
      this.degree = degree;
      this.major = major;
   }

   public Degree getDegree() {
      return degree;
   }

   public void setDegree(Degree degree) {
      this.degree = degree;
   }

   public Major getMajor() {
      return major;
   }

   public void setMajor(Major major) {
      this.major = major;
   }



}
