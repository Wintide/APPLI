package modele;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class Graphe {

    private TreeMap<Integer, Set<Integer>> graphe;
    private Integer ordre;

    public Graphe(int[][] table) {
        graphe = new TreeMap<>();
        for (int i = 0; i < table.length; i++) {
            Set<Integer> set = new TreeSet<Integer>();
            for (int v : table[i]) {
                set.add(v);
            }
            graphe.put(i, set);
        }
        ordre = graphe.size();
    }

    public String toString() {
        String str = "";
        str += "Ordre du graphe : " + ordre + "\n";
        str += "Taille : " + this.taille() + "\n";
        str += "Degrée minimal : " + this.getDegreMin() + "\n";
        str += "Degrée maximal : " + this.getDegreMax() + "\n";
        for (Integer i : graphe.keySet()) {
            str += "Le sommet " + i + " a pour voisins: " + graphe.get(i) + "\n";
        }
        return str;
    }

    public Set<Integer> getSommets() {
        return graphe.keySet();
    }

    public String getOrdre() {
        return "Ordre du graphe : " + ordre;
    }

    public Integer getDegreMin() {
        Integer min = Integer.MAX_VALUE;
        for (Set<Integer> set : graphe.values()) {
            if (set.size() < min) {
                min = set.size();
            }
        }
        return min;
    }

    public Integer getDegreMax() {
        Integer max = -1;
        for (Set<Integer> set : graphe.values()) {
            if (set.size() > max) {
                max = set.size();
            }
        }
        return max;
    }

    public Integer taille() {
        Integer taille = 0;
        for (Set<Integer> set : graphe.values()) {
            taille += set.size();
        }
        return (taille/2);
    }

    public TreeMap<Integer, Pair<Integer, Integer>> parcoursEnLargeur(int sommetDepart) {
        TreeMap<Integer, Pair<Integer, Integer>> resultat = new TreeMap<>();
        ArrayList<Integer> file = new ArrayList<>();

        resultat.put(sommetDepart, new Pair<>(null, 0)); // racine : pas de parent, distance 0
        file.add(sommetDepart);

        while (!file.isEmpty()) {
            int courant = file.remove(0);
            int distanceCourante = resultat.get(courant).getValue();

            for (Integer voisin : graphe.get(courant)) {
                if (!resultat.containsKey(voisin)) {
                    resultat.put(voisin, new Pair<>(courant, distanceCourante + 1));
                    file.add(voisin);
                }
            }
        }

        return resultat;
    }




}
