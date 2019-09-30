package mahesh.kumar.phonepe.game;

import java.util.List;

public class Game {
    private List<Player> players;
    private int level;

    public Game(List<Player> players, int level) {
        this.players = players;
        this.level = level;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private void defineGameRule(){

    }

    private void startGame(){

    }
}
