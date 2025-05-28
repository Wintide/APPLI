package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import vue.Appli;
import vue.FenetreScenario;
import vue.TableCorrespondance;

import java.io.FileNotFoundException;
import java.util.Optional;

public class Controleur implements EventHandler {
    @Override
    public void handle(Event event) {
        FenetreScenario fenetreScenario = Appli.getFenetreScenario();

        if (event.getSource() instanceof MenuItem) {

            String scenario = ((MenuItem) event.getSource()).getText();
            try {

                fenetreScenario.getTableCorrespondance().miseAJour(scenario);
                fenetreScenario.getParcours().miseAJour(scenario);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }





    }

}
