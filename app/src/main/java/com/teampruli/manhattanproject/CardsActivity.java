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

import com.teampruli.manhattanproject.BaseClases.Card;
import com.teampruli.manhattanproject.BaseClases.CardsManager;

import java.util.List;


public class CardsActivity extends ListActivity {

    public static final int NEW_CARD_ACTIVITY = 1;
    public static final int VIEW_CARD_ACTIVITY = 2;
    private CardsManager cardsManager;
    List<Card> cardList;
    Card selectedCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        cardsManager = CardsManager.getInstance();
        this.cardList = cardsManager.getCardList();
        draw();
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
                    String cardName = data.getStringExtra("title");
                    String cardDesc = data.getStringExtra("description");
                    this.cardsManager.newCard(cardName, cardDesc);
                    this.cardList = this.cardsManager.getCardList();
                    Toast.makeText(this, R.string.text_new_card_added, Toast.LENGTH_SHORT).show();
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
        Intent i = new Intent(this, ViewCard.class);
        i.putExtra("card", this.selectedCard);
        startActivityForResult(i, VIEW_CARD_ACTIVITY);
    }
}
