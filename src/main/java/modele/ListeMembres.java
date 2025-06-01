package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class ListeMembres {
    private ArrayList<Membre> listeDesMembres;
    public ListeMembres() throws FileNotFoundException {
        listeDesMembres = new ArrayList<Membre>();
        File doc = new File("fichier/membres_APPLI.txt");
        Scanner obj = new Scanner(doc);
        String regex = "[,\\.\\s]";

        while (obj.hasNextLine()) {

            String[] myArray = obj.nextLine().split(regex);
            Membre membre = new Membre(myArray[0], myArray[1]);
            listeDesMembres.add(membre);

        }

    }

    public void addMembre(Membre membre) {
        listeDesMembres.add(membre);
    }

    public String toString() {
        String retour = new String();
        for (Membre membre : listeDesMembres) {
            retour += membre.toString();
        }
        return retour;
    }

    public ArrayList<Membre> getlisteDesMembres() {
        return listeDesMembres;
    }

    public Membre getMembreParNom(String nom) {
        for (Membre membre : listeDesMembres) {
            if (membre.getnomDuMembre().equalsIgnoreCase(nom)) {
                return membre;
            }
        }
        return null;
    }

    public String getVilleParNom(String nomMembre) {
        for (Membre membre : listeDesMembres) {
            if (membre.getnomDuMembre().equals(nomMembre)) {
                return membre.getvilleDeResidence();
            }
        }
        return null;
    }

}
