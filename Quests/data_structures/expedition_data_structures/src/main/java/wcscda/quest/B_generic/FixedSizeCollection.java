package wcscda.quest.B_generic;

import wcscda.quest.Sentinel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class FixedSizeCollection<T> implements Collection<T> {
    private int maxSize;
    private T[] array;
    private int currentSize;

    public FixedSizeCollection(Class<T> clazz, int maxSize) {
        this.maxSize = maxSize;
        array = (T[])Array.newInstance(clazz, maxSize);
        currentSize = 0;
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
            if (array[i] == o) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return null;
    }

    @Override
    public boolean add(T t) {
        for ( int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = t;
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
    public boolean addAll(Collection<? extends T> collection) {
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