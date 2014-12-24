package com.teampruli.manhattanproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.teampruli.manhattanproject.BaseClases.GameManager;
import com.teampruli.manhattanproject.BaseClases.Team;


public class NextTurnActivity extends Activity {
    private TextView textRound;
    private TextView textTeam;
    private TextView textPlayer;
    private TextView textGuess;
    private TextView textRules;
    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_turn);
        this.gameManager = GameManager.getInstance();
        textRound = (TextView) findViewById(R.id.text_round);
        textTeam = (TextView) findViewById(R.id.text_team);
        textPlayer = (TextView) findViewById(R.id.text_player);
        textGuess = (TextView) findViewById(R.id.text_player_guess);
        textRules = (TextView) findViewById(R.id.text_rules);
        Team currentTeam = gameManager.currentTeam();
        String rulesString = "", roundName = "";

        switch (this.gameManager.getRound()) {
            case 1:
                roundName = getString(R.string.round1);
                rulesString = getString(R.string.rule_round1);
                break;
            case 2:
                roundName = getString(R.string.rule_round2);
                break;
            case 3:
                roundName = getString(R.string.rule_round3);
                break;
            default:
                break;
        }
        textRound.setText(roundName);
        textRules.setText(rulesString);
        textTeam.setText(currentTeam.getName());
        textPlayer.setText(currentTeam.currentPlayer().getName());
        textGuess.setText(currentTeam.currentGuess().getName());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_next_turn, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickContinue(View view) {
        Intent i = new Intent(this, RoundActivity.class);
        startActivity(i);

    }
}
