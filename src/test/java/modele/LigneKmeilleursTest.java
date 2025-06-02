package modele;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LigneKmeilleursTest {

    @Test
    void testClasse() {

        ArrayList<String> chemin = new ArrayList<>(List.of("Vélizy+", "Paris+", "Lyon-", "Vélizy-"));
        int distance = 22;

        LigneKmeilleurs ligne = new LigneKmeilleurs(distance, chemin);

        assertEquals(distance, ligne.getDistance());
        assertEquals(chemin, ligne.getChemin());
        assertEquals(4, ligne.getChemin().size());
        assertEquals("Paris+", ligne.getChemin().get(1));
    }
}
