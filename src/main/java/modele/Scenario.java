package modele;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Scenario {
    private int id;
    private ArrayList<Pair<String,String>> scenarios;
    private ListeVilles villes;
    private ListeMembres membres;

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

        // Étape 1 : extraire les sommets
        HashSet<String> sommets = new HashSet<>();
        for (Pair<String, String> arc : contraintes) {
            sommets.add(arc.getKey());
            sommets.add(arc.getValue());
        }

        // Étape 2 : construire le graphe
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

        // Étape 3 : tri topologique (Kahn)
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
                continue; // ou break si bloquant
            }

            total += distance;
        }

        return total;
    }

    public ArrayList<String> calculItinerairePlusCours() {
        ArrayList<Pair<String, String>> contraintes = this.associationMembresVilles();

        // Sommets, graphe et degrés
        HashSet<String> sommets = new HashSet<>();
        HashMap<String, ArrayList<String>> graphe = new HashMap<>();
        HashMap<String, Integer> degreEntree = new HashMap<>();

        for (Pair<String, String> arc : contraintes) {
            sommets.add(arc.getKey());
            sommets.add(arc.getValue());
            graphe.computeIfAbsent(arc.getKey(), k -> new ArrayList<>()).add(arc.getValue());
            degreEntree.put(arc.getValue(), degreEntree.getOrDefault(arc.getValue(), 0) + 1);
            degreEntree.putIfAbsent(arc.getKey(), 0);
        }

        ArrayList<String> ordreValide = new ArrayList<>();
        ArrayList<String> queue = new ArrayList<>();

        for (String s : sommets) {
            if (degreEntree.getOrDefault(s, 0) == 0) queue.add(s);
        }

        String villePrecedente = "Vélizy";
        ordreValide.add("Vélizy+");

        while (!queue.isEmpty()) {
            // Trouver le sommet dans queue le plus proche de villePrecedente
            String meilleurSommet = null;
            int distanceMin = Integer.MAX_VALUE;

            for (String candidat : queue) {
                String villeCandidat = extraireNomVille(candidat);
                Ville v = villes.getVilleParNom(villePrecedente);
                if (v != null) {
                    int dist = v.getDistanceAvec(villeCandidat);
                    if (dist < distanceMin) {
                        distanceMin = dist;
                        meilleurSommet = candidat;
                    }
                }
            }

            // Retirer meilleur sommet et l'ajouter à l'ordre
            queue.remove(meilleurSommet);
            ordreValide.add(meilleurSommet);

            // Mise à jour ville précédente
            villePrecedente = extraireNomVille(meilleurSommet);


            // Décrémenter degré des voisins
            for (String voisin : graphe.getOrDefault(meilleurSommet, new ArrayList<>())) {
                degreEntree.put(voisin, degreEntree.get(voisin) - 1);
                if (degreEntree.get(voisin) == 0) {
                    queue.add(voisin);
                }
            }
        }

        ordreValide.add("Vélizy-");

        return ordreValide;
    }

    private String extraireNomVille(String sommet) {
        if (sommet.endsWith("+") || sommet.endsWith("-")) {
            return sommet.substring(0, sommet.length() - 1);
        }
        return sommet;
    }






}
