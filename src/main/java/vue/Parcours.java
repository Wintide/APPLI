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
    private Label messageSelectionScenario;
    private Label itineraireQuelconque;
    private Label meilleurItineraire;
    private Label k;
    private TextField saisisK;
    private TableView table;
    private int idScenarioActuel;

    /**
     * Constructeur de la classe Parcours contenant les infos d'un scenario
     */
    public Parcours() {

        messageSelectionScenario = new Label("Veuillez choisir un scenario!");
        itineraireQuelconque = new Label("");
        meilleurItineraire = new Label("");
        k = new Label("Saisis un K : ");

        HBox hb = new HBox();

        saisisK = new TextField();
        saisisK.setOnKeyPressed(event -> {
            String keyValue = event.getCode().getName();
            if (keyValue.equals("Enter") && !saisisK.getText().equals("")) {
                int k = Integer.parseInt(saisisK.getText());
                try {
                    this.majTable(k);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        saisisK.setVisible(false);
        k.setVisible(false);

        hb.getChildren().addAll(k,saisisK);

        table = new TableView<>();

        TableColumn<LigneKmeilleurs, Integer> colDistance = new TableColumn<>("Distance");
        TableColumn<LigneKmeilleurs, ArrayList<String>> colChemin = new TableColumn<>("Chemin");

        colDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        colChemin.setCellValueFactory(new PropertyValueFactory<>("chemin"));

        colDistance.setPrefWidth(100);
        colChemin.setPrefWidth(600);

        table.getColumns().addAll(colDistance, colChemin);
        table.setVisible(false);

        this.getChildren().addAll(messageSelectionScenario,itineraireQuelconque,meilleurItineraire,hb,table);
        this.setPrefWidth(775);

    }

    /**
     * Accesseur du champ messageSelectionScenario
     * @return Label
     */
    public Label getmessageSelectionScenario() {
        return messageSelectionScenario;
    }

    /**
     * Accesseur du champ saisisK
     * @return TextField
     */
    public TextField getsaisisK() {  return saisisK;  }

    /**
     * Accesseur du champ k
     * @return Label
     */
    public Label getk() {  return k;  }

    /**
     * Accesseur du champ table
     * @return TableView
     */
    public TableView getTable() {  return table;  }

    /**
     * Accesseur du champ idScenarioActuel
     * @return int
     */
    public int getIdScenarioActuel() { return idScenarioActuel;}

    /**
     * Seteur du champ idScenarioActuel
     * @param idScenarioActuel
     */
    public void setIdScenarioActuel(int idScenarioActuel) { this.idScenarioActuel = idScenarioActuel;}

    /**
     * Seteur du champ messageSelectionScenario
     * @param contenuLabelScenario
     */
    public void setmessageSelectionScenario(String contenuLabelScenario) {
        messageSelectionScenario.setText(contenuLabelScenario);
    }

    /**
     * Seteur du champ itineraireQuelconque
     * @param contenuLabelItineraire
     */
    public void setitineraireQuelconque(String contenuLabelItineraire) {
        itineraireQuelconque.setText(contenuLabelItineraire);
    }

    /**
     * Seteur du champ meilleurItineraire
     * @param contenuLabelMeilleurItineraire
     */
    public void setmeilleurItineraire(String contenuLabelMeilleurItineraire) {
        meilleurItineraire.setText(contenuLabelMeilleurItineraire);
    }

    /**
     * Methode de mise à jour de l'affichage de la fenetre
     * @param parScenario
     * @throws FileNotFoundException
     */
    public void miseAJour(String parScenario) throws FileNotFoundException {
        setmessageSelectionScenario("Info sur : "+parScenario);

        Scenario scenario = new Scenario(parScenario);

        setIdScenarioActuel(scenario.getId());

        ArrayList<ArrayList<String>> meilleurs = scenario.calculKMeilleursItineraires(1);
        for (ArrayList<String> chemin : meilleurs) {
            String iti = chemin + " -> " + scenario.calculerDistanceTotale(chemin) + " km";
            setmeilleurItineraire("Meilleur itineraire : "+iti);
        }

        setitineraireQuelconque("Itineraire quelconque : " + scenario.calculItineraire() + " -> " + scenario.calculerDistanceTotale(scenario.calculItineraire()) + " km");

        getsaisisK().setVisible(true);
        getk().setVisible(true);
        getsaisisK().setText("");
        getTable().setVisible(true);
        getTable().getItems().clear();
        getsaisisK().requestFocus();


    }

    /**
     * Methode de mise a jour de la table
     * @param k
     * @throws FileNotFoundException
     */
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
