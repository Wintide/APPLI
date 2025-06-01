package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ListeVilles {
    private ArrayList<Ville> chVilles;

    public ListeVilles() throws FileNotFoundException {
        chVilles = new ArrayList<>();
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
            chVilles.add(ville);

        }
    }

    public ArrayList<Ville> getChVilles() {
        return chVilles;
    }

    public void ajoutVilles(Ville ville) {
        chVilles.add(ville);
    }

    @Override
    public String toString() {
        String res = "";
        for (Ville ville : chVilles) {
            res += ville.toString() + "\n";
        }
        return res;
    }

    public Ville getVilleParNom(String nom) {
        for (Ville ville : chVilles) {
            if (ville.getChNomVille().equals(nom)) {
                return ville;
            }
        }
        return null;
    }

    public int getDistance(String villeA, String villeB) {
        for (Ville ville : chVilles) {
            if (ville.getChNomVille().equals(villeA)) {
                Integer dist = ville.getChDistances().get(villeB);
                if (dist != null) return dist;
            }
        }
        return Integer.MAX_VALUE;
    }


}
