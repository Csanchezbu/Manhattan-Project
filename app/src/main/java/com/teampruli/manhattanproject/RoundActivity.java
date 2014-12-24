package com.teampruli.manhattanproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.teampruli.manhattanproject.BaseClases.Card;
import com.teampruli.manhattanproject.BaseClases.GameManager;


public class RoundActivity extends Activity {
    private TextView textCron;
    private TextView textTitle;
    private TextView textDescription;
    private GameManager gameManager;
    private Card cardPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        this.gameManager = GameManager.getInstance();
        textCron = (TextView) findViewById(R.id.text_cron);
        textDescription = (TextView) findViewById(R.id.text_description);
        textTitle = (TextView) findViewById(R.id.text_title);

        cardPlaying = this.gameManager.getCard();

        draw();
        CountDownTimer cron = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textCron.setText(String.valueOf(millisUntilFinished / 1000));
                if (millisUntilFinished <= 5000) {
                    textCron.setTextColor(Color.RED);
                } else {
                    textCron.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onFinish() {
                textCron.setText(String.valueOf(0));
                new AlertDialog.Builder(RoundActivity.this)
                        .setTitle(R.string.textEnd)
                        .setMessage(R.string.textEndMessage)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(RoundActivity.this, TurnEndActivity.class);
                                startActivity(i);
                            }
                        })
                        .show();
            }
        }.start();
    }

    private void draw() {
        this.textTitle.setText(this.cardPlaying.getTitle());
        this.textDescription.setText(this.cardPlaying.getDescription());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_round, menu);
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

    public void clickPass(View view) {
        this.gameManager.nextCard();
        this.cardPlaying = this.gameManager.getCard();
        draw();
    }

    public void clickCorrect(View view) {
        this.gameManager.correctCard();
        this.cardPlaying = this.gameManager.getCard();
        draw();
    }
}
