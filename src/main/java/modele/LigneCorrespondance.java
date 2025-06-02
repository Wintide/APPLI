package modele;

public class LigneCorrespondance {
    private String vendeur;
    private String acheteur;
    private int distance;

    /**
     * Constructeur de la classe ligne correspondance pour créer chaque ligne du tableau (TableView)
     * @param vendeur
     * @param villeVendeur
     * @param acheteur
     * @param villeAcheteur
     * @param distance
     */
    public LigneCorrespondance(String vendeur, String villeVendeur, String acheteur, String villeAcheteur, int distance) {
        this.vendeur = vendeur + " (" + villeVendeur + ")";
        this.acheteur = acheteur + " (" + villeAcheteur + ")";
        this.distance = distance;
    }

    /**
     * Accesseur du champ vendeur
     * @return Le vendeur
     */
    public String getVendeur() { return vendeur; }

    /**
     * Accesseur du champ acheteur
     * @return L'acheteur
     */
    public String getAcheteur() { return acheteur; }

    /**
     * Accesseur du champ distance
     * @return La distance qui sépare le vendeur et l'acheteur
     */
    public int getDistance() { return distance; }
}
