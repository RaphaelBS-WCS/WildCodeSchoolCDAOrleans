package wcscda.quest.A_collection;

import wcscda.quest.Sentinel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class FixedSizeCollection implements Collection<Integer> {
    private Integer[] array;

    public FixedSizeCollection(int maxSize) {
        this.array = new Integer[maxSize];
    }

    @Override
    public int size() {
        int s = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] == null) {
                s++;
            }
        }
         int j = array.length - s;
        return j;
    }

    @Override
    public boolean isEmpty() {
        boolean e = true;
        for (int i = 0; i < array.length; i++) {
            if(array[i] != null) {
                e = false;
            }
        }
        return e;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(Integer integer) {
        for ( int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = integer;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        for ( int i = 0; i < array.length; i++) {
            if (array[i] == o) {
                while (i < array.length -1) {
                    array[i] = array[i +1];
                    i++;
                }
                array[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }
}