package vue;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.File;
import java.util.Optional;

public class Appli extends VBox {
    private static Controleur controleur;
    private static FenetreScenario fenetreScenario;
    private static FenetreCreation fenetreCreation;

    public Appli() {
        controleur = new Controleur();
        fenetreScenario = new FenetreScenario();
        fenetreCreation = new FenetreCreation();

        MenuBar menuBar = new MenuBar();
        Menu scenario = new Menu("Scenario");
        Menu creation = new Menu("Creation");
        Menu quitter = new Menu("Quitter");
        menuBar.getMenus().addAll(scenario, creation, quitter);

        File fichier = new File("scenario");

        for (File f : fichier.listFiles()) {
            MenuItem item = new MenuItem(f.getName());
            item.addEventHandler(ActionEvent.ACTION, new Controleur());
            scenario.getItems().add(item);
        }

        MenuItem itemQuitter = new MenuItem("Quitter");
        quitter.getItems().addAll(itemQuitter);

        itemQuitter.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Quitter l'application ?");
            alert.setHeaderText("Êtes-vous certain de vouloir quitter l'application ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                System.exit(0);
            }
        });

        fenetreCreation.setVisible(false);

        this.getChildren().addAll(menuBar,fenetreScenario,fenetreCreation);
    }

    public static Controleur getControleur() { return controleur; }

    public static FenetreScenario getFenetreScenario() { return fenetreScenario; }

    public static FenetreCreation getFenetreCreation() { return fenetreCreation; }

}
