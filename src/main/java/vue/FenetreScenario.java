package vue;

import javafx.scene.layout.HBox;

public class FenetreScenario extends HBox {
    private Parcours parcours;
    private TableCorrespondance tableCorrespondance;

    /**
     * Constructeur de la classe FenetreScenario
     */
    public FenetreScenario() {
        parcours = new Parcours();
        tableCorrespondance = new TableCorrespondance();

        this.getChildren().addAll(tableCorrespondance,parcours);
    }

    /**
     * Accesseur du champ parcours
     * @return Parcours
     */
    public Parcours getParcours() { return parcours; }

    /**
     * Accesseur du champ talbeCorrespondance
     * @return TableCorrespondance
     */
    public TableCorrespondance getTableCorrespondance() { return tableCorrespondance; }
}
