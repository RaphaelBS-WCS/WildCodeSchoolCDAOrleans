import com.sun.jdi.event.StepEvent;
public abstract class Animal {

    private String name;

    private String shout;

    private String wayToMove;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setShout(String shout) {
        this.shout = shout;
    }

    public void setWayToMove(String wayToMove) {
        this.wayToMove = wayToMove;
    }

    public void move(){
        System.out.println("Je  " + this.wayToMove);
    }

    public void crier() {
        System.out.println(this.shout);
    }
}
