package wcscda.quest.C_ultron_army;

import java.util.ArrayList;

public abstract class ArrayListUtil {
    public static <E> E pop(ArrayList<E> arrayList) {
        return arrayList.remove(arrayList.size() - 1);
    }
}
