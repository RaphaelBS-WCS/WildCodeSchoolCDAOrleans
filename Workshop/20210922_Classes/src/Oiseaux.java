public class Oiseaux extends Animal implements paws, lay {

    private Object paws;

    @Override
    public Egg lay() {
        return new Egg();
    }

    @Override
    public Integer getPaws() {
        return (Integer) paws;
    }

    @Override
    public void setPaws(Integer NbPaws) {
        this.paws = NbPaws;
    }

    public Oiseaux(String name) {

        this.setName(name);
        this.setWayToMove("vole");
        this.setShout("Cuicui");
        this.setPaws(2);
    }
}


