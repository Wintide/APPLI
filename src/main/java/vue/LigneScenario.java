package vue;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class LigneScenario extends VBox {
    public LigneScenario() {
        Label vendeur = new Label("Vendeur");
        ComboBox listeVendeur = new ComboBox();
        Label acheteur = new Label("Acheteur");
        ComboBox listeAcheteur = new ComboBox();

        this.getChildren().addAll(vendeur,listeVendeur,acheteur,listeAcheteur);
    }
}
