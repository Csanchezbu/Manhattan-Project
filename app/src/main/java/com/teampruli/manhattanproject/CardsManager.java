package com.teampruli.manhattanproject;

import android.database.sqlite.SQLiteDatabase;

import com.teampruli.manhattanproject.utilities.DataBaseUtilities;

import java.util.ArrayList;
import java.util.List;

public class CardsManager {
    private static CardsManager ourInstance = new CardsManager();
    private List<Card> cardList;
    private SQLiteDatabase db;

    public static CardsManager getInstance() {
        return ourInstance;
    }

    private CardsManager() {
        this.cardList = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cardList.add(card);
    }

    public void addCardsList(ArrayList<Card> inCardList) {
        this.cardList.addAll(inCardList);
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void newCard(String title, String description) {
        Card card = new Card(title, description, 0);
        card.setId(DataBaseUtilities.addCard(db, card));
        this.addCard(card);
    }

    public void readCardsFromDataBase() {
        this.addCardsList(DataBaseUtilities.readCardsFromDataBase(db));
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public void deletePlayer(Card selectedCard) {
        DataBaseUtilities.deleteCard(db, selectedCard);
        this.cardList.remove(selectedCard);
    }

}
