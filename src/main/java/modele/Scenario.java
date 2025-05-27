package modele;

import javafx.util.Pair;

import java.util.*;

public class Scenario {
    private int id;
    private ArrayList<Pair<String,String>> scenarios;
    private ListeVilles villes;
    private ListeMembres membres;
    private int nbTotalChemins; // compteur global

    public int getNbTotalChemins() {
        return nbTotalChemins;
    }

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

    public ArrayList<String> calculItineraire() {
        ArrayList<String> resultat = new ArrayList<>();
        ArrayList<Pair<String,String>> contraintes = this.associationMembresVilles();
        resultat.add("Vélizy+");

        HashSet<String> sommets = new HashSet<>();
        for (Pair<String, String> arc : contraintes) {
            sommets.add(arc.getKey());
            sommets.add(arc.getValue());
        }

        HashMap<String, ArrayList<String>> graphe = new HashMap<>();
        HashMap<String, Integer> degreEntree = new HashMap<>();

        for (String s : sommets) {
            graphe.put(s, new ArrayList<>());
            degreEntree.put(s, 0);
        }

        for (Pair<String, String> arc : contraintes) {
            graphe.get(arc.getKey()).add(arc.getValue());
            degreEntree.put(arc.getValue(), degreEntree.get(arc.getValue()) + 1);
        }

        ArrayList<String> queue = new ArrayList<>();
        for (String s : sommets) {
            if (degreEntree.get(s) == 0) {
                queue.add(s);
            }
        }

        while (!queue.isEmpty()) {
            String courant = queue.remove(0);
            resultat.add(courant);

            for (String voisin : graphe.get(courant)) {
                degreEntree.put(voisin, degreEntree.get(voisin) - 1);
                if (degreEntree.get(voisin) == 0) {
                    queue.add(voisin);
                }
            }
        }

        resultat.add("Vélizy-");

        return resultat;
    }

    public int calculDistanceTotale(ArrayList<String> itineraire) {
        int total = 0;

        for (int i = 0; i < itineraire.size() - 1; i++) {
            String villeA = extraireNomVille(itineraire.get(i));
            String villeB = extraireNomVille(itineraire.get(i + 1));

            Ville vDepart = villes.getVilleParNom(villeA);
            int distance = vDepart.getDistanceAvec(villeB);

            if (distance == Integer.MAX_VALUE) {
                System.out.println("Pas de liaison entre " + villeA + " et " + villeB);
                continue;
            }

            total += distance;
        }

        return total;
    }

    private String extraireNomVille(String sommet) {
        if (sommet.endsWith("+") || sommet.endsWith("-")) {
            return sommet.substring(0, sommet.length() - 1);
        }
        return sommet;
    }

    public ArrayList<ArrayList<String>> calculKMeilleursItineraires(int k) {
        ArrayList<ArrayList<String>> resultats = new ArrayList<>();
        nbTotalChemins = 0; // reset du compteur

        ArrayList<Pair<String, String>> contraintes = this.associationMembresVilles();

        // 1. Sommets et graphe
        Set<String> sommets = new HashSet<>();
        Map<String, List<String>> graphe = new HashMap<>();
        Map<String, Integer> degreEntree = new HashMap<>();

        for (Pair<String, String> arc : contraintes) {
            sommets.add(arc.getKey());
            sommets.add(arc.getValue());
        }

        for (String s : sommets) {
            graphe.put(s, new ArrayList<>());
            degreEntree.put(s, 0);
        }

        for (Pair<String, String> arc : contraintes) {
            graphe.get(arc.getKey()).add(arc.getValue());
            degreEntree.put(arc.getValue(), degreEntree.get(arc.getValue()) + 1);
        }

        // 2. PriorityQueue pour garder les k meilleurs (ordre croissant de distance)
        PriorityQueue<ArrayList<String>> meilleurs = new PriorityQueue<>(
                k, Comparator.comparingInt(this::calculerDistanceTotale).reversed()
        );

        // 3. Noeuds sans prédécesseur (in-degree == 0)
        List<String> candidats = new ArrayList<>();
        for (String s : sommets) {
            if (degreEntree.get(s) == 0) {
                candidats.add(s);
            }
        }

        backtrack(new ArrayList<>(), candidats, graphe, new HashMap<>(degreEntree), meilleurs, k);

        // 4. Convertir PriorityQueue en liste triée
        ArrayList<ArrayList<String>> trie = new ArrayList<>(meilleurs);
        trie.sort(Comparator.comparingInt(this::calculerDistanceTotale));
        System.out.println(trie);

        // Filtrage par distances différentes
        ArrayList<ArrayList<String>> resultatsFinal = new ArrayList<>();
        Set<Integer> distancesUnicites = new HashSet<>();

        for (ArrayList<String> itineraire : trie) {
            int distance = calculerDistanceTotale(itineraire);
            if (!distancesUnicites.contains(distance)) {
                resultatsFinal.add(itineraire);
                distancesUnicites.add(distance);
            }
            if (resultatsFinal.size() >= k) break;
        }


        return resultatsFinal;

    }

    private void backtrack(ArrayList<String> chemin, List<String> candidats,
                           Map<String, List<String>> graphe, Map<String, Integer> degreEntree,
                           PriorityQueue<ArrayList<String>> meilleurs, int k) {
        if (chemin.size() == degreEntree.size()) {
            nbTotalChemins++;

            ArrayList<String> complet = new ArrayList<>();
            complet.add("Vélizy+");
            complet.addAll(chemin);
            complet.add("Vélizy-");

            if (meilleurs.size() < k) {
                meilleurs.add(new ArrayList<>(complet));
            } else {
                int distNouveau = calculerDistanceTotale(complet);
                int distMax = calculerDistanceTotale(meilleurs.peek());
                if (distNouveau < distMax) {
                    meilleurs.poll();
                    meilleurs.add(new ArrayList<>(complet));
                }
            }
            return;
        }

        for (int i = 0; i < candidats.size(); i++) {
            String courant = candidats.get(i);
            chemin.add(courant);

            List<String> nouveauxCandidats = new ArrayList<>(candidats);
            nouveauxCandidats.remove(i);

            for (String voisin : graphe.get(courant)) {
                degreEntree.put(voisin, degreEntree.get(voisin) - 1);
                if (degreEntree.get(voisin) == 0) {
                    nouveauxCandidats.add(voisin);
                }
            }

            backtrack(chemin, nouveauxCandidats, graphe, new HashMap<>(degreEntree), meilleurs, k);

            chemin.remove(chemin.size() - 1);

            for (String voisin : graphe.get(courant)) {
                degreEntree.put(voisin, degreEntree.get(voisin) + 1);
            }
        }
    }


    public int calculerDistanceTotale(ArrayList<String> itineraire) {
        int total = 0;

        for (int i = 0; i < itineraire.size() - 1; i++) {
            String villeA = extraireNomVille(itineraire.get(i));
            String villeB = extraireNomVille(itineraire.get(i + 1));

            Ville villeSource = villes.getVilleParNom(villeA);
            if (villeSource != null && villeSource.getChDistances().containsKey(villeB)) {
                total += villeSource.getChDistances().get(villeB);
            } else {
                total += Integer.MAX_VALUE / 2; // pénalité si pas de chemin connu
            }
        }

        return total;
    }

}
