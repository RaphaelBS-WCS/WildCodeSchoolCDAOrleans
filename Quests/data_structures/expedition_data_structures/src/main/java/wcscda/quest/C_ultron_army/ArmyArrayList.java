package wcscda.quest.C_ultron_army;

import wcscda.quest.Sentinel;

import java.util.ArrayList;
import java.util.Arrays;

public class ArmyArrayList implements UltronArmy {
    private ArrayList<Sentinel> sentinelArrayList = new ArrayList<>();

    @Override
    public boolean add(Sentinel sentinel) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Sentinel[] getPatrol(int patrolSize) {
        return new Sentinel[0];
    }

    @Override
    public void patrolReturn(Sentinel[] sentinels) {

    }

    @Override
    public boolean contains(Sentinel sentinel) {
        return sentinelArrayList.contains(sentinel);
    }
}
