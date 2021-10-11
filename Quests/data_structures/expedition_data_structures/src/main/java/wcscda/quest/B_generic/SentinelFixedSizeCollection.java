package wcscda.quest.B_generic;

import wcscda.quest.Sentinel;

public class SentinelFixedSizeCollection extends FixedSizeCollection<Sentinel>{
    public SentinelFixedSizeCollection(int maxSize) {
        super(Sentinel.class, maxSize);
    }
}
