package modele;

import java.util.TreeMap;

public class Ville {
    private String chNomVille;
    private TreeMap<String, Integer> chDistances; // plus simple et efficace

    public Ville(String chNomVille) {
        this.chNomVille = chNomVille;
        this.chDistances = new TreeMap<>();
    }

    public void ajout(String parNomVille, Integer parDistance){
        chDistances.put(parNomVille, parDistance); // mapping direct
    }

    public Integer getDistanceAvec(String autreVille) {
        return chDistances.getOrDefault(autreVille, Integer.MAX_VALUE); // ou null selon ton choix
    }

    public String getChNomVille() {
        return chNomVille;
    }

    public String toString(){
        return chNomVille + " : " + chDistances.toString();
    }

    public TreeMap<String, Integer> getChDistances() {
        return chDistances;
    }

}
