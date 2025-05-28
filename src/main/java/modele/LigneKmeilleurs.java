package modele;

import java.util.ArrayList;

public class LigneKmeilleurs {
    private Integer distance;
    private ArrayList<String> chemin;

    public LigneKmeilleurs(Integer distance, ArrayList<String> chemin) {
        this.distance = distance;
        this.chemin = chemin;
    }

    public int getDistance() { return distance; }
    public ArrayList<String> getChemin() { return chemin; }
}
