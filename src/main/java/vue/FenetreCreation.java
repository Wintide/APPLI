package vue;

import controleur.Controleur;
import exception.ExceptionEnregistrement;
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

    /**
     * Constructeur de la classe FenetreCreation
     */
    public FenetreCreation() {
        this.setSpacing(10);
        this.setAlignment(Pos.TOP_LEFT);
        ajouterUneLigne.addEventHandler(ActionEvent.ACTION, new Controleur());
        retirerUneLigne.addEventHandler(ActionEvent.ACTION, new Controleur());
        enregistrer.addEventHandler(ActionEvent.ACTION, new Controleur());
        this.getChildren().add(new HBox(ajouterUneLigne, retirerUneLigne, enregistrer));
    }

    /**
     * Ajoute une ligne de la classe LigneScenario à l'interface
     * @throws FileNotFoundException
     */
    public void ajouterUneLigne() throws FileNotFoundException {
        nbLigne++;
        LigneScenario ligneScenario = new LigneScenario();
        this.getChildren().add(ligneScenario);
        listeLigne.add(ligneScenario);
    }

    /**
     * Retire une ligne de l'interface
     * @throws FileNotFoundException
     */
    public void retirerUneLigne() throws FileNotFoundException {
        nbLigne--;
        this.getChildren().remove(nbLigne+1);
        listeLigne.remove(listeLigne.get(nbLigne-1));
    }

    /**
     * Enregistre le contenu des lignes actuels dans un nouveau fichier (gerer dans le controleur)
     * @return ArrayList
     */
    public ArrayList<String> enregistrer() throws ExceptionEnregistrement {
        ArrayList<String> nouveauScenario = new ArrayList<>();

        for (LigneScenario ligneScenario : listeLigne) {



            if (ligneScenario.getVendeur() == null || ligneScenario.getAcheteur() == null) {
                throw new ExceptionEnregistrement("Ligne " + (listeLigne.indexOf(ligneScenario)+1) + " : une sélection est manquante.");
            }

            if (ligneScenario.getVendeur().equals(ligneScenario.getAcheteur())) {
                throw new ExceptionEnregistrement("Ligne " + (listeLigne.indexOf(ligneScenario)+1) + " : les deux villes sélectionnées sont identiques.");
            }

            String vendeur = ligneScenario.getVendeur();
            String acheteur = ligneScenario.getAcheteur();

            String vente = vendeur + " -> " + acheteur;

            nouveauScenario.add(vente);

        }
        return nouveauScenario;
    }

    /**
     * Accesseur du champ ajouterUneLigne
     * @return Button
     */
    public Button getAjouterUneLigne() { return ajouterUneLigne; }

    /**
     * Accesseur du champ retirerUneLigne
     * @return Button
     */
    public Button getRetirerUneLigne() { return retirerUneLigne; }

    /**
     * Accesseur du champ enregistrer
     * @return Button
     */
    public Button getEnregistrer() { return enregistrer; }

    /**
     * Accesseur du champ nbLigne
     * @return int
     */
    public int getNbLigne() { return nbLigne; }
}
