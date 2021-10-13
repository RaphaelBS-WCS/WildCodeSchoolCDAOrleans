package wcscda.quest.C_ultron_army;

import wcscda.quest.Sentinel;

import java.util.Collection;

public class ArmyCollection implements UltronArmy {
    private final Collection<Sentinel> collection;

    public ArmyCollection(Collection<Sentinel> collection) {
        this.collection = collection;
    }

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
        return false;
    }
}
