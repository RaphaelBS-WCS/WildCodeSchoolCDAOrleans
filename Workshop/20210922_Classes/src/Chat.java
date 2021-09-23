public class Chat extends Animal implements paws{

    private Object paws;

    @Override
    public Integer getPaws() {
        return (Integer) paws;
    }

    @Override
    public void setPaws(Integer NbPaws) {
        this.paws = NbPaws;
    }

    public Chat(String name) {
        this.setName(name);
        this.setPaws(4);
        this.setShout("miaou");
        this.setWayToMove("marche");
    }
}
