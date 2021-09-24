public class Oiseaux extends Podes implements lay {

    @Override
    public Egg lay() {
        return new Egg();
    }

    public Oiseaux(String name) {

        this.name = name;
        this.wayToMove = "nage";
        this.shout = "bublllbulllbll";
        this.paws = 2;
    }
}


