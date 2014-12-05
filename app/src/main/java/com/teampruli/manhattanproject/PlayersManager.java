package com.teampruli.manhattanproject;


import android.database.sqlite.SQLiteDatabase;

import com.teampruli.manhattanproject.utilities.DataBaseUtilities;

import java.util.ArrayList;
import java.util.List;

public class PlayersManager {
    private static PlayersManager ourInstance = new PlayersManager();
    private List<Player> playerList;
    private SQLiteDatabase db;

    public static PlayersManager getInstance() {
        return ourInstance;
    }

    private PlayersManager() {
        this.playerList = new ArrayList<Player>();
    }

    public void addPlayer(Player player) {
        this.playerList.add(player);
    }

    public void addPlayersList(ArrayList<Player> inPlayerList) {
        this.playerList.addAll(inPlayerList);
    }

    public List<Player> getPlayerList() {
        return playerList;
    }


    public void newPlayer(String playerName) {
        Player player = new Player(playerName, 0);
        player.setId(DataBaseUtilities.addPlayer(db, player));
        this.playerList.add(player);
    }

    public void readPlayersFromDataBase() {
        this.addPlayersList(DataBaseUtilities.readPlayersFromDataBase(db));
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
