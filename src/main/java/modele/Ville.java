package modele;

import java.util.TreeMap;

public class Ville {
    private String nomVille;
    private TreeMap<String, Integer> listeDesDistances; // plus simple et efficace

    public Ville(String nomVille) {
        this.nomVille = nomVille;
        this.listeDesDistances = new TreeMap<>();
    }

    public void ajout(String nomVille, Integer distance){
        listeDesDistances.put(nomVille, distance); // mapping direct
    }

    public Integer getDistanceAvec(String autreVille) {
        return listeDesDistances.getOrDefault(autreVille, Integer.MAX_VALUE); // ou null selon ton choix
    }

    public String getnomVille() {
        return nomVille;
    }

    public String toString(){
        return nomVille + " : " + listeDesDistances.toString();
    }

    public TreeMap<String, Integer> getlisteDesDistances() {
        return listeDesDistances;
    }

}
