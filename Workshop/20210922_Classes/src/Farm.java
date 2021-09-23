public class Farm {

    private static Chat kitty = new Chat("Kitty");
    private static Cheval jollyJumper = new Cheval("JollyJumper");
    private static Chien  bobby = new Chien("Bobby");
    private static Dauphin flipper = new Dauphin("Flipper");
    private static Oiseaux charlie = new Oiseaux("Charlie");
    private static Poisson nemo = new Poisson("Nemo");
    private static Serpent kaa = new Serpent("Kaa");

    public static void main(String[] args) {
        System.out.println("Je m'appel " + kitty.getName());
        kitty.move();
        kitty.crier();
        System.out.println("Je m'appel " + jollyJumper.getName());
        jollyJumper.move();
        jollyJumper.crier();
        System.out.println("Je m'appel " + bobby.getName());
        bobby.move();
        bobby.crier();
        System.out.println("Je m'appel " + flipper.getName());
        flipper.move();
        flipper.crier();
        System.out.println("Je m'appel " + charlie.getName());
        charlie.move();
        charlie.crier();
        charlie.lay();
        System.out.println("Je m'appel " + nemo.getName());
        nemo.move();
        nemo.crier();
        nemo.lay();
        System.out.println("Je m'appel " + kaa.getName());
        kaa.move();
        kaa.crier();
        kaa.lay();


        System.out.println();
        System.out.println(kitty.getName() + " a " + kitty.getPaws() + " pattes.");
    }
}
