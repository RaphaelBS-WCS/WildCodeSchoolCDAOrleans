import com.sun.jdi.event.StepEvent;

public abstract class Animal {

    String name;

    String shout;

    String wayToMove;

    public String getName() {
        return name;
    }

    public void move(){
        System.out.println("Je  " + this.wayToMove);
    }

    public void crier() {
        System.out.println(this.shout);
    }

}
