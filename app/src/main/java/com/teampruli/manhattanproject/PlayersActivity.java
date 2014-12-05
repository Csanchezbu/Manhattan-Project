package com.teampruli.manhattanproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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


public class PlayersActivity extends ListActivity {
    public final int NEW_PLAYER_ACTIVITY = 001;
    private PlayersManager playersManager;
    List<Player> playerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        playersManager = PlayersManager.getInstance();
        this.playerList = playersManager.getPlayerList();
        draw();
    }

    private void draw() {
        ListAdapter listAdapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_2, android.R.id.text1, this.playerList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(playerList.get(position).getName());
                text2.setText(String.valueOf(playerList.get(position).getId()));
                return view;
            }
        };
        setListAdapter(listAdapter);
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

    public void clickAddPlayer(View v) {
        Intent i = new Intent(this, NewPlayerActivity.class);
        startActivityForResult(i, NEW_PLAYER_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Log.d("manhattan", "1");
            switch (requestCode) {
                case NEW_PLAYER_ACTIVITY:
                    Log.d("manhattan", "2");
                    String playerName = data.getStringExtra("name");
                    Toast.makeText(this, R.string.text_new_player_added, Toast.LENGTH_SHORT).show();
                    this.playersManager.newPlayer(playerName);
                    this.playerList = this.playersManager.getPlayerList();
                    draw();
                    break;
                default:
                    Log.d("manhattan", "3");
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
