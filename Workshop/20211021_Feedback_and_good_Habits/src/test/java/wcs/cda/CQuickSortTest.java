package wcs.cda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CQuickSortTest {

    @Test
    void quickSort() {
        for(int length: new int[]{10, 100, 1000}) {
            ArrayList<Integer> testArray = new ArrayList<>();

            for(int i = 0; i < length; ++i) {
                testArray.add(i);
            }

            Collections.shuffle(testArray);
            CQuickSort.sort(testArray);

            for(int i = 0; i < length; ++i) {
                assertEquals(i, testArray.get(i));
            }
        }
    }
}