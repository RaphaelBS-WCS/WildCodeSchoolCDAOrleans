public abstract class Podes extends Animal{
    public Podes(String name, String shout, String wayToMove, int paws) {
        super(name, shout, wayToMove);
        this.paws = paws;
    }

    public int getPaws() {
        return paws;
    }

    protected int paws;
}
