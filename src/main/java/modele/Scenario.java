package modele;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
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

    public Scenario(int parId) throws FileNotFoundException {
        File doc = new File("fichier/membres_APPLI.txt");
        Scanner obj = new Scanner(doc);
        String regex = "[,\\.\\s]";
        ListeMembres liste = new ListeMembres();

        while (obj.hasNextLine()) {

            String[] myArray = obj.nextLine().split(regex);
            Membre membre = new Membre(myArray[0], myArray[1]);
            liste.addMembre(membre);

        }

        File doc2 = new File("fichier/distances.txt");
        Scanner obj2 = new Scanner(doc2);
        ArrayList<String> list = new ArrayList<String>();
        while (obj2.hasNextLine()) {
            String line = obj2.nextLine();
            String[] split = line.split(regex);
            list.add(split[0]);
        }
        ListeVilles listeVilles = new ListeVilles();
        Scanner obj3 = new Scanner(doc2);
        while (obj3.hasNextLine()) {

            String[] myArray = obj3.nextLine().split(regex);
            Ville ville = new Ville(myArray[0]);

            for (int i = 1; i < myArray.length; i++) {
                int dist = Integer.parseInt(myArray[i]);
                ville.ajout(list.get(i - 1),dist);
            }
            listeVilles.ajoutVilles(ville);

        }

        scenarios = new ArrayList<>();
        id = parId;
        villes = listeVilles;
        membres = liste;

        remplissageScenario();
    }

    public Scenario(String parScenario) throws FileNotFoundException {
        File doc = new File("fichier/membres_APPLI.txt");
        Scanner obj = new Scanner(doc);
        String regex = "[,\\.\\s]";
        ListeMembres liste = new ListeMembres();

        while (obj.hasNextLine()) {

            String[] myArray = obj.nextLine().split(regex);
            Membre membre = new Membre(myArray[0], myArray[1]);
            liste.addMembre(membre);

        }

        File doc2 = new File("fichier/distances.txt");
        Scanner obj2 = new Scanner(doc2);
        ArrayList<String> list = new ArrayList<String>();
        while (obj2.hasNextLine()) {
            String line = obj2.nextLine();
            String[] split = line.split(regex);
            list.add(split[0]);
        }
        ListeVilles listeVilles = new ListeVilles();
        Scanner obj3 = new Scanner(doc2);
        while (obj3.hasNextLine()) {

            String[] myArray = obj3.nextLine().split(regex);
            Ville ville = new Ville(myArray[0]);

            for (int i = 1; i < myArray.length; i++) {
                int dist = Integer.parseInt(myArray[i]);
                ville.ajout(list.get(i - 1),dist);
            }
            listeVilles.ajoutVilles(ville);

        }
        String idStr = parScenario.replaceAll("[^0-9]", "");
        int Nid = Integer.parseInt(idStr);

        scenarios = new ArrayList<>();
        id = Nid;
        villes = listeVilles;
        membres = liste;

        remplissageScenario();
    }

    public void remplissageScenario() throws FileNotFoundException {
        String regex = "[,\\.\\s]";
        File doc = new File("scenario/scenario_"+id+".txt");
        Scanner scan = new Scanner(doc);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] split = line.split(regex);
            this.ajout(split[0], split[2]);

        }
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

    private String extraireNomVille(String sommet) {
        if (sommet.endsWith("+") || sommet.endsWith("-")) {
            return sommet.substring(0, sommet.length() - 1);
        }
        return sommet;
    }

    public ArrayList<ArrayList<String>> calculKMeilleursItineraires(int k) {
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

        Set<Integer> distancesVues = new HashSet<>();

        backtrack(new ArrayList<>(), candidats, graphe, new HashMap<>(degreEntree), meilleurs, k, distancesVues);


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
                           PriorityQueue<ArrayList<String>> meilleurs, int k, Set<Integer> distancesVues) {

        if (chemin.size() == degreEntree.size()) {
            nbTotalChemins++;
            if (nbTotalChemins%1000000 == 0) {
                System.out.println(nbTotalChemins);
            }

            ArrayList<String> complet = new ArrayList<>();
            complet.add("Vélizy+");
            complet.addAll(chemin);
            complet.add("Vélizy-");

            int distNouveau = calculerDistanceTotale(complet);
            if (distancesVues.contains(distNouveau)) return;

            if (meilleurs.size() < k) {
                meilleurs.add(new ArrayList<>(complet));
                distancesVues.add(distNouveau);
            } else {
                int distMax = calculerDistanceTotale(meilleurs.peek());
                if (distNouveau < distMax) {
                    meilleurs.poll();
                    meilleurs.add(new ArrayList<>(complet));
                    distancesVues.add(distNouveau);
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

            backtrack(chemin, nouveauxCandidats, graphe, new HashMap<>(degreEntree), meilleurs, k, distancesVues);

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
