package modele;

public class Membre {
    private String nomDuMembre;
    private String villeDeResidence;

    public Membre(String nomDuMembre, String villeDeResidence) {
        this.nomDuMembre = nomDuMembre;
        this.villeDeResidence = villeDeResidence;
    }

    public String getnomDuMembre() {
        return nomDuMembre;
    }

    public String getvilleDeResidence() {
        return villeDeResidence;
    }

    public String toString() {
        return "Nom du membre : " + nomDuMembre + ", lieu de résience : " + villeDeResidence + "\n";
    }

    public int compareTo(Membre membre) {
        if (this.nomDuMembre.compareTo(membre.nomDuMembre) == 0) {
            return this.villeDeResidence.compareTo(membre.villeDeResidence);
        }

        else return this.nomDuMembre.compareTo(membre.nomDuMembre);
    }




}
