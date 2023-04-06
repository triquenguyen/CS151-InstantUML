import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Main {

   // https://stackoverflow.com/questions/15464111/run-cmd-commands-through-java
   public static void main(String[] args) throws Exception {

      // Initialize the builder for the terminal commands
      ProcessBuilder builder = new ProcessBuilder();
      builder.redirectErrorStream(true);

      // The path to the .class files in the out folder
      File main = new File("out/production/DecompilerTest/test_files");

      // array of .class files in the out folder
      File[] files = main.listFiles();
      builder.directory(main);

      for (File file : files) {

         // Commands to get the method and variable signatures
         builder.command("cmd.exe", "/c", "javap -p " + file.getName());

         // line to start and run the process for each .class file
         Process p = builder.start();
         BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
         String line = "";
         String[] s;


         // Print out all .class info
         do {

            System.out.println(line);

            line = r.readLine();

         } while (line != null);
      }
   }
}
