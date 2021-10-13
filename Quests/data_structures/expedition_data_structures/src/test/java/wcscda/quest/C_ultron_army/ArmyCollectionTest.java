package wcscda.quest.C_ultron_army;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyCollectionTest extends AbstractUltronArmyTest {

    @Test
    public void testAddList() {
        super.add(new ArmyCollection(new ArrayList<>()));
    }

    @Test
    public void testAddSet() {
        super.add(new ArmyCollection(new HashSet<>()));
    }

    @Test
    public void testSizeList() {
        super.size(new ArmyCollection(new ArrayList<>()));
    }

    @Test
    public void testSizeSet() {
        super.size(new ArmyCollection(new HashSet<>()));
    }

    @Test
    public void testGetPatrolList() {
        super.getPatrol(new ArmyCollection(new ArrayList<>()));
    }

    @Test
    public void testGetPatrolSet() {
        super.getPatrol(new ArmyCollection(new HashSet<>()));
    }

    @Test
    public void testPatrolReturnList() {
        super.patrolReturn(new ArmyCollection(new ArrayList<>()));
    }
    @Test
    public void testPatrolReturnSet() {
        super.patrolReturn(new ArmyCollection(new HashSet<>()));
    }

    @Test
    public void performanceTestSmallList() {
        super.performanceTest(new ArmyCollection(new ArrayList<>()), SMALL_TEST_SIZE);
    }

    @Test
    public void performanceTestSmallSet() {
        super.performanceTest(new ArmyCollection(new HashSet<>()), SMALL_TEST_SIZE);
    }

    @Test
    public void performanceTestBigList() {
        super.performanceTest(new ArmyCollection(new ArrayList<>()), BIG_TEST_SIZE);
    }

    @Test
    public void performanceTestBigSet() {
        super.performanceTest(new ArmyCollection(new HashSet<>()), BIG_TEST_SIZE);
    }

}