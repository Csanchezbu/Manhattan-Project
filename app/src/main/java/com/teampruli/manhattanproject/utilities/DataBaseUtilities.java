package com.teampruli.manhattanproject.utilities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.teampruli.manhattanproject.DataBaseOpenHelper;
import com.teampruli.manhattanproject.Player;

import java.util.ArrayList;

/**
 * Created by Carlos on 04/12/2014.
 */
public class DataBaseUtilities {

    public static ArrayList<Player> readPlayersFromDataBase(SQLiteDatabase db) {
        ArrayList<Player> playersList = new ArrayList<Player>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DataBaseOpenHelper.TABLE_PLAYERS, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            playersList.add(cursorToPlayer(cursor));
        }
        cursor.close();
        return playersList;
    }

    private static Player cursorToPlayer(Cursor cursor) {
        return new Player(cursor.getString(cursor.getColumnIndex(DataBaseOpenHelper.PLAYERS_COLUMN_NAME)), cursor.getInt(cursor.getColumnIndex(DataBaseOpenHelper.PLAYERS_COLUMN_ID)));
    }
}
