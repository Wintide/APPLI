package modele;

public class Membre {
    String chNom;
    String chVille;

    public Membre(String chNom, String chVille) {
        this.chNom = chNom;
        this.chVille = chVille;
    }

    public String getChNom() {
        return chNom;
    }

    public String getChVille() {
        return chVille;
    }

    public String toString() {
        return "Nom du membre : " + chNom + ", lieu de résience : " + chVille + "\n";
    }

    public int compareTo(Membre parMembre) {
        if (this.chNom.compareTo(parMembre.chNom) == 0) {
            return this.chVille.compareTo(parMembre.chVille);
        }

        else return this.chNom.compareTo(parMembre.chNom);
    }


}
