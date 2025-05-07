package modele;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class GrapheOriente {

    private TreeMap<Integer, Set<Integer>> graphe;
    private Integer ordre;

    public GrapheOriente(int[][] table) {
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

    public TreeMap<Integer, Integer> calculDegresEntrants() {
        TreeMap<Integer, Integer> degresEntrants = new TreeMap<>();

        // Initialiser tous les degrés à 0
        for (Integer sommet : graphe.keySet()) {
            degresEntrants.put(sommet, 0);
        }

        // Incrémenter le degré entrant pour chaque arc
        for (Set<Integer> voisins : graphe.values()) {
            for (Integer voisin : voisins) {
                degresEntrants.put(voisin, degresEntrants.getOrDefault(voisin, 0) + 1);
            }
        }

        return degresEntrants;
    }

    public Set<Integer> getSources(TreeMap<Integer, Integer> degresEntrants) {
        Set<Integer> sources = new TreeSet<>();
        for (Integer sommet : degresEntrants.keySet()) {
            if (degresEntrants.get(sommet) == 0) {
                sources.add(sommet);
            }
        }
        return sources;
    }

    public ArrayList<Integer> triTopologique() {
        TreeMap<Integer, Integer> degresEntrants = calculDegresEntrants();
        ArrayList<Integer> resultat = new ArrayList<>();
        Set<Integer> sources = getSources(degresEntrants);

        while (!sources.isEmpty()) {
            Integer source = sources.iterator().next(); // récupérer un élément
            sources.remove(source);
            resultat.add(source);

            for (Integer voisin : graphe.get(source)) {
                int nouveauDegre = degresEntrants.get(voisin) - 1;
                degresEntrants.put(voisin, nouveauDegre);
                if (nouveauDegre == 0) {
                    sources.add(voisin);
                }
            }
        }

        if (resultat.size() != graphe.size()) {
            throw new IllegalStateException("Le graphe contient un cycle, tri topologique impossible.");
        }

        return resultat;
    }





}
