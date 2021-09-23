import com.sun.jdi.event.StepEvent;

public abstract class Animal {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShout(String shout) {
        this.shout = shout;
    }

    public void setWayToMove(String wayToMove) {
        this.wayToMove = wayToMove;
    }

    String name;

    String shout;

    String wayToMove;

    public void move(){
        System.out.println("Je  " + this.wayToMove);
    }

    public void crier() {
        System.out.println(this.shout);
    }

}
