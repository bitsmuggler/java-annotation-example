package com.bitsmuggler.learning;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class RunTests {


  public static void main(String[] args) {
    System.out.println("Testing ....");

    Class<SpecialTestExample> obj = SpecialTestExample.class;

    getTesterInfo(obj);

    processTest(obj);
  }

  private static void processTest(Class<SpecialTestExample> obj) {
    int passed = 0, failed = 0, count = 0, ignore = 0;

    // Process @Test
    for (Method method : obj.getDeclaredMethods()) {

      // if method is annotated with @Test
      if (method.isAnnotationPresent(SpecialTest.class)) {

        Annotation annotation = method.getAnnotation(SpecialTest.class);
        SpecialTest test = (SpecialTest) annotation;

        // if enabled = true (default)
        if (test.enabled()) {

          try {
            method.invoke(obj.newInstance());
            System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
            passed++;
          } catch (Throwable ex) {
            System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
            failed++;
          }

        } else {
          System.out.printf("%s - Test '%s' - ignored%n", ++count, method.getName());
          ignore++;
        }

      }

    }
    System.out.printf("%nResult : Total : %d, Passed: %d, Failed %d, Ignore %d%n", count, passed, failed, ignore);
  }

  private static void getTesterInfo(Class<SpecialTestExample> obj) {
    if(obj.isAnnotationPresent(SpecialTesterInfo.class)) {
      SpecialTesterInfo testerInfo = obj.getAnnotation(SpecialTesterInfo.class);;

      System.out.printf("%nPriority: %s", testerInfo.priority());
      System.out.printf("%nCreatedBy: %s", testerInfo.createdBy());
      System.out.printf("%nTags :");

      int tagLength = testerInfo.tags().length;
      for (String tag : testerInfo.tags()) {
        if (tagLength > 1) {
          System.out.print(tag + ", ");
        } else {
          System.out.print(tag);
        }
        tagLength--;
      }
    }
  }

}
