public class Poisson extends Animal implements lay {

    @Override
    public Egg lay() {
        return new Egg();
    }

    public Poisson(String name){

        this.setName(name);
        this.setWayToMove("rampe");
        this.setShout("bublllbulllbll");
    }
}
