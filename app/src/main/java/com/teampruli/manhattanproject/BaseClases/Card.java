package com.teampruli.manhattanproject.BaseClases;

import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable {

    private String title;
    private String description;
    private long id;
    private CardState cardState;


    public Card(String title, String description, long id) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.cardState = CardState.NONE;
    }

    public Card(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.getId());
        dest.writeString(this.getTitle());
        dest.writeString(this.getDescription());
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public static final Parcelable.Creator<Card> CREATOR
            = new Parcelable.Creator<Card>() {

        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public CardState getCardState() {
        return cardState;
    }

    public void setCardState(CardState cardState) {
        this.cardState = cardState;
    }
}
