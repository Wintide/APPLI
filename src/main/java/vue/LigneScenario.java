package vue;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import modele.ListeMembres;
import modele.Membre;

import java.io.FileNotFoundException;

public class LigneScenario extends HBox {
    private ComboBox listeVendeur;
    private ComboBox listeAcheteur;

    /**
     * Constructeur de la classe LigneScenario (correspodance au ligne que l'on voit dans le fenetre de creation de scenario
     * @throws FileNotFoundException
     */
    public LigneScenario() throws FileNotFoundException {
        setSpacing(15);
        Label vendeur = new Label("_Vendeur :");
        vendeur.setMnemonicParsing(true);
        listeVendeur = new ComboBox<>();
        vendeur.setLabelFor(listeVendeur);
        vendeur.setOnMouseClicked(event -> listeVendeur.show());

        Label acheteur = new Label("_Acheteur :");
        acheteur.setMnemonicParsing(true);
        listeAcheteur = new ComboBox<>();
        acheteur.setLabelFor(listeAcheteur);
        acheteur.setOnMouseClicked(event -> listeAcheteur.show());


        ListeMembres listeMembres = new ListeMembres();

        for (Membre nom : listeMembres.getlisteDesMembres()) {
            listeVendeur.getItems().add(nom.getnomDuMembre());
            listeAcheteur.getItems().add(nom.getnomDuMembre());
        }

        this.getChildren().addAll(vendeur,listeVendeur,acheteur,listeAcheteur);

        listeVendeur.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                listeVendeur.show();
            }
        });

        listeAcheteur.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                listeAcheteur.show();
            }
        });
    }

    /**
     * Accesseur du champ listeVendeur
     * @return String
     */
    public String getVendeur() {
        Object vendeur = listeVendeur.getSelectionModel().getSelectedItem();
        if (vendeur != null) {
            return vendeur.toString();
        } else {
            return null;
        }
    }

    /**
     * Accesseur du champ listeAcheteur
     * @return String
     */
    public String getAcheteur() {
        Object acheteur = listeAcheteur.getSelectionModel().getSelectedItem();
        if (acheteur != null) {
            return acheteur.toString();
        } else {
            return null;
        }
    }
}
