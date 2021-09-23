public class Chien extends Animal implements paws  {

    private Object paws;

    @Override
    public Integer getPaws() {
        return (Integer) paws;
    }

    @Override
    public void setPaws(Integer NbPaws) {
        this.paws = NbPaws;
    }

    public Chien(String name) {
        this.setName(name);
        this.setShout("Wouaf");
        this.setWayToMove("marche");
    }
}
