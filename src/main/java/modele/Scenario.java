package modele;

import javafx.util.Pair;
import java.util.ArrayList;

public class Scenario {
    private int id;
    private ArrayList<Pair<String,String>> scenarios;

    public Scenario(int parId) {
        scenarios = new ArrayList<>();
        id = parId;
    }

    public int getId() {
        return id;
    }

    public void ajout(String parSource, String parDestination) {
        scenarios.add(new Pair<>(parSource, parDestination));
    }

    public String toString() {
        return "Scenario [id=" + id + ", scenarios=" + scenarios + "]";
    }
}
