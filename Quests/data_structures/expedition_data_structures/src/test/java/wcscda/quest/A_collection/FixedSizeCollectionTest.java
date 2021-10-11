package wcscda.quest.A_collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FixedSizeCollectionTest {
    private FixedSizeCollection fixture = new FixedSizeCollection(5);

    @Test
    public void sizeAndAdd() {
        for(int i = 0; i < 7;++i) {
            assertEquals(Math.min(i, 5), fixture.size());
            fixture.add(0);
        }
    }

    @Test
    public void testConstructor() {
        // Just checking it doesn't rise exception
        new FixedSizeCollection(5);
    }

    @Test
    public void isEmpty() {
        for(int i = 0; i < 7.;++i) {
            assertEquals(i == 0, fixture.isEmpty());
            fixture.add(0);
        }
    }

    @Test
    public void contains() {
        for(int i = 0; i < 10; ++i) {
            fixture.add(i*i);
        }

        Integer[] included = new Integer[]{
                0, 1, 4, 9, 16
        };
        List<Integer> includedAsList = Arrays.asList(included);

        for(int i = 0; i < 100; i++) {
            assertEquals(
                    includedAsList.contains(i),
                    fixture.contains(i)
            );
        }


    }

    @Test
    public void remove() {
        fixture.add(0);
        fixture.add(4);
        fixture.add(9);
        fixture.add(13);
        fixture.add(22);

        assertArrayEquals(new Integer[]{0, 4, 9, 13, 22}, fixture.toArray());
        assert(!fixture.remove(1));
        assertEquals(5, fixture.size());
        assert(fixture.remove(9));
        assertEquals(4, fixture.size());

        assertArrayEquals(new Integer[]{0, 4, 13, 22, null}, fixture.toArray());
        assert(!fixture.remove(9));
        assert(fixture.remove(0));
        assertEquals(3, fixture.size());
        assertArrayEquals(new Integer[]{4, 13, 22, null, null}, fixture.toArray());
    }

    @Before
    public void setUp() throws Exception {
        fixture = new FixedSizeCollection(5);
    }
}