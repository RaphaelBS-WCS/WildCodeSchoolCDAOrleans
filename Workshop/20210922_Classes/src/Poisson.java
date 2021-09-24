public class Poisson extends Animal implements lay {

    @Override
    public Egg lay() {
        return new Egg();
    }

    public Poisson(String name){

        this.name = name;
        this.wayToMove = "nage";
        this.shout = "bublllbulllbll";
    }
}
