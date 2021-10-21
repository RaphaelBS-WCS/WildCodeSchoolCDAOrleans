package wcs.cda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BStream {
    // Here is the code for counting the number of student studying java
    public static int countStudent(String[] subjects) {
        return (int)Arrays.stream(subjects)
                // The argument of filter is called a lambda
                .filter(s -> s.toLowerCase().equals("java"))
                .count();
    }

    // Exercice 1 : Using that syntax, write a method that receive
    // an array of int corresponding at the age and return the
    // the number of persons of 18 or above
    public static int countLegalAge(Integer[] ages) {
        return 0;
    }

    public static class Student {
        public Student(String name) { this.name = name; }
        public String name;
        public int age = 20;
        public ArrayList<String> subjects = new ArrayList<>();
    }

    // Exemple d'utilisation de map
    public static List<Student> createStudents(String[] names) {
        return Arrays.stream(names)
                .map(n -> new Student(n))
                .collect(Collectors.toList());
    }

    // Exercice 2 : Créez une méthode qui prend en argument
    // une liste d'étudiants et un sujet, et ajoute le
    // sujet à tous les étudiants
    // Sans utiliser de boucle for
    public static void addSubject(List<Student> students, String subject) {
        // TODO
    }
}
