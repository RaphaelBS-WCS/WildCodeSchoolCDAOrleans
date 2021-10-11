package wcscda.quest.G_debug_challenge;

public class App {
    private static App instance = new App();

    private Integer id;

    public App() {
        if(id < 0) {
            System.err.println("Id should be positive");
        }
        System.out.println("app " + id + " has been created.");
    }

    public static void main(String[] args) {
        System.out.println("Hello world !");
    }
}
