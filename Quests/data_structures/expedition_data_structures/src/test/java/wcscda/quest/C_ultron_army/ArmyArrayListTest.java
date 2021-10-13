package wcscda.quest.C_ultron_army;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyArrayListTest extends AbstractUltronArmyTest {

    @Test
    public void add() {
        super.add(new ArmyArrayList());
    }

    @Test
    public void size() {
        super.size(new ArmyArrayList());
    }

    @Test
    public void getPatrol() {
        super.getPatrol(new ArmyArrayList());
    }

    @Test
    public void patrolReturn() {
        super.patrolReturn(new ArmyArrayList());
    }

    @Test
    public void performanceTestSmall() {
        super.performanceTest(new ArmyArrayList(), SMALL_TEST_SIZE);
    }

    @Test
    public void performanceTestBig() {
        super.performanceTest(new ArmyArrayList(), BIG_TEST_SIZE);
    }
}