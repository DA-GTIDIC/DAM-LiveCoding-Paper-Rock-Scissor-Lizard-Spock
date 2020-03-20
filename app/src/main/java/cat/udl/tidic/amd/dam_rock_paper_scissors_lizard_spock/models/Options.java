package cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock.models;

import java.util.Random;

public enum Options {
    ROCK,
    SPOCK,
    PAPER,
    LIZARD,
    SCISSORS;

    public static Options getRandomOption() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}


