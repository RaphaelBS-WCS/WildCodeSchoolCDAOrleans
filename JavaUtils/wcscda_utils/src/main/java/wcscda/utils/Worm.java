package wcscda.utils;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Worm{
    private String name;
    private Player player;
    private String noAccessAttribute;

    public String getName() {
        return name;
    }

    @JsonManagedReference
    public Player getPlayer() {
        return player;
    }

    public Worm(Player player, String name) {
        this.player = player;
        this.name = name;
        this.noAccessAttribute = "You should not pass";
        player.setWorm(this);
    }
}
