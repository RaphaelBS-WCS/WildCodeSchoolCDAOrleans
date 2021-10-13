package wcscda.quest.C_ultron_army;

import wcscda.quest.Sentinel;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractUltronArmyTest {
    protected static final int BIG_TEST_SIZE = 1000;
    protected static final int SMALL_TEST_SIZE = 100;

    private static Random randomInstance;

    protected void add(UltronArmy army) {
        assertEquals(0, army.size());
        army.add(new Sentinel());
        assertEquals(1, army.size());
    }

    protected void size(UltronArmy army) {
        for(int i = 0; i < 10; ++i) {
            assertEquals(i, army.size());
            army.add(new Sentinel());
        }
    }

    protected void getPatrol(UltronArmy army) {
        addSentinels(army, 100);

        assertEquals(100, army.size());
        Sentinel[] patrol = army.getPatrol(10);
        assertEquals(10, patrol.length);
        assertEquals(90, army.size());

        for(Sentinel sentinel: patrol) {
            assert(!army.contains(sentinel));
        }
    }

    protected void patrolReturn(UltronArmy army) {
        addSentinels(army, 100);

        assertEquals(100, army.size());
        Sentinel[] patrol = army.getPatrol(10);
        assertEquals(10, patrol.length);
        assertEquals(90, army.size());

        for(Sentinel sentinel: patrol) {
            assert(!army.contains(sentinel));
        }

        army.patrolReturn(patrol);

        assertEquals(100, army.size());

        for(Sentinel sentinel: patrol) {
            assert(army.contains(sentinel));
        }
    }

    protected void performanceTest(UltronArmy army, int nbElts) {
        addSentinels(army, nbElts);

        for(int i = 0; i < nbElts; ++i) {
            Sentinel[] patrol = army.getPatrol(getRandom().nextInt(nbElts / 2) + 1);
            army.patrolReturn(patrol);
        }
    }

    private static Random getRandom() {
        if(randomInstance == null) {
            randomInstance = new Random(0);
        }

        return randomInstance;
    }

    private void addSentinels(UltronArmy army, int nbToAdd) {
        for(int i = 0; i < nbToAdd; ++i) {
            army.add(new Sentinel());
        }
    }
}
