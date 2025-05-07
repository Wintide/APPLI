package modele;

import java.io.File;
import java.io.FileNotFoundException;
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

        System.out.println(liste);

    }
}
