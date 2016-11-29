package com.example.leila.androidproject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Leïla on 28-11-16.
 */

public class Contient implements Parcelable {
    private int id_groupe;
    private int id_membre;

    public Contient(int id_groupe, int id_membre) {
        this.id_groupe = id_groupe;
        this.id_membre = id_membre;
    }
    public Contient(int id_membre) {
        this.id_membre = id_membre;
    }

    public int getId_groupe() {
        return id_groupe;
    }

    public void setId_groupe(int id_groupe) {
        this.id_groupe = id_groupe;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    @Override
    public String toString() {
        return "Contient{" +
                "id_groupe=" + id_groupe +
                ", id_membre=" + id_membre +
                '}';
    }

    protected Contient(Parcel in) {
        id_groupe = in.readInt();
        id_membre = in.readInt();
    }

    public static final Creator<Contient> CREATOR = new Creator<Contient>() {
        @Override
        public Contient createFromParcel(Parcel in) {
            return new Contient(in);
        }

        @Override
        public Contient[] newArray(int size) {
            return new Contient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_groupe);
        dest.writeInt(id_membre);
    }
}
