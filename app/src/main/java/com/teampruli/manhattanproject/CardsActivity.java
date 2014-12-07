package com.teampruli.manhattanproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class CardsActivity extends ListActivity {

    private static final int NEW_CARD_ACTIVITY = 001;
    public final int VIEW_CARD_ACTIVITY = 002;
    private CardsManager cardsManager;
    List<Card> cardList;
    Card selectedCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cards, menu);
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

    public void clickAddPlayer(View v) {
        Intent i = new Intent(this, NewPlayerActivity.class);
        startActivityForResult(i, NEW_CARD_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case NEW_CARD_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String cardName = data.getStringExtra("name");
                    Toast.makeText(this, R.string.text_new_player_added, Toast.LENGTH_SHORT).show();
                    this.cardsManager.newCard(cardName);
                    this.cardList = this.cardsManager.getCardList();
                    draw();
                }
                break;
            case VIEW_CARD_ACTIVITY:

                if (resultCode == ViewPlayer.RESULT_DELETE) {
                    cardsManager.deletePlayer(this.selectedCard);
                    draw();
                }
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void draw() {
        ListAdapter listAdapter = new ArrayAdapter<Card>(this, android.R.layout.simple_list_item_2, android.R.id.text1, this.cardList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(cardList.get(position).getTitle());
                text2.setText(String.valueOf(cardList.get(position).getId()));
                return view;
            }
        };
        setListAdapter(listAdapter);
    }

    public void clickAddCard(View view) {
        Intent i = new Intent(this, NewCardActivity.class);
        startActivityForResult(i, NEW_CARD_ACTIVITY);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        this.selectedCard = cardList.get(position);
        Intent i = new Intent(this, ViewPlayer.class);
        i.putExtra("card", this.selectedCard);
        startActivityForResult(i, VIEW_CARD_ACTIVITY);
    }
}
