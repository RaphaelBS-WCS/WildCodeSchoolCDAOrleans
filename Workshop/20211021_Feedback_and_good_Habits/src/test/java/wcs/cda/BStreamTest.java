package wcs.cda;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BStreamTest {

    @Test
    void countStudent() {
        assertEquals(0, BStream.countStudent(new String[] {"C++", "php"}));
        assertEquals(1, BStream.countStudent(new String[] {"C++", "php", "JaVa"}));
        assertEquals(2, BStream.countStudent(new String[] {"C++", "php", "JaVa", "java"}));
    }

    @Test
    void countLegalAge() {
        assertEquals(0, BStream.countLegalAge(new Integer[] {15, 16}));
        assertEquals(0, BStream.countLegalAge(new Integer[] {15, 16, 17}));
        assertEquals(3, BStream.countLegalAge(new Integer[] {15, 16, 17, 18, 19, 20}));
        assertEquals(2, BStream.countLegalAge(new Integer[] {18, 19}));
    }

    @Test
    void addSubject() {
        List<BStream.Student> students = BStream.createStudents(
                new String[] {"Joe", "Jack", "William", "Averell"}
        );

        BStream.addSubject(students, "java");

        assertEquals(4, students
                .stream()
                .filter(s -> s.subjects.contains("java"))
                .count()
        );
    }
}