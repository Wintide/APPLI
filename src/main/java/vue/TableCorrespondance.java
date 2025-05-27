package vue;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import modele.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TableCorrespondance extends VBox {
    private TableView table;

    public TableCorrespondance() {
        table = new TableView<>();

        TableColumn<LigneCorrespondance, String> vendeur = new TableColumn<>("Vendeur");
        TableColumn<LigneCorrespondance, String> acheteur = new TableColumn<>("Acheteur");
        TableColumn<LigneCorrespondance, Integer> distance = new TableColumn<>("Distance");

        vendeur.setCellValueFactory(new PropertyValueFactory<>("vendeur"));
        acheteur.setCellValueFactory(new PropertyValueFactory<>("acheteur"));
        distance.setCellValueFactory(new PropertyValueFactory<>("distance"));

        vendeur.setPrefWidth(200);
        acheteur.setPrefWidth(200);
        distance.setPrefWidth(100);

        table.getColumns().addAll(vendeur, acheteur, distance);

        this.getChildren().add(table);
    }

    public void miseAJour(String parScenario) throws FileNotFoundException {
        table.getItems().clear();

        // Chemins des fichiers
        File fichierScenario = new File("scenario/"+ parScenario);
        File fichierMembres = new File("fichier/membres_APPLI.txt");
        File fichierDistances = new File("fichier/distances.txt");

        // Charger les membres
        ListeMembres listeMembres = new ListeMembres();
        Scanner scanMembres = new Scanner(fichierMembres);
        String regex = "[,\\.\\s]";
        while (scanMembres.hasNextLine()) {
            String[] tokens = scanMembres.nextLine().split(regex);
            if (tokens.length >= 2)
                listeMembres.addMembre(new Membre(tokens[0], tokens[1]));
        }

        // Charger les villes et distances
        ListeVilles listeVilles = new ListeVilles();
        Scanner scanDist = new Scanner(fichierDistances);
        ArrayList<String> nomsVilles = new ArrayList<>();
        ArrayList<String[]> lignesDistances = new ArrayList<>();
        while (scanDist.hasNextLine()) {
            String[] tokens = scanDist.nextLine().split(regex);
            nomsVilles.add(tokens[0]);
            lignesDistances.add(tokens);
        }
        for (String[] ligne : lignesDistances) {
            Ville ville = new Ville(ligne[0]);
            for (int i = 1; i < ligne.length; i++) {
                ville.ajout(nomsVilles.get(i - 1), Integer.parseInt(ligne[i]));
            }
            listeVilles.ajoutVilles(ville);
        }

        // Charger le scénario
        String idStr = parScenario.replaceAll("[^0-9]", ""); // garde uniquement les chiffres
        int id = Integer.parseInt(idStr);

        Scenario scenario = new Scenario(id, listeVilles, listeMembres);
        Scanner scanScenario = new Scanner(fichierScenario);
        while (scanScenario.hasNextLine()) {
            String[] tokens = scanScenario.nextLine().split(regex);
            if (tokens.length >= 3)
                scenario.ajout(tokens[0], tokens[2]);
        }

        // Remplissage du tableau
        for (Pair<String, String> transaction : scenario.getScenarios()) {
            String vendeurNom = transaction.getKey();
            String acheteurNom = transaction.getValue();

            String villeVendeur = listeMembres.getVilleParNom(vendeurNom);
            String villeAcheteur = listeMembres.getVilleParNom(acheteurNom);
            int distance = listeVilles.getDistance(villeVendeur, villeAcheteur);

            LigneCorrespondance ligne = new LigneCorrespondance(
                    vendeurNom, villeVendeur, acheteurNom, villeAcheteur, distance
            );
            table.getItems().add(ligne);
        }
    }

}
