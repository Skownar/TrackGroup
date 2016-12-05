package Metier;

import android.os.Parcel;
import android.os.Parcelable;

import Metier.Groupe;

/**
 * Created by stephanie on 01-12-16.
 */

    public class Activite implements Parcelable {
        private int idactivite;
        private String titre;
        private String description;
        private String dateact;
        private int idgroupe;

    public Activite(int idactivite, String titre, String description, String dateact, int idgroupe) {
        this.idactivite = idactivite;
        this.titre = titre;
        this.description = description;
        this.dateact = dateact;
        this.idgroupe = idgroupe;
    }

    public int getIdactivite() {
        return idactivite;
    }

    public void setIdactivite(int idactivite) {
        this.idactivite = idactivite;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateact() {
        return dateact;
    }

    public void setDateact(String dateact) {
        this.dateact = dateact;
    }

    public int getIdgroupe() {
        return idgroupe;
    }

    public void setIdgroupe(int idgroupe) {
        this.idgroupe = idgroupe;
    }

    @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(idactivite);
            dest.writeString(titre);
            dest.writeString(description);
            dest.writeString(dateact);
            dest.writeInt(idgroupe);
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




}
