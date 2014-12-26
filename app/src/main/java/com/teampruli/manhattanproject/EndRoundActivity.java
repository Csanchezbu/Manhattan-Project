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
import android.widget.TextView;

import com.teampruli.manhattanproject.BaseClases.GameManager;
import com.teampruli.manhattanproject.BaseClases.Team;

import java.util.List;


public class EndRoundActivity extends ListActivity {

    private List<Team> teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_round);
        this.teamList = GameManager.getInstance().getTeamList();
        ListAdapter listAdapter = new ArrayAdapter<Team>(this, android.R.layout.simple_list_item_2, android.R.id.text1, teamList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(teamList.get(position).getName());
                text2.setText("resultado: " + teamList.get(position).getCorrectAnswers());
                return view;
            }
        };
        setListAdapter(listAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_end_round, menu);
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
        if (GameManager.getInstance().getRound() == GameManager.END_GAME) {
            // i = new Intent(this,);
        } else {
            Intent i;
            i = new Intent(this, StartRoundActivity.class);
            startActivity(i);
        }
    }
}
