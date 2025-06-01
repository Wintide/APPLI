package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import vue.Appli;
import vue.FenetreCreation;
import vue.FenetreScenario;
import vue.TableCorrespondance;

import java.io.FileNotFoundException;
import java.util.Optional;

public class Controleur implements EventHandler {
    @Override
    public void handle(Event event) {
        FenetreScenario fenetreScenario = Appli.getFenetreScenario();
        FenetreCreation fenetreCreation = Appli.getFenetreCreation();
        ScrollPane fenetreCreationScrollPane = Appli.getFenetreCreationScrollPane();

        if (event.getSource() instanceof MenuItem menuItem) {
            switch (menuItem.getText()) {
                case "Creer un scenario" -> {
                    fenetreScenario.setVisible(false);
                    fenetreCreationScrollPane.setVisible(true);
                }
                case "Quitter" -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Quitter l'application ?");
                    alert.setHeaderText("Êtes-vous certain de vouloir quitter l'application ?");
                    if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                        System.exit(0);
                    }
                }
                default -> {
                    fenetreCreationScrollPane.setVisible(false);
                    fenetreScenario.setVisible(true);
                    try {
                        fenetreScenario.getTableCorrespondance().miseAJour(menuItem.getText());
                        fenetreScenario.getParcours().miseAJour(menuItem.getText());
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }


        if (event.getSource() instanceof Button) {
            if (((Button) event.getSource()).getText().equals("Ajouter une ligne")) {
                try {
                    fenetreCreation.ajouterUneLigne();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            if (((Button) event.getSource()).getText().equals("Retirer une ligne") && fenetreCreation.getNbLigne()!=0) {
                try {
                    fenetreCreation.retirerUneLigne();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            if (((Button) event.getSource()).getText().equals("Enregistrer") && fenetreCreation.getNbLigne()!=0) {
                System.out.println("Enregistrement");
            }
        }




    }

}
