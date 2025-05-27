package vue;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class Parcours extends VBox {
    private Label chMessageSelectionScenario;

    public Parcours() {

        chMessageSelectionScenario = new Label("Veuillez choisir un scenario!");

        this.getChildren().add(chMessageSelectionScenario);

    }

    public Label getChMessageSelectionScenario() {
        return chMessageSelectionScenario;
    }

    public void setChMessageSelectionScenario(String parContenuLabelScenario) {
        chMessageSelectionScenario.setText(parContenuLabelScenario);
    }

    public void miseAJour(String parScenario) {

    }
}
