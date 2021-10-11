package wcscda.quest;

public class Sentinel implements Comparable<Sentinel> {
    private static Integer nextId = 0;

    private Integer id;
    private String identificationCode;

    public Sentinel() {
        this.id = ++nextId;
    }

    public int compareTo(Sentinel sentinel) {
        return id.compareTo(sentinel.id);
    }
}
