package modele;

public class LigneCorrespondance {
    private String vendeur;
    private String acheteur;
    private int distance;

    public LigneCorrespondance(String vendeur, String villeVendeur, String acheteur, String villeAcheteur, int distance) {
        this.vendeur = vendeur + " (" + villeVendeur + ")";
        this.acheteur = acheteur + " (" + villeAcheteur + ")";
        this.distance = distance;
    }

    public String getVendeur() { return vendeur; }
    public String getAcheteur() { return acheteur; }
    public int getDistance() { return distance; }
}
