package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {




        while (true) {
            String regex = "[,\\.\\s]";

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
            Scenario scenario = new Scenario(intId);
            while (obj4.hasNextLine()) {
                String line = obj4.nextLine();
                String[] split = line.split(regex);
                scenario.ajout(split[0], split[2]);

            }

            System.out.println(scenario.associationMembresVilles());
            System.out.println(scenario.trouveVillePassage());

            System.out.println("Type d'itineraire :\n 1-N'importe quel itineraire \n 2-Itineraire le plus court \n 3-k meilleurs solutions" );
            Scanner decisionCalcul = new Scanner(System.in);
            String num = decisionCalcul.nextLine();
            int numero = Integer.parseInt(num);

            if (numero == 1) {
                System.out.println(scenario.calculItineraire());
                System.out.println(scenario.calculerDistanceTotale(scenario.calculItineraire()));
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
                System.out.println("Choisissez le k : ");
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
}
