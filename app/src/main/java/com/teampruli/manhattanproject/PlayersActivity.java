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

import com.teampruli.manhattanproject.BaseClases.Player;
import com.teampruli.manhattanproject.BaseClases.PlayersManager;

import java.util.List;


public class PlayersActivity extends ListActivity {
    public final int NEW_PLAYER_ACTIVITY = 1;
    public final int VIEW_PLAYER_ACTIVITY = 2;
    private PlayersManager playersManager;
    List<Player> playerList;
    Player selectedPlayer;
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
        this.selectedPlayer = playerList.get(position);
        Intent i = new Intent(this, ViewPlayer.class);
        i.putExtra("player", this.selectedPlayer);
        startActivityForResult(i, VIEW_PLAYER_ACTIVITY);
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

        switch (requestCode) {
            case NEW_PLAYER_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String playerName = data.getStringExtra("name");
                    Toast.makeText(this, R.string.text_new_player_added, Toast.LENGTH_SHORT).show();
                    this.playersManager.newPlayer(playerName);
                    this.playerList = this.playersManager.getPlayerList();
                    draw();
                }
                break;
            case VIEW_PLAYER_ACTIVITY:

                if (resultCode == ViewPlayer.RESULT_DELETE) {
                    playersManager.deletePlayer(this.selectedPlayer);
                    draw();
                }
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
