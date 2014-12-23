package com.teampruli.manhattanproject.BaseClases;

import java.util.ArrayList;
import java.util.List;


public class Team {
    private String name;
    private int maxPlayers;
    private List<Player> playerList;
    private int indexPlayer;
    private int indexGuess;

    public Team(int maxPlayers, String name) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.playerList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String playersToString() {
        String aux = "";
        for (Player player : playerList) {
            aux = aux + player.getName() + " ";
        }
        return aux;
    }

    public boolean isFull() {
        return playerList.size() == this.maxPlayers;
    }

    public void addPlayer(Player player) {
        this.playerList.add(player);
    }

    /*   public void deletePlayer(Player player) {
           this.playerList.remove(player);
       }
   */
    public boolean isOK() {
        return (this.playerList.size() == this.maxPlayers) || (this.playerList.size() == this.maxPlayers - 1);
    }

    public Player currentPlayer() {
        return this.playerList.get(indexPlayer);
    }

    public Player currentGuess() {
        return this.playerList.get(indexGuess);
    }

    private void updateIndex() {
        this.indexGuess = (this.indexPlayer + 1) % this.playerList.size();
    }

    public void startIndex() {
        this.indexPlayer = 0;
        updateIndex();
    }

    public void nextPlayer() {
        this.indexPlayer = this.indexGuess;
        this.updateIndex();
    }
}
