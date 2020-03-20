package cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock.models;

public class Player {

    private String name;
    private Options decision;
    private int victories;

    public Player() {

    }

    public Player(String name, Options decision) {
        this.name = name;
        this.decision = decision;
        this.victories = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Options getDecision() {
        return decision;
    }

    public void setDecision(Options decision) {
        this.decision = decision;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }
}
