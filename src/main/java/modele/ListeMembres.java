package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class ListeMembres {
    private ArrayList<Membre> listeDesMembres;

    /**
     * Constructeur automatique de la classe ListeMembres contenant la liste de touts les membres
     * @throws FileNotFoundException
     */
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

    /**
     * Methode toString pour retourner la liste des membres en chaine de caracteres
     * @return String
     */
    public String toString() {
        String retour = new String();
        for (Membre membre : listeDesMembres) {
            retour += membre.toString();
        }
        return retour;
    }

    /**
     * Accesseur du champ listeDesMembres
     * @return la liste des membres
     */
    public ArrayList<Membre> getlisteDesMembres() {
        return listeDesMembres;
    }

    /**
     * Permet de récuperer la ville de résidence d'un membre spécifique
     * @param nomMembre
     * @return String : ville de residence du membre
     */
    public String getVilleParNom(String nomMembre) {
        for (Membre membre : listeDesMembres) {
            if (membre.getnomDuMembre().equals(nomMembre)) {
                return membre.getvilleDeResidence();
            }
        }
        return null;
    }

}
