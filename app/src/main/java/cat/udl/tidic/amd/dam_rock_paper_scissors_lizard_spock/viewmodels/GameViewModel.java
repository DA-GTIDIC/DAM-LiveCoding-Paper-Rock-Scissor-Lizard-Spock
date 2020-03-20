package cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock.models.Game;
import cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock.models.Options;
import cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock.models.Player;

public class GameViewModel extends ViewModel {

    private Game game;

    public LiveData<Game.Round> getWinner(){
        return game.summary;
    }

    public void init(String player){

        Player p = new Player();
        p.setName(player);
        Player ia = new Player();
        ia.setName("AI");
        game = new Game(p,ia);
    }

    public void onClickedAt(Options decision){
        game.getAi().setDecision( Options.getRandomOption());
        game.getPlayer().setDecision(decision);
        game.playRound();
    }


}
