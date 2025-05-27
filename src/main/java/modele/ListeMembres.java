package modele;

import java.util.ArrayList;
import java.util.TreeSet;

public class ListeMembres {
    private ArrayList<Membre> chMembres;
    public ListeMembres() {
        chMembres = new ArrayList<Membre>();
    }

    public void addMembre(Membre m) {
        chMembres.add(m);
    }

    public String toString() {
        String retour = new String();
        for (Membre m : chMembres) {
            retour += m.toString();
        }
        return retour;
    }

    public ArrayList<Membre> getChMembres() {
        return chMembres;
    }

    public Membre trouverParNom(String nom) {
        for (Membre m : chMembres) {
            if (m.getChNom().equalsIgnoreCase(nom)) {
                return m;
            }
        }
        return null;
    }

    public String getVilleParNom(String nomMembre) {
        for (Membre membre : chMembres) {
            if (membre.getChNom().equals(nomMembre)) {
                return membre.getChVille();
            }
        }
        return null; // ou "Inconnue"
    }

}
