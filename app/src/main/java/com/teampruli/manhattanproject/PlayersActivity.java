package com.teampruli.manhattanproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;


public class PlayersActivity extends ListActivity {

    private PlayersManager playersManager;
    List<Player> playerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("manhattan", "1");
        setContentView(R.layout.activity_players);
        Log.d("manhattan", "2");
        playersManager = PlayersManager.getInstance();
        Log.d("manhattan", "3");
        this.playerList = playersManager.getPlayerList();
        Log.d("manhattan", "4");

        ListAdapter listAdapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_2, this.playerList);
        setListAdapter(listAdapter);
        Log.d("manhattan", "5");
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(this, ViewPlayer.class);
        i.putExtra("idPlayer", playerList.get(position));
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_players, menu);
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
}
