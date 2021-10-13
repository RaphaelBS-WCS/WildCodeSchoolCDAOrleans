package wcscda.utils;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Player {

        public String getName() {
            return name;
        }

        private String name;

        @JsonBackReference
        public Worm getWorm() {
            return worm;
        }

        public void setWorm(Worm worm) {
            this.worm = worm;
        }

        private Worm worm;

        public Player(String name) {
            this.name = name;
        }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", worm=" + worm +
                '}';
    }
}
