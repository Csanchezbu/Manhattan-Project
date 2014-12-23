package com.teampruli.manhattanproject.BaseClases;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GameManager {

    private static final int MIN_PLAYERS_TEAM = 2;
    private static final int MIN_TEAMS = 2;
    private static GameManager ourInstance = new GameManager();
    private List<Team> teamList;
    private Player[] playersList;
    private Random randomGenerator;
    private List<Card> cardList;
    private int round;
    private int indexTeam;


    public static GameManager getInstance() {
        return ourInstance;
    }

    private GameManager() {
        teamList = new ArrayList<>();
        cardList = new ArrayList<>();
        randomGenerator = new Random();
    }

    public void createRandomTeams(int teamsNumber) {
        do {
            this.createTeams(teamsNumber);
            for (Player player : playersList) {
                Team team;
                do {
                    team = this.getRandomTeam();
                } while (team.isFull());
                team.addPlayer(player);
            }
        } while (!this.teamsOK());
    }


    private boolean teamsOK() {
        if (teamList.isEmpty()) {
            return false;
        }
        for (Team team : teamList) {
            if (!team.isOK())
                return false;
        }
        return true;
    }

    private void createTeams(int teamsNumber) {
        int maxPlayers = (this.playersList.length % teamsNumber != 0) ? this.playersList.length / teamsNumber + 1 : this.playersList.length / teamsNumber;
        this.teamList.clear();
        for (int i = 0; i < teamsNumber; i++) {
            this.teamList.add(new Team(maxPlayers, "Equipo " + (i + 1)));
        }
    }

    private Team getRandomTeam() {
        return teamList.get(randomGenerator.nextInt(this.teamList.size()));
    }

    public List<Team> getTeamList() {
        return teamList;
    }


    public int getMaxPlayersTeam() {
        return playersList.length / 2 + playersList.length % 2;
    }

    public int getMaxTeams() {
        return playersList.length / 2;
    }

    public void setPlayersList(Parcelable[] players) {
        Player[] playersList = new Player[players.length];
        int i = 0;
        for (Parcelable player : players) {
            Player auxPlayer = (Player) player;
            playersList[i] = auxPlayer;
            i++;
        }

        this.playersList = playersList;
    }

    public int getNumberOfTeams(int players) {
        return this.playersList.length / players;
    }

    public int getNumberOfPlayers(int teams) {
        return this.playersList.length / teams;
    }

    public int getMinTeams() {
        return MIN_TEAMS;
    }

    public int getMinPlayersTeam() {
        return MIN_PLAYERS_TEAM;
    }

    public void startGame(List<Card> cardList) {
        Collections.shuffle(cardList);
        this.cardList = cardList;
        Collections.shuffle(this.teamList);
        this.round = 1;
        this.indexTeam = 0;
        for (int i = 0; i < this.teamList.size(); i++) {
            this.teamList.get(i).startIndex();
        }

    }


    public int getRound() {
        return round;
    }

    public Team currentTeam() {

        return this.teamList.get(indexTeam);
    }

    public void nextTeam() {
        this.indexTeam = (this.indexTeam + 1) % this.teamList.size();
    }
}
