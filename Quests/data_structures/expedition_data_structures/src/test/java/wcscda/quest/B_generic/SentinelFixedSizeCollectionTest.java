package wcscda.quest.B_generic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wcscda.quest.Sentinel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SentinelFixedSizeCollectionTest {
    private SentinelFixedSizeCollection fixture = new SentinelFixedSizeCollection(5);

    @Test
    public void sizeAndAdd() {
        for(int i = 0; i < 7;++i) {
            assertEquals(Math.min(i, 5), fixture.size());
            fixture.add(new Sentinel());
        }
    }

    @Test
    public void testConstructor() {
        // Just checking it doesn't rise exception
        new SentinelFixedSizeCollection(5);
    }

    @Test
    public void isEmpty() {
        for(int i = 0; i < 7.;++i) {
            assertEquals(i == 0, fixture.isEmpty());
            fixture.add(new Sentinel());
        }
    }

    @Test
    public void contains() {
        ArrayList<Sentinel> fullList = new ArrayList<>();
        for(int i = 0; i < 10; ++i) {
            Sentinel sentinel = new Sentinel();
            fullList.add(sentinel);
            if(i % 3 == 0) {
                fixture.add(sentinel);
            }
        }

        for(int i = 0; i < 10; i++) {
            assertEquals(
                    i % 3 == 0,
                    fixture.contains(fullList.get(i))
            );
        }
    }

    @Test
    public void remove() {
        Sentinel sentinel1 = new Sentinel();
        Sentinel sentinel2 = new Sentinel();
        Sentinel sentinel3 = new Sentinel();

        fixture.add(sentinel1);
        fixture.add(sentinel2);

        assert(!fixture.remove(sentinel3));
        assertEquals(2, fixture.size());
        assert(fixture.remove(sentinel1));
        assertEquals(1, fixture.size());
        assert(!fixture.remove(sentinel1));
        assert(fixture.toArray()[0] != null);
    }

    @BeforeEach
    public void setUp() throws Exception {
        fixture = new SentinelFixedSizeCollection(5);
    }
}