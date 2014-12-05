package com.teampruli.manhattanproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ViewPlayer extends Activity {

    public static int RESULT_DELETE = 2;
    TextView txtName;
    Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_player);
        txtName = (TextView) findViewById(R.id.txtName);
        this.player = getIntent().getParcelableExtra("player");
        txtName.setText(player.getName());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_player, menu);
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

    public void deletePlayer(View v) {
        setResult(RESULT_DELETE);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("delete", -1);
        setResult(RESULT_CANCELED, i);
        super.onBackPressed();
    }
}
