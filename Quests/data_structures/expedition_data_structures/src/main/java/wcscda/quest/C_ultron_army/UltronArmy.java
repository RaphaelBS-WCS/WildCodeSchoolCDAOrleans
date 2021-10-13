package wcscda.quest.C_ultron_army;

import wcscda.quest.Sentinel;

public interface UltronArmy {
    // Add a sentinel to the army
    boolean add(Sentinel sentinel);

    // Get the size of the army
    int size();

    // Get a patrol out of the army (so this method
    // reduce the army size by patrolSize and return null
    // if patrolSize > armySize
    Sentinel[] getPatrol(int patrolSize);

    // Put back the patrol in the army (this method increase
    // army size by sentinels.length
    void patrolReturn(Sentinel[] sentinels);

    boolean contains(Sentinel sentinel);
}
