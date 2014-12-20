package com.teampruli.manhattanproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.teampruli.manhattanproject.BaseClases.GameManager;
import com.teampruli.manhattanproject.BaseClases.Team;

import java.util.List;


public class TeamsActivity extends Activity {

    private GameManager gameManager;
    private ListView teamsView;
    private NumberPicker pickerPlayers;
    private NumberPicker pickerTeams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        getNecessaryViews();
        gameManager = GameManager.getInstance();
        gameManager.setPlayersList(getIntent().getParcelableArrayExtra("players"));
        teamsView.setEmptyView(findViewById(R.id.empty_list));
        pickerPlayers.setMinValue(gameManager.getMinPlayersTeam());
        pickerPlayers.setMaxValue(gameManager.getMaxPlayersTeam());
        pickerTeams.setMinValue(gameManager.getMinTeams());
        pickerTeams.setMaxValue(gameManager.getMaxTeams());

        pickerPlayers.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                switch (scrollState) {
                    case NumberPicker.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                    case NumberPicker.OnScrollListener.SCROLL_STATE_IDLE:
                        pickerTeams.setValue(gameManager.getNumberOfTeams(pickerPlayers.getValue()));
                        break;
                    default:
                        break;
                }
            }
        });

        pickerTeams.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                switch (scrollState) {
                    case NumberPicker.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                    case NumberPicker.OnScrollListener.SCROLL_STATE_IDLE:
                        pickerPlayers.setValue(gameManager.getNumberOfPlayers(pickerTeams.getValue()));
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void getNecessaryViews() {
        teamsView = (ListView) findViewById(R.id.teamsView);
        pickerPlayers = (NumberPicker) findViewById(R.id.pickerPlayers);
        pickerTeams = (NumberPicker) findViewById(R.id.pickerTeams);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teams, menu);
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

    public void clickNewTeams(View view) {
        gameManager.createRandomTeams(pickerTeams.getValue());
        List<Team> teamList = gameManager.getTeamList();
        draw(teamList);
    }

    public void clickContinue(View view) {

    }

    private void draw(final List<Team> teamList) {
        ListAdapter listAdapter = new ArrayAdapter<Team>(this, android.R.layout.simple_list_item_2, android.R.id.text1, teamList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(teamList.get(position).getName());
                text2.setText(teamList.get(position).playersToString());
                return view;
            }
        };
        teamsView.setAdapter(listAdapter);
    }
}
