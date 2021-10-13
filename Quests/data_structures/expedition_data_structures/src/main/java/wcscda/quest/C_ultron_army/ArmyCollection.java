package wcscda.quest.C_ultron_army;

import wcscda.quest.Sentinel;

import java.util.Collection;
import java.util.Set;

public class ArmyCollection implements UltronArmy {
    private final Collection<Sentinel> collection;

    public ArmyCollection(Collection<Sentinel> collection) {
        this.collection = collection;
    }

    @Override
    public boolean add(Sentinel sentinel) {
        collection.add(sentinel);
        return true;
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public Sentinel[] getPatrol(int patrolSize) {
        int i = 0;
        Sentinel[] patrol = new Sentinel[patrolSize];

        for ( Sentinel sentinel : collection) {
            patrol[i] = sentinel;
            i++;
            if (i == patrolSize) {
                break;
            }
        }
        for (Sentinel sentinel: patrol) {
            collection.remove(sentinel);
        }
        return patrol;
    }

    @Override
    public void patrolReturn(Sentinel[] sentinels) {
        for (int i = 0; i < sentinels.length; i++) {
            collection.add(sentinels[i]);
        }
    }

    @Override
    public boolean contains(Sentinel sentinel) {
        return collection.contains(sentinel);
    }
}
