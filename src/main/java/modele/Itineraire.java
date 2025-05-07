package modele;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Itineraire {
    private Scenario scenario;
    private ListeVilles villes;
    private ListeMembres membres;

    public Itineraire(Scenario parScenario, ListeVilles parVilles, ListeMembres parMembres) {
        scenario = parScenario;
        villes = parVilles;
        membres = parMembres;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public ListeVilles getVilles() {
        return villes;
    }

    public ListeMembres getMembres() {
        return membres;
    }

    public ArrayList<String> trouveVillePassage() {
        ArrayList<String> liste = new ArrayList<>();


        for (Pair<String,String> carte : scenario.getScenarios()) {

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

        for (Pair<String,String> carte : scenario.getScenarios()) {

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
