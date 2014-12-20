package com.teampruli.manhattanproject.utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseOpenHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "DataBase.db";
    public static int DATABASE_CURRENT_VERSION = 1;

    public static String TABLE_PLAYERS = "players";
    public static String PLAYERS_COLUMN_ID = "id";
    public static String PLAYERS_COLUMN_NAME = "name";

    public static String TABLE_CARDS = "cards";
    public static String CARDS_COLUMN_ID = "id";
    public static String CARDS_COLUMN_TITLE = "title";
    public static String CARDS_COLUMN_DESCRIPTION = "description";

    private static String CREATE_TABLE_PLAYERS = "create table " + TABLE_PLAYERS + " ( " + PLAYERS_COLUMN_ID + " integer primary key autoincrement," +
            PLAYERS_COLUMN_NAME + " varchar(50) not null );";

    private static String CREATE_TABLE_CARDS = "create table " + TABLE_CARDS + "(" + CARDS_COLUMN_ID + " integer primary key autoincrement," +
            CARDS_COLUMN_TITLE + " varchar(300) not null," +
            CARDS_COLUMN_DESCRIPTION + " varchar(1000));";

    public DataBaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PLAYERS);
        db.execSQL(CREATE_TABLE_CARDS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);

        onCreate(db);
    }
}
