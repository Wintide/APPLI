package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
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
            //System.out.println(((MenuItem) event.getSource()).getText());
            String scenario = ((MenuItem) event.getSource()).getText();
            try {
                fenetreScenario.getTableCorrespondance().miseAJour(scenario);
                fenetreScenario.getParcours().setChMessageSelectionScenario(scenario);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }



    }

}
