package modele;

import java.util.ArrayList;
import java.util.TreeMap;

public class Ville {
    String chNomVille;
    ArrayList<TreeMap<String, Integer>> chDistances;

    public Ville(String chNomVille) {
        this.chNomVille = chNomVille;
        this.chDistances = new ArrayList<TreeMap<String,Integer>>();
    }

    public void ajout(String parNomVille,Integer parDistance){
        TreeMap<String, Integer> doublon = new TreeMap<>();
        doublon.put(parNomVille, parDistance);
        chDistances.add(doublon);
    }

    public String toString(){
        String retour = new String();
        retour += chNomVille + " : ";
        for (TreeMap<String, Integer> doublon : chDistances) {
            retour += doublon.toString();
        }
        return retour;
    }
}
