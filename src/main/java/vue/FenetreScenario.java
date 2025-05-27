package vue;

import javafx.scene.layout.HBox;

public class FenetreScenario extends HBox {
    private Parcours parcours;
    private TableCorrespondance tableCorrespondance;

    public FenetreScenario() {
        parcours = new Parcours();
        tableCorrespondance = new TableCorrespondance();

        this.getChildren().addAll(tableCorrespondance,parcours);
    }

    public Parcours getParcours() { return parcours; }

    public TableCorrespondance getTableCorrespondance() { return tableCorrespondance; }
}
