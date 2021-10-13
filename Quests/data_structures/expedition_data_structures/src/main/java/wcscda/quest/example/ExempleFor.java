package wcscda.quest.example;

import java.util.ArrayList;
import java.util.Iterator;

public class ExempleFor {
    static class HelloIterator implements Iterator<String> {
        private int nbHello;

        public HelloIterator(int nbHello) {
            this.nbHello = nbHello;
        }

        @Override
        public boolean hasNext() {
            return nbHello > 0;
        }

        @Override
        public String next() {
            nbHello--;
            return "Bonjour";
        }
    }

    static class MyIterable implements Iterable<String> {
        private final int nbHello;

        public MyIterable(int nbHello) {
            this.nbHello = nbHello;
        }

        @Override
        public Iterator<String> iterator() {
            return new HelloIterator(nbHello);
        }
    }

    public static void main(String[] args) {
        MyIterable iterable = new MyIterable(8);
        ArrayList<String> al = new ArrayList<>();

        iterable.forEach(System.out::println);
        /*for(String s: iterable) {
            System.out.println(s);
        }*/
    }
}
