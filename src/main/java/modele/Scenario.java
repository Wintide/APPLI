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


    /**
     * Accesseur du champ nbTotalChemins
     * @return le nombre total de chemins exploré pour le scenario
     */
    public int getNbTotalChemins() {
        return nbTotalChemins;
    }

    /**
     * Construteur par id du scenario (int)
     * @param idScenario
     * @throws FileNotFoundException
     */
    public Scenario(int idScenario) throws FileNotFoundException {

        ListeMembres liste = new ListeMembres();
        ListeVilles listeVilles = new ListeVilles();

        scenarios = new ArrayList<>();
        id = idScenario;
        villes = listeVilles;
        membres = liste;

        remplissageScenario();
    }

    /**
     * Constructeur par nom du scenario (String)
     * @param scenario
     * @throws FileNotFoundException
     */
    public Scenario(String scenario) throws FileNotFoundException {
        ListeMembres liste = new ListeMembres();
        ListeVilles listeVilles = new ListeVilles();
        String idStr = scenario.replaceAll("[^0-9]", "");
        int Nid = Integer.parseInt(idStr);

        scenarios = new ArrayList<>();
        id = Nid;
        villes = listeVilles;
        membres = liste;

        remplissageScenario();
    }

    /**
     * Remplis les infos du scenario
     * @throws FileNotFoundException
     */
    public void remplissageScenario() throws FileNotFoundException {
        String regex = "[,\\.\\s]";
        File doc = new File("scenario/scenario_"+id+".txt");
        Scanner scan = new Scanner(doc);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] split = line.split(regex);
            this.ajout(split[0], split[2]);
            System.out.println(1);

        }
    }

    /**
     * Accesseur du champ id
     * @return retourne l'id du scenario
     */
    public int getId() {
        return id;
    }

    /**
     * Ajouter une association (vendeur / acheteur) a la liste scenarios
     * @param source
     * @param destination
     */
    public void ajout(String source, String destination) {
        scenarios.add(new Pair<>(source, destination));
    }

    /**
     * Methode toString
     * @return une chaine de caractere renvoyant les infos du scenario
     */
    public String toString() {
        return "Scenario [id=" + id + ", scenarios=" + scenarios + "]";
    }

    /**
     * Accesseur sur le champ scenarios
     * @return les contraintes du scenario
     */
    public ArrayList<Pair<String,String>> getScenarios() {
        return scenarios;
    }

    /**
     * Accesseur sur le champ villes
     * @return la liste des villes
     */
    public ListeVilles getVilles() {
        return villes;
    }

    /**
     * Accesseur sur le champ membre
     * @return la liste des membres
     */
    public ListeMembres getMembres() {
        return membres;
    }

    /**
     * Trouve les villes par les quels ils faut passer
     * @return
     */
    public ArrayList<String> trouveVillePassage() {
        ArrayList<String> liste = new ArrayList<>();


        for (Pair<String,String> carte : this.getScenarios()) {

            for (Membre membre : membres.getlisteDesMembres()) {
                if ((membre.getnomDuMembre().compareTo(carte.getKey()) == 0) && (!liste.contains(membre.getvilleDeResidence()))) {
                    liste.add(membre.getvilleDeResidence());
                }
            }

            for (Membre membre : membres.getlisteDesMembres()) {
                if ((membre.getnomDuMembre().compareTo(carte.getValue()) == 0) && (!liste.contains(membre.getvilleDeResidence()))) {
                    liste.add(membre.getvilleDeResidence());
                }
            }


        }


        return liste;
    }

    /**
     * Associe les membres à leur ville
     * @return
     */
    public ArrayList<Pair<String,String>> associationMembresVilles() {
        ArrayList<Pair<String,String>> liste = new ArrayList<>();
        String villeDepart = new String();
        String villeArrive = new String();

        for (Pair<String,String> carte : this.getScenarios()) {

            for (Membre membre : membres.getlisteDesMembres()) {
                if (membre.getnomDuMembre().compareTo(carte.getKey()) == 0) {
                    villeDepart = membre.getvilleDeResidence();
                }
            }

            for (Membre membre : membres.getlisteDesMembres()) {
                if (membre.getnomDuMembre().compareTo(carte.getValue()) == 0) {
                    villeArrive = membre.getvilleDeResidence();
                }
            }

            liste.add(new Pair<>(villeDepart+"+", villeArrive+"-"));
        }


        return liste;
    }

    /**
     * Calcul l'itnineraire le plus basique de manierer rapide
     * @return
     */
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

    /**
     * Permet d'extraire les + et - des villes dans les algos
     * @param sommet
     * @return
     */
    private String extraireNomVille(String sommet) {
        if (sommet.endsWith("+") || sommet.endsWith("-")) {
            return sommet.substring(0, sommet.length() - 1);
        }
        return sommet;
    }

    /**
     * calcul les k meilleurs itineraires du scenario
     * @param k
     * @return
     */
    public ArrayList<ArrayList<String>> calculKMeilleursItineraires(int k) {
        nbTotalChemins = 0;

        ArrayList<Pair<String, String>> contraintes = this.associationMembresVilles();

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

        PriorityQueue<ArrayList<String>> meilleurs = new PriorityQueue<>(
                k, Comparator.comparingInt(this::calculerDistanceTotale).reversed()
        );

        List<String> candidats = new ArrayList<>();
        for (String s : sommets) {
            if (degreEntree.get(s) == 0) {
                candidats.add(s);
            }
        }

        Set<Integer> distancesVues = new HashSet<>();

        triRecursif(new ArrayList<>(), candidats, graphe, new HashMap<>(degreEntree), meilleurs, k, distancesVues);

        ArrayList<ArrayList<String>> trie = new ArrayList<>(meilleurs);
        trie.sort(Comparator.comparingInt(this::calculerDistanceTotale));

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

    /**
     * Calcul les chemins les plus optimaux tri topologique recursif
     * @param chemin
     * @param candidats
     * @param graphe
     * @param degreEntree
     * @param meilleurs
     * @param k
     * @param distancesVues
     */
    private void triRecursif(ArrayList<String> chemin, List<String> candidats,
                           Map<String, List<String>> graphe, Map<String, Integer> degreEntree,
                           PriorityQueue<ArrayList<String>> meilleurs, int k, Set<Integer> distancesVues) {
        nbTotalChemins++;

        if (nbTotalChemins%100000==0) {
            System.out.println(nbTotalChemins);
        }

        if (chemin.size() == degreEntree.size()) {


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
                    for (ArrayList<String> c : meilleurs) {
                        //System.out.println(calculerDistanceTotale(c));
                    }
                    distancesVues.add(distNouveau);
                }
            }
            return;
        }

        candidats.sort(Comparator.comparingInt(v -> {
            String derniereVille = chemin.isEmpty() ? "Vélizy+" : chemin.get(chemin.size() - 1);
            Ville villeActuelle = villes.getVilleParNom(extraireNomVille(derniereVille));
            return villeActuelle != null ? villeActuelle.getlisteDesDistances().getOrDefault(extraireNomVille(v), Integer.MAX_VALUE / 2) : Integer.MAX_VALUE / 2;
        }));

        for (int i = 0; i < candidats.size(); i++) {
            String courant = candidats.get(i);
            chemin.add(courant);

            ArrayList<String> prefixe = new ArrayList<>();
            prefixe.add("Vélizy+");
            prefixe.addAll(chemin);
            prefixe.add("Vélizy-");

            int distPartielle = calculerDistanceTotale(prefixe);
            if (meilleurs.size() == k && distPartielle >= calculerDistanceTotale(meilleurs.peek())) {
                chemin.remove(chemin.size() - 1);
                continue;
            }

            List<String> nouveauxCandidats = new ArrayList<>(candidats);
            nouveauxCandidats.remove(i);

            for (String voisin : graphe.get(courant)) {
                degreEntree.put(voisin, degreEntree.get(voisin) - 1);
                if (degreEntree.get(voisin) == 0) {
                    nouveauxCandidats.add(voisin);
                }
            }

            if (nbTotalChemins < 10000000) {
                triRecursif(chemin, nouveauxCandidats, graphe, new HashMap<>(degreEntree), meilleurs, k, distancesVues);
            }


            chemin.remove(chemin.size() - 1);
            for (String voisin : graphe.get(courant)) {
                degreEntree.put(voisin, degreEntree.get(voisin) + 1);
            }
        }
    }


    /**
     * Calcul la distance totale a pracour pour un chemin donnée
     * @param itineraire
     * @return
     */
    public int calculerDistanceTotale(ArrayList<String> itineraire) {
        int total = 0;

        for (int i = 0; i < itineraire.size() - 1; i++) {
            String villeA = extraireNomVille(itineraire.get(i));
            String villeB = extraireNomVille(itineraire.get(i + 1));

            Ville villeSource = villes.getVilleParNom(villeA);
            if (villeSource != null && villeSource.getlisteDesDistances().containsKey(villeB)) {
                total += villeSource.getlisteDesDistances().get(villeB);
            } else {
                total += Integer.MAX_VALUE / 2; // pénalité si pas de chemin connu
            }
        }

        return total;
    }







}
