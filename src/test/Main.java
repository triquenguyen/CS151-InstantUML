import test_files.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

   // https://stackoverflow.com/questions/15464111/run-cmd-commands-through-java
   public static void main(String[] args) throws Exception {

      // Initialize the builder for the terminal commands
      ProcessBuilder builder = new ProcessBuilder();
      builder.redirectErrorStream(true);

      // The path to the .class files in the out folder
      File main = new File("DecompilerTest/src/test_files");

      // array of .class files in the out folder
      File[] files = main.listFiles();
      // builder.directory(main);

      for (File file : files){



/*         URL[] cp = {file.toURI().toURL()};
         URLClassLoader urlcl = new URLClassLoader(cp);
         Class clazz = urlcl.loadClass(String.format("%s.%s", "test_files", file.getName()));

         System.out.println(clazz.getName());*/


         // Class clazz = file.;
         Path path = file.toPath();
         Stack<String> stack = new Stack<>();
         Path name = path.getName(path.getNameCount() - 1);;
         for (int i = path.getNameCount() - 2; i >= 0; i--) {
            stack.push(name.toString());
            name = path.getName(i);
            if (name.toString().equals("src"))
               break;
         }
         String result = String.format("");
         while (!stack.isEmpty()) {
            if (stack.size() == 1) {
               String temp = stack.pop();
               String[] strings = temp.split("\\.");
               result += strings[0];
            }
            else
               result += stack.pop() + ".";
         }

         Class clazz = Class.forName(result);

         System.out.println(clazz.getSimpleName());


/*         // Commands to get the method and variable signatures
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

         } while (line != null);*/
      }

/*      boolean DeveloperInfo = test_files.DeveloperInfo.class.isInterface();
      System.out.println(DeveloperInfo);

      Field[] list = Tree.class.getDeclaredFields();

      for (Field field : list) {
         System.out.println(field.getType());
      }

      Class[] classes = Tree.class.getDeclaredClasses();
      for (Class clazz : classes) {
         System.out.println(clazz.getSimpleName());
      }

      // Class c = Package.getPackages("test_files");
      // Class c = Class.forName("Package");
      Package p = Class.forName("java.lang.Package").getPackage();;

      System.out.println(p.getName());*/
   }

}
