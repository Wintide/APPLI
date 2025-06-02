package modele;

import java.util.TreeMap;

public class Ville {
    private String nomVille;
    private TreeMap<String, Integer> listeDesDistances; // plus simple et efficace

    /**
     * Constructeur de la classe Ville
     * @param nomVille
     */
    public Ville(String nomVille) {
        this.nomVille = nomVille;
        this.listeDesDistances = new TreeMap<>();
    }

    /**
     * Ajoute une ville à la correspondance de this
     * @param nomVille
     * @param distance
     */
    public void ajout(String nomVille, Integer distance){
        listeDesDistances.put(nomVille, distance); // mapping direct
    }

    /**
     * Accesseur du champ nomVilles
     * @return
     */
    public String getnomVille() {
        return nomVille;
    }

    /**
     * methode toString
     * @return la ville en chaine de caractere
     */
    public String toString(){
        return nomVille + " : " + listeDesDistances.toString();
    }

    /**
     * Accesseur du champ listeDesDistances
     * @return
     */
    public TreeMap<String, Integer> getlisteDesDistances() {
        return listeDesDistances;
    }

}
