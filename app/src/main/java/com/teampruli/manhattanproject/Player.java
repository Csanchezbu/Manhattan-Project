package com.teampruli.manhattanproject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Carlos on 02/12/2014.
 */
public class Player implements Parcelable {
    private String name;
    private long id;

    public Player(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
    }

    public void readFromParcel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }
}
