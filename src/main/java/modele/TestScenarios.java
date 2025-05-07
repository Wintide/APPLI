package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestScenarios {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner numScenario = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Entrer le numéro du scenario");

        String idSc = numScenario.nextLine();  // Read user input
        System.out.println(idSc);  // Output user input

        File doc = new File("fichier/scenario_"+idSc+".txt");
        Scanner obj = new Scanner(doc);
        String regex = "[,\\.\\s\\->]";
        Scenario scenario = new Scenario(0);
        while (obj.hasNextLine()) {
            String line = obj.nextLine();
            String[] split = line.split(regex);
            scenario.ajout(split[0], split[4]);

        }
        System.out.println(scenario);


    }
}
