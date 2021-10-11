package wcscda.quest.B_generic;

import wcscda.quest.Sentinel;

import java.lang.reflect.Array;
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
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
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