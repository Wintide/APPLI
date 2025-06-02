package modele;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
public class VilleTest {

    @Test
    void constructeur() {
        String nom = "Paris";

        Ville ville = new Ville(nom);

        assertEquals("Paris", ville.getnomVille(), "Le nom de la ville doit être 'Paris'");
        assertNotNull(ville.getlisteDesDistances(), "La liste des distances ne doit pas être nulle");
        assertTrue(ville.getlisteDesDistances().isEmpty(), "La liste des distances doit être vide au départ");
    }

    @Test
    public void ajout() {
        Ville ville = new Ville("Lyon");
        ville.ajout("Marseille", 300);

        TreeMap<String, Integer> distances = ville.getlisteDesDistances();
        assertEquals(1, distances.size());
        assertTrue(distances.containsKey("Marseille"));
        assertEquals(300, distances.get("Marseille"));
    }

    @Test
    public void getnomVille() {
        Ville ville = new Ville("Toulouse");
        assertEquals("Toulouse", ville.getnomVille());
    }

    @Test
    public void getlisteDesDistances() {
        Ville ville = new Ville("Nice");
        ville.ajout("Cannes", 30);
        TreeMap<String, Integer> distances = ville.getlisteDesDistances();

        assertFalse(distances.isEmpty());
        assertEquals(30, distances.get("Cannes"));
    }

    @Test
    public void testToString() {
        Ville ville = new Ville("Bordeaux");
        ville.ajout("Pau", 200);
        ville.ajout("Bayonne", 180);

        String result = ville.toString();
        assertTrue(result.contains("Bordeaux"));
        assertTrue(result.contains("Pau"));
        assertTrue(result.contains("200"));
        assertTrue(result.contains("Bayonne"));
        assertTrue(result.contains("180"));
    }

}
