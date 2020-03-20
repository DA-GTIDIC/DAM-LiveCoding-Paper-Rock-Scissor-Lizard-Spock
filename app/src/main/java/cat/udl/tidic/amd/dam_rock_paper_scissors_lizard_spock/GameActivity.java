package cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock.databinding.ActivityGameBinding;
import cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock.models.Game;
import cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock.models.Options;
import cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock.models.Player;
import cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock.viewmodels.GameViewModel;




public class GameActivity extends AppCompatActivity{

    private static final String GAME_BEGIN_DIALOG_TAG = "game_dialog_tag";
    private static final String GAME_END_DIALOG_TAG = "game_end_dialog_tag";

    private GameViewModel viewModel;
    private static final String TAG = "GameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        promptForPlayer();
    }



    public void onPlayersSet(String player) {
        initDataBinding(player);
    }

    private void initDataBinding(String player1) {
        ActivityGameBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        viewModel = new GameViewModel();
        viewModel.init(player1);
        activityGameBinding.setGameViewModel(viewModel);
        setUpOnGameEndListener();
    }

    private void setUpOnGameEndListener() {
        viewModel.getWinner().observe(this, this::onGameWinnerChanged);
    }

    private void onGameWinnerChanged(Game.Round round) {
        Player winner = round.getWinner();
        Player loser = round.getLoser();
        String message = round.getSummary();

        GameEndDialog dialog = GameEndDialog.newInstance(this,
                winner.getName(), message, winner.getDecision(),loser.getDecision());

        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), GAME_END_DIALOG_TAG);

    }



    public void promptForPlayer() {
        GameBeginDialog dialog = GameBeginDialog.newInstance(this);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), GAME_BEGIN_DIALOG_TAG);
    }
}
