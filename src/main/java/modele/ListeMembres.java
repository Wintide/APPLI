package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class ListeMembres {
    private ArrayList<Membre> chMembres;
    public ListeMembres() throws FileNotFoundException {
        chMembres = new ArrayList<Membre>();
        File doc = new File("fichier/membres_APPLI.txt");
        Scanner obj = new Scanner(doc);
        String regex = "[,\\.\\s]";

        while (obj.hasNextLine()) {

            String[] myArray = obj.nextLine().split(regex);
            Membre membre = new Membre(myArray[0], myArray[1]);
            chMembres.add(membre);

        }

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
