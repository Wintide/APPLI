package vue;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FenetreCreation extends VBox {
    private final Button ajouterUneLigne = new Button("Ajouter une ligne");
    private final Button retirerUneLigne = new Button("Retirer une ligne");
    private final Button enregistrer = new Button("Enregistrer");
    private int nbLigne = 0;
    private ArrayList<LigneScenario> listeLigne = new ArrayList<>();

    public FenetreCreation() {
        this.setSpacing(10);
        this.setAlignment(Pos.TOP_LEFT);
        ajouterUneLigne.addEventHandler(ActionEvent.ACTION, new Controleur());
        retirerUneLigne.addEventHandler(ActionEvent.ACTION, new Controleur());
        enregistrer.addEventHandler(ActionEvent.ACTION, new Controleur());
        this.getChildren().add(new HBox(ajouterUneLigne, retirerUneLigne, enregistrer));
    }

    public void ajouterUneLigne() throws FileNotFoundException {
        nbLigne++;
        LigneScenario ligneScenario = new LigneScenario();
        this.getChildren().add(ligneScenario);
        listeLigne.add(ligneScenario);
    }

    public void retirerUneLigne() throws FileNotFoundException {
        nbLigne--;
        this.getChildren().remove(nbLigne+1);
        listeLigne.remove(listeLigne.get(nbLigne-1));
    }

    public ArrayList<String> enregistrer() {
        ArrayList<String> nouveauScenario = new ArrayList<>();

        for (LigneScenario ligneScenario : listeLigne) {
            String vendeur = ligneScenario.getVendeur();
            String acheteur = ligneScenario.getAcheteur();
            String vente = vendeur + " -> " + acheteur;

            nouveauScenario.add(vente);

        }
        return nouveauScenario;
    }

    public Button getAjouterUneLigne() { return ajouterUneLigne; }
    public Button getRetirerUneLigne() { return retirerUneLigne; }
    public Button getEnregistrer() { return enregistrer; }
    public int getNbLigne() { return nbLigne; }
}
