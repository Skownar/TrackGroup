package Metier;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Leïla on 14-12-16.
 */

public class Invitation implements Parcelable {

    int id_expediteur;
    int id_destinataire;
    int id_groupe;


    public Invitation(){}

    public Invitation(int id_expediteur, int id_destinataire, int id_groupe) {
        this.id_expediteur = id_expediteur;
        this.id_destinataire = id_destinataire;
        this.id_groupe = id_groupe;
    }

    public Invitation(int id_expediteur, int id_destinataire) {
        this.id_expediteur = id_expediteur;
        this.id_destinataire = id_destinataire;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_expediteur);
        dest.writeInt(id_destinataire);
        dest.writeInt(id_groupe);
    }

    public static final Creator<Invitation> CREATOR = new Creator<Invitation>() {
        @Override
        public Invitation createFromParcel(Parcel in) {
            return new Invitation(in);
        }

        @Override
        public Invitation[] newArray(int size) {
            return new Invitation[size];
        }
    };
    public Invitation(Parcel in) {
        id_expediteur = in.readInt();
        id_destinataire = in.readInt();
        id_groupe = in.readInt();
    }
}
