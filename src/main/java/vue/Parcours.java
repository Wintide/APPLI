package vue;

import controleur.Controleur;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.LigneKmeilleurs;
import modele.Scenario;

import javafx.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parcours extends VBox {
    private Label chMessageSelectionScenario;
    private Label chItineraireQuelconque;
    private Label chMeilleurItineraire;
    private Label chK;
    private TextField chSaisisK;
    private TableView table;
    private int idScenarioActuel;

    public Parcours() {

        chMessageSelectionScenario = new Label("Veuillez choisir un scenario!");
        chItineraireQuelconque = new Label("");
        chMeilleurItineraire = new Label("");
        chK = new Label("Saisis un K : ");

        HBox hb = new HBox();

        chSaisisK = new TextField();
        chSaisisK.setOnKeyPressed(event -> {
            String keyValue = event.getCode().getName();
            if (keyValue.equals("Enter") && !chSaisisK.getText().equals("")) {
                int k = Integer.parseInt(chSaisisK.getText());
                try {
                    this.majTable(k);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        chSaisisK.setVisible(false);
        chK.setVisible(false);

        hb.getChildren().addAll(chK,chSaisisK);

        table = new TableView<>();

        TableColumn<LigneKmeilleurs, Integer> colDistance = new TableColumn<>("Distance");
        TableColumn<LigneKmeilleurs, ArrayList<String>> colChemin = new TableColumn<>("Chemin");

        colDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        colChemin.setCellValueFactory(new PropertyValueFactory<>("chemin"));

        colDistance.setPrefWidth(100);
        colChemin.setPrefWidth(600);

        table.getColumns().addAll(colDistance, colChemin);
        table.setVisible(false);

        this.getChildren().addAll(chMessageSelectionScenario,chItineraireQuelconque,chMeilleurItineraire,hb,table);
        this.setPrefWidth(775);

    }

    public Label getChMessageSelectionScenario() {
        return chMessageSelectionScenario;
    }

    public TextField getChSaisisK() {  return chSaisisK;  }
    public Label getChK() {  return chK;  }
    public TableView getTable() {  return table;  }
    public int getIdScenarioActuel() { return idScenarioActuel;}

    public void setIdScenarioActuel(int idScenarioActuel) { this.idScenarioActuel = idScenarioActuel;}

    public void setChMessageSelectionScenario(String parContenuLabelScenario) {
        chMessageSelectionScenario.setText(parContenuLabelScenario);
    }

    public void setChItineraireQuelconque(String parContenuLabelItineraire) {
        chItineraireQuelconque.setText(parContenuLabelItineraire);
    }

    public void setChMeilleurItineraire(String parContenuLabelMeilleurItineraire) {
        chMeilleurItineraire.setText(parContenuLabelMeilleurItineraire);
    }

    public void miseAJour(String parScenario) throws FileNotFoundException {
        setChMessageSelectionScenario("Info sur : "+parScenario);

        Scenario scenario = new Scenario(parScenario);

        setIdScenarioActuel(scenario.getId());

        ArrayList<ArrayList<String>> meilleurs = scenario.calculKMeilleursItineraires(1);
        for (ArrayList<String> chemin : meilleurs) {
            String iti = chemin + " -> " + scenario.calculerDistanceTotale(chemin) + " km";
            setChMeilleurItineraire("Meilleur itineraire : "+iti);
        }

        setChItineraireQuelconque("Itineraire quelconque : " + scenario.calculItineraire() + " -> " + scenario.calculerDistanceTotale(scenario.calculItineraire()) + " km");

        getChSaisisK().setVisible(true);
        getChK().setVisible(true);
        getChSaisisK().setText("");
        getTable().setVisible(true);
        getTable().getItems().clear();
        getChSaisisK().requestFocus();


    }

    public void majTable(int k) throws FileNotFoundException {
        getTable().getItems().clear();
        Scenario scenario = new Scenario(idScenarioActuel);

        ArrayList<ArrayList<String>> meilleurs = scenario.calculKMeilleursItineraires(k);
        for (ArrayList<String> chemin : meilleurs) {
            Integer distance = scenario.calculerDistanceTotale(chemin);
            LigneKmeilleurs ligne = new LigneKmeilleurs(distance,chemin);
            table.getItems().add(ligne);
        }


    }
}
