package modele;

public class Membre {
    private String nomDuMembre;
    private String villeDeResidence;

    /**
     * Constructeur de la classe Membre
     * @param nomDuMembre
     * @param villeDeResidence
     */
    public Membre(String nomDuMembre, String villeDeResidence) {
        this.nomDuMembre = nomDuMembre;
        this.villeDeResidence = villeDeResidence;
    }

    /**
     * Accesseur du champ nomDuMembre
     * @return le nom du membre
     */
    public String getnomDuMembre() {
        return nomDuMembre;
    }

    /**
     * Accesseur du champ villeDeResidence
     * @return la ville de residence du membre
     */
    public String getvilleDeResidence() {
        return villeDeResidence;
    }

    /**
     * Methode toString
     * @return le membre en chaine de caractere
     */
    public String toString() {
        return "Nom du membre : " + nomDuMembre + ", lieu de résience : " + villeDeResidence + "\n";
    }

    /**
     * Methode compareTo pour la classe Membre
     * @param membre
     * @return en entier (>0 si this suit membre, <0 si this precede membre et sinon 0)
     */
    public int compareTo(Membre membre) {
        if (this.nomDuMembre.compareTo(membre.nomDuMembre) == 0) {
            return this.villeDeResidence.compareTo(membre.villeDeResidence);
        }

        else return this.nomDuMembre.compareTo(membre.nomDuMembre);
    }




}
