package cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import cat.udl.tidic.amd.dam_rock_paper_scissors_lizard_spock.models.Options;

public class GameEndDialog extends DialogFragment {

    private View rootView;
    private GameActivity activity;
    private String winnerName;
    private String message;
    private Options loserDecision;
    private Options winnerDecision;
    private static final String TIE = "TIE";


    public static GameEndDialog newInstance(GameActivity activity, String winnerName,
                                            String message,
                                            Options winnerOption, Options loserOption) {
        GameEndDialog dialog = new GameEndDialog();
        dialog.activity = activity;
        dialog.winnerName = winnerName;
        dialog.winnerDecision = winnerOption;
        dialog.message = message;
        dialog.loserDecision = loserOption;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setCancelable(false)
                .setPositiveButton(R.string.end, ((dialog, which) -> onNewGame()))
                .setNegativeButton(R.string.again, ((dialog, which) -> onNewRound()))
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        return alertDialog;
    }

    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.game_end_dialog, null, false);

        if (winnerDecision.equals(loserDecision)){
            ((TextView) rootView.findViewById(R.id.tv_winner)).setText(TIE);
        }else{
            ((TextView) rootView.findViewById(R.id.tv_winner)).setText(winnerName);
        }

        ((ImageView) rootView.findViewById(R.id.winner))
                .setImageResource(getResourceId(winnerDecision));

        ((ImageView) rootView.findViewById(R.id.looser))
                .setImageResource(getResourceId(loserDecision));

        ((TextView) rootView.findViewById(R.id.message)).setText(message);


    }

    private int getResourceId(Options o){
        switch (o){
            case ROCK:
                return R.mipmap.rock;
            case PAPER:
                return R.mipmap.paper;
            case SCISSORS:
                return R.mipmap.scissors;
            case LIZARD:
                return R.mipmap.lizard;
            case SPOCK:
                return R.mipmap.spock;
            default:
                return -1;
        }
    }

    private void onNewGame() {
        dismiss();
        activity.promptForPlayer();
    }
    private void onNewRound() {
        dismiss();
    }
}
