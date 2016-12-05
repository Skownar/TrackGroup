package Metier;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lafer on 15-11-16.
 */

public class Groupe implements Parcelable {
    private int id_groupe;
    private String nom;
    private int id_admin;

    public Groupe() {}

    public Groupe(int id_groupe, String nom_groupe, int id_admin) {
        this.id_groupe=id_groupe;
        this.nom=nom_groupe;
        this.id_admin=id_admin;
    }

    public Groupe(String nom_groupe,int id_admin) {
        this.id_admin = id_admin;
        this.nom = nom_groupe;
    }
    public Groupe(String nom_groupe){
        this.nom = nom_groupe;
    }

    public int getId_groupe() {
        return id_groupe;
    }

    public void setId_groupe(int id_groupe) {
        this.id_groupe = id_groupe;
    }

    public String getNom_groupe() {
        return nom;
    }

    public void setNom_groupe(String nom_groupe) {
        this.nom = nom_groupe;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    @Override
    public String toString() {
        return nom;/*"Groupe{" +
                "id_groupe=" + id_groupe +
                ", nom_groupe='" + nom + '\'' +
                ", id_admin=" + id_admin +
                '}';*/
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_groupe);
        dest.writeString(nom);
        dest.writeInt(id_admin);
    }
    public static final Parcelable.Creator<Groupe> CREATOR = new Parcelable.Creator<Groupe>() {
        @Override
        public Groupe createFromParcel(Parcel source) {
            return new Groupe(source);
        }
        @Override
        public Groupe[] newArray(int size) {
            return new Groupe[size];
        }
    };
    public Groupe(Parcel in) {
        id_groupe = in.readInt();
        nom = in.readString();
        id_admin = in.readInt();
    }
}

