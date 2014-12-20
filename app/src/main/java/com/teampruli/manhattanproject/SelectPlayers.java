package com.teampruli.manhattanproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.teampruli.manhattanproject.BaseClases.Player;
import com.teampruli.manhattanproject.BaseClases.PlayersManager;

import java.util.ArrayList;
import java.util.List;


public class SelectPlayers extends ListActivity {

    private static final int NEW_PLAYER_ACTIVITY = 1;
    private List<String> names;
    private ListView list;
    private PlayersManager playersManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playersManager = PlayersManager.getInstance();
        setContentView(R.layout.activity_select_players);
        this.names = new ArrayList<>();
        for (Player player : playersManager.getPlayerList()) {
            this.names.add(player.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, this.names);

        list = this.getListView();
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_game, menu);
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    public void clickContinue(View view) {
        if (list.getCheckedItemCount() < 4) {
            Toast.makeText(this, R.string.text_min_players, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, TeamsActivity.class);
            Player[] players = new Player[this.list.getCheckedItemCount()];
            SparseBooleanArray selected = this.list.getCheckedItemPositions();
            int j = 0;
            for (int i = 0; i < this.list.getCount(); i++) {
                if (selected.get(i)) {
                    players[j] = playersManager.getPlayerList().get(i);
                    j++;
                }
            }
            intent.putExtra("players", players);
            startActivity(intent);
        }
    }

    public void clickAddPlayer(View view) {
        Intent intent = new Intent(this, NewPlayerActivity.class);
        startActivityForResult(intent, NEW_PLAYER_ACTIVITY);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case NEW_PLAYER_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String playerName = data.getStringExtra("name");
                    Toast.makeText(this, R.string.text_new_player_added, Toast.LENGTH_SHORT).show();
                    PlayersManager.getInstance().newPlayer(playerName);
                    this.names.add(playerName);
                    draw();
                }
                break;

            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void draw() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, this.names);
        list = this.getListView();
        list.setAdapter(adapter);
    }
}

