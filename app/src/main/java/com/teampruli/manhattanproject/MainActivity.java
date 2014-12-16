package com.teampruli.manhattanproject;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlayersManager playersManager = PlayersManager.getInstance();
        CardsManager cardsManager = CardsManager.getInstance();
        DataBaseOpenHelper openHelper = new DataBaseOpenHelper(this, DataBaseOpenHelper.DATABASE_NAME, null, DataBaseOpenHelper.DATABASE_CURRENT_VERSION);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        playersManager.setDb(db);
        playersManager.readPlayersFromDataBase();
        cardsManager.setDb(db);
        cardsManager.readCardsFromDataBase();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void clickNew(View view) {
        Intent i = new Intent(this, SelectPlayers.class);
        startActivity(i);
    }

    public void clickLoad(View v) {
        Intent i = new Intent(this, SavedGames.class);
        startActivity(i);
    }

    public void clickPlayers(View v) {
        Intent i = new Intent(this, PlayersActivity.class);
        startActivity(i);
    }

    public void clickCards(View v) {
        Intent i = new Intent(this, CardsActivity.class);
        startActivity(i);
    }
}
