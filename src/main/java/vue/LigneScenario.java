package vue;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import modele.ListeMembres;
import modele.Membre;

import java.io.FileNotFoundException;

public class LigneScenario extends HBox {
    public LigneScenario() throws FileNotFoundException {
        setSpacing(15);
        Label vendeur = new Label("Vendeur : ");
        ComboBox listeVendeur = new ComboBox();
        Label acheteur = new Label("Acheteur : ");
        ComboBox listeAcheteur = new ComboBox();

        ListeMembres listeMembres = new ListeMembres();

        for (Membre nom : listeMembres.getlisteDesMembres()) {
            listeVendeur.getItems().add(nom.getnomDuMembre());
            listeAcheteur.getItems().add(nom.getnomDuMembre());
        }

        this.getChildren().addAll(vendeur,listeVendeur,acheteur,listeAcheteur);
    }
}
