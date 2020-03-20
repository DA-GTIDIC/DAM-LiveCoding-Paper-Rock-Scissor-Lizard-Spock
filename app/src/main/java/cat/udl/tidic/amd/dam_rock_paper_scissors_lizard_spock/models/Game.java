package cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock.models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

public class Game {


    private static final String TAG = Game.class.getSimpleName();
    private Player player;
    private Player ai;
    private Round round;

    public MutableLiveData<Round> summary = new MutableLiveData<>();


    public Game(Player p1, Player p2){
        this.player = p1 ;
        this.ai = p2 ;
        this.round = new Round();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getAi() {
        return ai;
    }

    public void setAi(Player ai) {
        this.ai = ai;
    }

    private int parseDecision(Options decision){

        switch(decision){
            case ROCK:
                return 0;
            case SPOCK:
                return 1;
            case PAPER:
                return 2;
            case LIZARD:
                return 3;
            case SCISSORS:
                return 4;
            default:
                return -1;
        }
    }



    public void playRound(){

        int ai_decision = this.parseDecision(this.ai.getDecision());
        int player_decision = this.parseDecision(this.player.getDecision());
        int diff = (ai_decision - player_decision + 5) % 5;

        if (ai_decision == player_decision) {
            Log.d(TAG,"AI and Player tie!");
            round.setWinner(player);
            round.setLoser(player);
            summary.setValue(round);


        } else if (diff < 3) {
            Log.d(TAG,"AI is the winner");
            round.setWinner(ai);
            round.setLoser(player);
            summary.setValue(round);
        } else {
            Log.d(TAG,"Player is the winner");
            round.setWinner(player);
            round.setLoser(ai);
            summary.setValue(round);
        }

    }

    public void reset(){
        this.player = null ;
        this.ai = null ;
    }

    public class Round {

        private Player winner;
        private Player loser;

        public Round() {
        }

        public Player getWinner() {
            return winner;
        }

        public void setWinner(Player winner) {
            this.winner = winner;
        }

        public Player getLoser() {
            return loser;
        }

        public void setLoser(Player loser) {
            this.loser = loser;
        }

        public String getSummary(){

            if (winner.getDecision().equals(Options.SCISSORS)
                    && loser.getDecision().equals(Options.PAPER)){
                return "CUT";
            }else if (winner.getDecision().equals(Options.PAPER)
                    && loser.getDecision().equals(Options.ROCK)){
                return "COVERS";
            }else if (winner.getDecision().equals(Options.ROCK)
                    && loser.getDecision().equals(Options.LIZARD)){
                return "CRUSHES";
            }else if (winner.getDecision().equals(Options.LIZARD)
                    && loser.getDecision().equals(Options.SPOCK)){
                return "POISONS";
            }else if (winner.getDecision().equals(Options.SPOCK)
                    && loser.getDecision().equals(Options.SCISSORS)){
                return "SMASHES";
            }else if (winner.getDecision().equals(Options.SCISSORS)
                    && loser.getDecision().equals(Options.LIZARD)){
                return "DECAPITATES";
            }else if (winner.getDecision().equals(Options.LIZARD)
                    && loser.getDecision().equals(Options.PAPER)){
                return "EATS";
            }else if (winner.getDecision().equals(Options.PAPER)
                    && loser.getDecision().equals(Options.SPOCK)){
                return "DISPROVES";
            }else if (winner.getDecision().equals(Options.SPOCK)
                    && loser.getDecision().equals(Options.ROCK)){
                return "VAPORIZES";
            }
            else if (winner.getDecision().equals(Options.ROCK)
                    && loser.getDecision().equals(Options.SCISSORS)){
                return "CRUSHES";
            }
            else {
                return "NOT DEFEAT :(";
            }
        }
    }
}
