package vue;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.File;
import java.util.Optional;

public class Appli extends VBox {
    private static Controleur controleur;
    private static FenetreScenario fenetreScenario;
    private static FenetreCreation fenetreCreation;
    private static ScrollPane fenetreCreationScrollPane;
    private static Menu scenario;

    public Appli() {
        controleur = new Controleur();
        fenetreScenario = new FenetreScenario();
        fenetreCreation = new FenetreCreation();
        fenetreCreationScrollPane = new ScrollPane(fenetreCreation);

        MenuBar menuBar = new MenuBar();
        scenario = new Menu("Scenario");
        Menu creation = new Menu("Creation");
        Menu quitter = new Menu("Quitter");
        menuBar.getMenus().addAll(scenario, creation, quitter);

        File fichier = new File("scenario");

        for (File f : fichier.listFiles()) {
            MenuItem item = new MenuItem(f.getName());
            item.addEventHandler(ActionEvent.ACTION, new Controleur());
            scenario.getItems().add(item);
        }

        MenuItem itemCreation = new MenuItem("Creer un scenario");
        creation.getItems().add(itemCreation);
        itemCreation.addEventHandler(ActionEvent.ACTION, new Controleur());

        MenuItem itemQuitter = new MenuItem("Quitter");
        quitter.getItems().addAll(itemQuitter);

        itemQuitter.addEventHandler(ActionEvent.ACTION, new Controleur());

        fenetreCreationScrollPane.setVisible(false);
        StackPane contenu = new StackPane();
        contenu.getChildren().addAll(fenetreScenario, fenetreCreationScrollPane);
        this.getChildren().addAll(menuBar, contenu);

    }

    public static Controleur getControleur() { return controleur; }

    public static FenetreScenario getFenetreScenario() { return fenetreScenario; }

    public static FenetreCreation getFenetreCreation() { return fenetreCreation; }

    public static ScrollPane getFenetreCreationScrollPane() { return fenetreCreationScrollPane; }

    public static void remplissageMenuBarScenario() {
        scenario.getItems().clear();
        File fichier = new File("scenario");

        for (File f : fichier.listFiles()) {
            MenuItem item = new MenuItem(f.getName());
            item.addEventHandler(ActionEvent.ACTION, new Controleur());
            scenario.getItems().add(item);
        }
    }


}
