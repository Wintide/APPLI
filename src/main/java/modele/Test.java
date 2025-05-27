package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {

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
        System.out.println("Scenarios disponibles : ");
        for (File fichier : new File("scenario").listFiles()) {
            System.out.println(fichier.getName());
        }

        Scanner numScenario = new Scanner(System.in);
        System.out.println("Entrer le numéro du scenario : ");

        String idSc = numScenario.nextLine();
        int intId = Integer.parseInt(idSc);

        File doc3 = new File("scenario/scenario_"+idSc+".txt");
        Scanner obj4 = new Scanner(doc3);
        Scenario scenario = new Scenario(intId,listeVilles,liste);
        while (obj4.hasNextLine()) {
            String line = obj4.nextLine();
            String[] split = line.split(regex);
            scenario.ajout(split[0], split[2]);

        }




        System.out.println(scenario.associationMembresVilles());
        //System.out.println(scenario.trouveVillePassage());

        System.out.println("Type d'itineraire :\n 1-N'importe quel itineraire \n 2-Itineraire le plus court \n 3-k meilleurs solutions" );
        Scanner decisionCalcul = new Scanner(System.in);
        String num = decisionCalcul.nextLine();
        int numero = Integer.parseInt(num);

        if (numero == 1) {
            System.out.println(scenario.calculItineraire());
            System.out.println(scenario.calculDistanceTotale(scenario.calculItineraire()));
        }
        if (numero == 2) {
            ArrayList<ArrayList<String>> meilleurs = scenario.calculKMeilleursItineraires(1);
            System.out.println("Meilleurs itinéraires :");
            for (ArrayList<String> chemin : meilleurs) {
                System.out.println(chemin + " -> " + scenario.calculerDistanceTotale(chemin) + " km");
            }
            System.out.println("Nombre total de chemins explorés : " + scenario.getNbTotalChemins());


        }
        if (numero == 3) {
            Scanner Kscan = new Scanner(System.in);
            String Kstring = Kscan.nextLine();
            int K = Integer.parseInt(Kstring);
            ArrayList<ArrayList<String>> meilleurs = scenario.calculKMeilleursItineraires(K);
            System.out.println("Meilleurs itinéraires :");
            for (ArrayList<String> chemin : meilleurs) {
                System.out.println(chemin + " -> " + scenario.calculerDistanceTotale(chemin) + " km");
            }
            System.out.println("Nombre total de chemins explorés : " + scenario.getNbTotalChemins());

        }
        if (numero != 1 && numero !=2 && numero != 3) { System.out.println("Option non valide!"); }




    }
}
