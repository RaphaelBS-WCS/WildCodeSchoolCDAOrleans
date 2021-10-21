package wcs.cda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DFusionSortTest {

    @Test
    void fusionSort() {
        for(int length: new int[]{10, 100, 1000}) {
            ArrayList<Integer> testArray = new ArrayList<>();

            for(int i = 0; i < length; ++i) {
                testArray.add(i);
            }

            Collections.shuffle(testArray);
            DFusionSort.sort(testArray);

            for(int i = 0; i < length; ++i) {
                assertEquals(i, testArray.get(i));
            }
        }
    }

    @Test
    void merge() {
        List<Integer> list1 = Arrays.asList(new Integer[] {1, 3, 4, 8});
        List<Integer> list2 = Arrays.asList(new Integer[] {2, 5, 6, 10});

        assertArrayEquals(
                DFusionSort.merge(list1, list2).toArray(),
                new Integer[] {1, 2, 3, 4, 5, 6, 8, 10}
        );
    }
}