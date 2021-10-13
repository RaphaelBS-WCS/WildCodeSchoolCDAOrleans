package wcscda.utils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class App {
  public static void main(String[] args) throws JsonProcessingException, IllegalAccessException {
    System.out.println("4 / 5 * 5 => " + (4 / 5 * 5));

    Collection<Worm> collection = new ArrayList<>();
    collection.remove(5);
  }

    public static void main2(String[] args) throws JsonProcessingException, IllegalAccessException {


    Player player = new Player("Nico");
    Worm worm = new Worm(player, "Rambo");

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = ow.writeValueAsString(worm);

    System.out.println(json);

    ObjectWriter oxw = new XmlMapper().writer().withDefaultPrettyPrinter();
    json = oxw.writeValueAsString(worm);

    System.out.println(json);

    ArrayList<Integer> arrayList = new ArrayList<>();
    arrayList.add(5);
    arrayList.add(2);
    arrayList.add(7);
    arrayList.add(9);
    arrayList.add(null);
    Field[] fields = ArrayList.class.getDeclaredFields();

    Arrays.stream(fields).forEach(f -> {
      f.setAccessible(true);
      try {
        String s = f.getName() + " => " + ow.writeValueAsString(
                f.get(arrayList));
        System.out.println(s);
      }
      catch (IllegalAccessException iae) {
      }
      catch(IllegalArgumentException illegalArgumentException)
      {
        System.out.println("ERROR with : " + f.getName());
        System.out.println(illegalArgumentException);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }

    });
  }
}
