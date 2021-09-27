import com.sun.jdi.event.StepEvent;
public abstract class Animal {

    private String name;

    private String shout;

    private String wayToMove;

    public Animal(String name, String shout, String wayToMove) {
        this.name = name;
        this.shout = shout;
        this.wayToMove = wayToMove;
    }    
    
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
