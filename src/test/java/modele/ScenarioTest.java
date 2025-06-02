package modele;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class ScenarioTest {
    @Test
    public void testAssociationMembresVilles() throws FileNotFoundException {
        Scenario scenario = new Scenario(0);
        ArrayList<Pair<String, String>> associations = scenario.associationMembresVilles();
        assertEquals(5, associations.size());
        assertEquals(new Pair<>("Lyon+", "Grenoble-"), associations.get(0));
        assertEquals(new Pair<>("Grenoble+", "Lyon-"), associations.get(1));
    }

    @Test
    public void testCalculerDistanceTotale() throws FileNotFoundException {
        Scenario scenario = new Scenario(0);
        ArrayList<String> itineraire = new ArrayList<>(List.of("Vélizy+", "Paris+", "Lyon+", "Marseille-", "Vélizy-"));
        int distance = scenario.calculerDistanceTotale(itineraire);
        assertEquals(1622, distance);
    }

    @Test
    public void testCalculKMeilleursItineraires() throws FileNotFoundException {
        Scenario scenario = new Scenario(0);
        ArrayList<ArrayList<String>> itineraires = scenario.calculKMeilleursItineraires(2);
        assertEquals(2, itineraires.size());
        ArrayList<String> best = itineraires.get(0);

        assertEquals("Vélizy+", best.get(0));
        assertEquals("Vélizy-", best.get(best.size() - 1));

        assertTrue(best.contains("Grenoble+"));
        assertTrue(best.contains("Lyon+"));
        assertTrue(best.contains("Marseille-"));
    }
}
