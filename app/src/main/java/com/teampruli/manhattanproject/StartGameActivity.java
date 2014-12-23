package com.teampruli.manhattanproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.teampruli.manhattanproject.BaseClases.CardsManager;
import com.teampruli.manhattanproject.BaseClases.GameManager;


public class StartGameActivity extends Activity {
    private TextView textNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        this.textNumber = (TextView) findViewById(R.id.text_number_cards);
        this.textNumber.setText(String.valueOf(CardsManager.getInstance().getCardList().size()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_game, menu);
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
        GameManager.getInstance().startGame(CardsManager.getInstance().getCardList());
        Intent i = new Intent(this, NextTurnActivity.class);
        startActivity(i);
    }
}
