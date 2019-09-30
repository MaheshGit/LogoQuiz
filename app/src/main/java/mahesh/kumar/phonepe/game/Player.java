package mahesh.kumar.phonepe.game;

public class Player {
    private String name;
    private String id;
    private int score;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
