import com.sun.jdi.event.StepEvent;
public abstract class Animal {

    protected String name;

    protected String shout;

    protected String wayToMove;

    public String getName() {
        return name;
    }

    public void move(){
        System.out.println("Je  " + this.wayToMove);
    }

    public void crier() {
        System.out.println(this.shout);
    }

    public Animal() {};
}
