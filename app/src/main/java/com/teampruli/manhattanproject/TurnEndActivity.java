package com.teampruli.manhattanproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.teampruli.manhattanproject.BaseClases.Card;
import com.teampruli.manhattanproject.BaseClases.CardState;
import com.teampruli.manhattanproject.BaseClases.GameManager;

import java.util.ArrayList;
import java.util.List;


public class TurnEndActivity extends ListActivity {

    private GameManager gameManager;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameManager = GameManager.getInstance();
        setContentView(R.layout.activity_turn_end);
        List<String> titles = new ArrayList<>();
        List<Card> cardList = gameManager.getTurnList();
        for (Card card : cardList) {
            if (card.getCardState() != CardState.NONE)
                titles.add(card.getTitle());
        }
        Log.d("manhattan", "" + cardList.size());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, titles);

        list = this.getListView();
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        for (int i = 0; i < cardList.size(); i++) {
            Card card = cardList.get(i);
            if (card.getCardState() == CardState.CORRECT) {
                list.setItemChecked(i, true);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_turn_end, menu);
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
        List<Card> correctCards = new ArrayList<>(this.list.getCheckedItemCount());
        SparseBooleanArray correct = this.list.getCheckedItemPositions();

        for (int i = 0; i < this.list.getCount(); i++) {
            if (correct.get(i)) {
                correctCards.add(this.gameManager.getTurnList().get(i));
            }
        }
        Log.d("manhattan", "" + correctCards.size());
        this.gameManager.correctAnswers(correctCards);
        Intent i;

        if (this.gameManager.isRoundFinished()) {
            this.gameManager.endRound();
            this.gameManager.nextTeam();
            i = new Intent(this, EndRoundActivity.class);
        } else {
            this.gameManager.nextTeam();
            i = new Intent(this, NextTurnActivity.class);
        }
        startActivity(i);
    }
}
