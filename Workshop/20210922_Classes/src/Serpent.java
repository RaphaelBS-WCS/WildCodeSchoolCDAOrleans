public class Serpent extends Animal implements lay{
    @Override
    public Egg lay() {
        return new Egg();
    }

    public Serpent(String name){
        this.setName(name);
        this.setWayToMove("crawl");
        this.setShout("SSsssSSSSssss");
    }
}
