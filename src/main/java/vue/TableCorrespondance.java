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

    /**
     * Constructeur de la classe TableCorrespondance
     */
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
        this.setPrefWidth(500);
    }

    /**
     * Methode de mise a jour de la table de correspondance
     * @param parScenario
     * @throws FileNotFoundException
     */
    public void miseAJour(String parScenario) throws FileNotFoundException {
        table.getItems().clear();


        Scenario scenario = new Scenario(parScenario);


        // Remplissage du tableau
        for (Pair<String, String> transaction : scenario.getScenarios()) {
            String vendeurNom = transaction.getKey();
            String acheteurNom = transaction.getValue();

            String villeVendeur = scenario.getMembres().getVilleParNom(vendeurNom);
            String villeAcheteur = scenario.getMembres().getVilleParNom(acheteurNom);
            int distance = scenario.getVilles().getDistance(villeVendeur, villeAcheteur);

            LigneCorrespondance ligne = new LigneCorrespondance(
                    vendeurNom, villeVendeur, acheteurNom, villeAcheteur, distance
            );
            table.getItems().add(ligne);
        }
    }

}
