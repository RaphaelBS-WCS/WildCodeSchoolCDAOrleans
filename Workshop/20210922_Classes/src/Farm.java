public class Farm {

    private static Chat kitty = new Chat("Kitty");
    private static Cheval jollyJumper = new Cheval("JollyJumper");
    private static Chien  bobby = new Chien("Bobby");
    private static Dauphin flipper = new Dauphin("Flipper");
    private static Oiseaux charlie = new Oiseaux("Charlie");
    private static Poisson nemo = new Poisson("Nemo");
    private static Serpent kaa = new Serpent("Kaa");

    public static void main(String[] args) {
        Animal[] animals = {kitty, jollyJumper, bobby, flipper, charlie, nemo, kaa};

        for (Animal animal : animals) {
            System.out.println("Je m'appelle " + animal.getName());
            animal.move();
            animal.crier();
        }
    }
}
