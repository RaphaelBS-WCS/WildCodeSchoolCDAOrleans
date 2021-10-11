package wcscda.quest.B_generic;

public class IntegerFixedSizeCollection extends FixedSizeCollection<Integer>{
    public IntegerFixedSizeCollection(int maxSize) {
        super(Integer.class, maxSize);
    }
}
