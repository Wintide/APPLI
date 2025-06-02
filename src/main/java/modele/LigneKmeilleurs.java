package modele;

import java.util.ArrayList;

public class LigneKmeilleurs {
    private Integer distance;
    private ArrayList<String> chemin;

    /**
     * Constructeur de la classe LigneKmeilleurs qui correspond à chaque ligne de la TableView pour les K meilleurs solutions
     * @param distance
     * @param chemin
     */
    public LigneKmeilleurs(Integer distance, ArrayList<String> chemin) {
        this.distance = distance;
        this.chemin = chemin;
    }

    /**
     * Accesseur du champ distance
     * @return la distande totale du chemin
     */
    public int getDistance() { return distance; }

    /**
     * Accesseur du champ chemin
     * @return Le chemin pour effectuer tout les ventes/achats
     */
    public ArrayList<String> getChemin() { return chemin; }
}
