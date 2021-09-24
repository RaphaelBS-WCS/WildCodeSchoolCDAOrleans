public class Serpent extends Animal implements lay{
    @Override
    public Egg lay() {
        return new Egg();
    }

    public Serpent(String name){
        this.name = name;
        this.wayToMove = "rampe";
        this.shout = "SssSSssssss";
    }
}
