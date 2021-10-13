package wcscda.utils.dark_side;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public abstract class Vardump {
  private static class MethodComparator implements Comparator<Method> {
    @Override
    public int compare(Method m0, Method m1) {
      return m0.getName().compareTo(m1.getName());
    }
  }

  public static void dump(Object o) {
    dump(o, true);
  }

  public static void dump(Object o, boolean includeFields) {
    dump(o, includeFields, false);
  }

  public static void dump(Object o, boolean includeFields, boolean includeProperty) {
    /*Field[] fields = o.getClass().getFields() ;



    java.lang.reflect.Method[] fs = o.getClass().getMethods();
    ArrayList<Method> methodList = new ArrayList<Method>(Arrays.asList(fs));

    methodList.sort(new MethodComparator());
    String result = o.getClass().getSimpleName();

    for (Method m : methodList) {

      if (m.getName().startsWith("get")
          && m.getName() != "getClass"
          && ((m.getModifiers() & Modifier.STATIC) == 0)) {

        try {
          result += ";" + m.invoke(this).toString();
        } catch (NullPointerException e) {
          result += ";NULL";
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
          result += ";ERROR";
        }
      }
    }

    System.out.println(result);*/
  }
}
