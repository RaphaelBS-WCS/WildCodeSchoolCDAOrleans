package wcs.cda;

import java.util.ArrayList;

// There is nothing to do in this file, it is just an example
public class CQuickSort {
    // This algorithm is the quick sort, it work by finding a value (the pivot)
    public static void sort(ArrayList<Integer> arrayList) {
        sort(arrayList, 0, arrayList.size());
    }

    public static void sort(ArrayList<Integer> arrayList, int start, int end) {
        if(end - start < 2) return;
        int initialStart = start;
        int initialEnd = end;

        int pivotValue = arrayList.get(end - 1);
        end--;

        while(end - start > 0) {
            if(arrayList.get(start) < pivotValue) {
                start++;
                continue;
            }
            if(arrayList.get(end - 1) >= pivotValue) {
                end--;
                continue;
            }
            switchValues(arrayList, start, end - 1);
            start++;
            end--;
        }

        switchValues(arrayList, start, initialEnd - 1);

        sort(arrayList, initialStart, start);
        sort(arrayList, start + 1, initialEnd);
    }

    private static void switchValues(ArrayList<Integer> arrayList, int first, int second) {
        int temp = arrayList.get(second);
        arrayList.set(second, arrayList.get(first));
        arrayList.set(first, temp);
    }
}
