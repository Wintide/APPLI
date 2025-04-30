package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        File doc = new File("fichier/membres_APPLI.txt");
        Scanner obj = new Scanner(doc);
        String regex = "[,\\.\\s]";

        while (obj.hasNextLine()) {

            String[] myArray = obj.nextLine().split(regex);
            for (String s : myArray) {
                System.out.println(s);
            }
        }
    }
}
