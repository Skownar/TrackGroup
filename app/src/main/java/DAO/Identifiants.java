package DAO;

/**
 * Created by lafer on 20-11-16.
 */
// // TODO: 20-11-16 cryptage du mot de passe, passage en clair provisoire
public class Identifiants {
    private String pseudo;
    private String mdp;

    public Identifiants() {
    }

    public Identifiants(String pseudo, String mdp) {
        this.pseudo = pseudo;
        this.mdp = mdp;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
