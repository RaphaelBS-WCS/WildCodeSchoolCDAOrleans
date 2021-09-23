public class Oiseaux extends Podes implements paws, lay {

    @Override
    public Egg lay() {
        return new Egg();
    }

    public Oiseaux(String name) {

        this.setName(name);
        this.setWayToMove("vole");
        this.setShout("Cuicui");
        this.setPaws(2);
    }
}


