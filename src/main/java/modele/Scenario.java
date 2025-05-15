package modele;

import javafx.util.Pair;
import java.util.ArrayList;

public class Scenario {
    private int id;
    private ArrayList<Pair<String,String>> scenarios;
    private ListeVilles villes;
    private ListeMembres membres;

    public Scenario(int parId, ListeVilles parVilles, ListeMembres parMembres) {
        scenarios = new ArrayList<>();
        id = parId;
        villes = parVilles;
        membres = parMembres;
    }

    public int getId() {
        return id;
    }

    public void ajout(String parSource, String parDestination) {
        scenarios.add(new Pair<>(parSource, parDestination));
    }

    public String toString() {
        return "Scenario [id=" + id + ", scenarios=" + scenarios + "]";
    }

    public ArrayList<Pair<String,String>> getScenarios() {
        return scenarios;
    }

    public ListeVilles getVilles() {
        return villes;
    }

    public ListeMembres getMembres() {
        return membres;
    }

    public ArrayList<String> trouveVillePassage() {
        ArrayList<String> liste = new ArrayList<>();


        for (Pair<String,String> carte : this.getScenarios()) {

            for (Membre membre : membres.getChMembres()) {
                if ((membre.getChNom().compareTo(carte.getKey()) == 0) && (!liste.contains(membre.getChVille()))) {
                    liste.add(membre.getChVille());
                }
            }

            for (Membre membre : membres.getChMembres()) {
                if ((membre.getChNom().compareTo(carte.getValue()) == 0) && (!liste.contains(membre.getChVille()))) {
                    liste.add(membre.getChVille());
                }
            }


        }


        return liste;
    }

    public ArrayList<Pair<String,String>> associationMembresVilles() {
        ArrayList<Pair<String,String>> liste = new ArrayList<>();
        String villeDepart = new String();
        String villeArrive = new String();

        for (Pair<String,String> carte : this.getScenarios()) {

            for (Membre membre : membres.getChMembres()) {
                if (membre.getChNom().compareTo(carte.getKey()) == 0) {
                    villeDepart = membre.getChVille();
                }
            }

            for (Membre membre : membres.getChMembres()) {
                if (membre.getChNom().compareTo(carte.getValue()) == 0) {
                    villeArrive = membre.getChVille();
                }
            }

            liste.add(new Pair<>(villeDepart+"+", villeArrive+"-"));
        }


        return liste;
    }
}
