package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class TestVille {
    public static void main(String[] args) throws FileNotFoundException {

        File doc = new File("fichier/distances.txt");
        Scanner obj = new Scanner(doc);
        String regex = "[,\\.\\s]";
        ArrayList<String> list = new ArrayList<String>();
        while (obj.hasNextLine()) {
            String line = obj.nextLine();
            String[] split = line.split(regex);
            list.add(split[0]);
        }
        System.out.println(list);

        Scanner obj2 = new Scanner(doc);
        while (obj2.hasNextLine()) {

            String[] myArray = obj2.nextLine().split(regex);
            Ville ville = new Ville(myArray[0]);

            for (int i = 1; i < myArray.length; i++) {
                int dist = Integer.parseInt(myArray[i]);
                ville.ajout(list.get(i - 1),dist);
            }
            System.out.println(ville);

        }




    }
}
