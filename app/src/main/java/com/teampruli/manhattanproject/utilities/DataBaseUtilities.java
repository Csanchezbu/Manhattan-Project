package com.teampruli.manhattanproject.utilities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.teampruli.manhattanproject.Card;
import com.teampruli.manhattanproject.DataBaseOpenHelper;
import com.teampruli.manhattanproject.Player;

import java.util.ArrayList;

public class DataBaseUtilities {

    public static ArrayList<Player> readPlayersFromDataBase(SQLiteDatabase db) {
        ArrayList<Player> playersList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DataBaseOpenHelper.TABLE_PLAYERS, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            playersList.add(cursorToPlayer(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return playersList;
    }

    private static Player cursorToPlayer(Cursor cursor) {
        return new Player(cursor.getString(cursor.getColumnIndex(DataBaseOpenHelper.PLAYERS_COLUMN_NAME)), cursor.getInt(cursor.getColumnIndex(DataBaseOpenHelper.PLAYERS_COLUMN_ID)));
    }

    public static long addPlayer(SQLiteDatabase db, Player player) {
        ContentValues values = new ContentValues();
        values.put(DataBaseOpenHelper.PLAYERS_COLUMN_NAME, player.getName());
        return db.insert(DataBaseOpenHelper.TABLE_PLAYERS, null, values);

    }

    public static void deletePlayer(SQLiteDatabase db, Player selectedPlayer) {
        db.delete(DataBaseOpenHelper.TABLE_PLAYERS, DataBaseOpenHelper.PLAYERS_COLUMN_ID + " = '" + selectedPlayer.getId() + "'", null);

    }

    public static long addCard(SQLiteDatabase db, Card card) {
        ContentValues values = new ContentValues();
        values.put(DataBaseOpenHelper.CARDS_COLUMN_TITLE, card.getTitle());
        values.put(DataBaseOpenHelper.CARDS_COLUMN_DESCRIPTION, card.getDescription());
        return db.insert(DataBaseOpenHelper.TABLE_CARDS, null, values);
    }

    public static ArrayList<Card> readCardsFromDataBase(SQLiteDatabase db) {
        ArrayList<Card> cardsList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DataBaseOpenHelper.TABLE_CARDS, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            cardsList.add(cursorToCard(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return cardsList;
    }

    private static Card cursorToCard(Cursor cursor) {
        return new Card(cursor.getString(cursor.getColumnIndex(DataBaseOpenHelper.CARDS_COLUMN_TITLE)), cursor.getString(cursor.getColumnIndex(DataBaseOpenHelper.CARDS_COLUMN_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(DataBaseOpenHelper.CARDS_COLUMN_ID)));
    }

    public static void deleteCard(SQLiteDatabase db, Card selectedCard) {
        db.delete(DataBaseOpenHelper.TABLE_CARDS, DataBaseOpenHelper.CARDS_COLUMN_ID + " = '" + selectedCard.getId() + "'", null);
    }
}
