package com.teampruli.manhattanproject;


import java.util.ArrayList;
import java.util.List;

public class PlayersManager {
    private static PlayersManager ourInstance = new PlayersManager();
    private List<Player> playerList;


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


}
