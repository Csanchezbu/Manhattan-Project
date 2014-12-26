package com.teampruli.manhattanproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.teampruli.manhattanproject.BaseClases.GameManager;


public class StartRoundActivity extends Activity {

    private GameManager gameManager;
    private TextView textRound;
    private TextView textRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_round);
        this.gameManager = GameManager.getInstance();
        this.textRound = (TextView) findViewById(R.id.text_round);
        this.textRules = (TextView) findViewById(R.id.text_rules);

        String rulesString = "", roundName = "";

        switch (this.gameManager.getRound()) {
            case GameManager.ROUND_1:
                roundName = getString(R.string.round1);
                rulesString = getString(R.string.rule_round1);
                break;
            case GameManager.ROUND_2:
                roundName = getString(R.string.round2);
                rulesString = getString(R.string.rule_round2);
                break;
            case GameManager.ROUND_3:
                roundName = getString(R.string.round3);
                rulesString = getString(R.string.rule_round3);
                break;
            default:
                break;
        }
        textRound.setText(roundName);
        textRules.setText(rulesString);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_round, menu);
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

    public void startClick(View view) {
        gameManager.startRound();
        Intent i = new Intent(this, NextTurnActivity.class);
        startActivity(i);
    }
}
