package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ListeVilles {
    private ArrayList<Ville> listeDesVilles;

    /**
     * Constructeur automatique de la classe ListeVilles contenant le talbeau croisé de la liste des villes et distances
     * @throws FileNotFoundException
     */
    public ListeVilles() throws FileNotFoundException {
        listeDesVilles = new ArrayList<>();
        String regex = "[,\\.\\s]";
        File doc2 = new File("fichier/distances.txt");
        Scanner obj2 = new Scanner(doc2);
        ArrayList<String> list = new ArrayList<String>();
        while (obj2.hasNextLine()) {
            String line = obj2.nextLine();
            String[] split = line.split(regex);
            list.add(split[0]);
        }
        Scanner obj3 = new Scanner(doc2);
        while (obj3.hasNextLine()) {

            String[] myArray = obj3.nextLine().split(regex);
            Ville ville = new Ville(myArray[0]);

            for (int i = 1; i < myArray.length; i++) {
                int dist = Integer.parseInt(myArray[i]);
                ville.ajout(list.get(i - 1),dist);
            }
            listeDesVilles.add(ville);

        }
    }

    /**
     * Accesseur du champ listeDesVilles
     * @return la liste des villes
     */
    public ArrayList<Ville> getlisteDesVilles() {
        return listeDesVilles;
    }

    /**
     * Methode toString
     * @return String : chaine de caratere de la liste des villes
     */
    @Override
    public String toString() {
        String res = "";
        for (Ville ville : listeDesVilles) {
            res += ville.toString() + "\n";
        }
        return res;
    }

    /**
     * Permet de recuperer la ville et toutes ces correspondance grace à son nom
     * @param nom
     * @return Ville : la corresponde nom(String) à ville(Ville)
     */
    public Ville getVilleParNom(String nom) {
        for (Ville ville : listeDesVilles) {
            if (ville.getnomVille().equals(nom)) {
                return ville;
            }
        }
        return null;
    }

    /**
     * Calcule la distance entre 2 villes
     * @param villeA
     * @param villeB
     * @return int : distance entre 2 villes
     */
    public int getDistance(String villeA, String villeB) {
        for (Ville ville : listeDesVilles) {
            if (ville.getnomVille().equals(villeA)) {
                Integer dist = ville.getlisteDesDistances().get(villeB);
                if (dist != null) return dist;
            }
        }
        return Integer.MAX_VALUE;
    }


}
