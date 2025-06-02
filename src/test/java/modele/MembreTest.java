package modele;
import modele.Membre;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MembreTest {

    @Test
    public void constructeurEtAccesseurs() {
        Membre membre = new Membre("Alice", "Paris");

        assertEquals("Alice", membre.getnomDuMembre());
        assertEquals("Paris", membre.getvilleDeResidence());
    }

    @Test
    public void testToString() {
        Membre membre = new Membre("Bob", "Lyon");
        String result = membre.toString();

        assertTrue(result.contains("Bob"));
        assertTrue(result.contains("Lyon"));
        assertTrue(result.contains("Nom du membre"));
    }

    @Test
    public void compareTo_casDesNomsEgaux() {
        Membre m1 = new Membre("Charlie", "Marseille");
        Membre m2 = new Membre("Charlie", "Lille");

        assertTrue(m1.compareTo(m2) > 0);
    }

    @Test
    public void compareTo_casDesNomsDifferents() {
        Membre m1 = new Membre("Alice", "Paris");
        Membre m2 = new Membre("Bob", "Paris");

        assertTrue(m1.compareTo(m2) < 0); // "Alice" < "Bob"
    }

    @Test
    public void compareTo_casMembresIdentiques() {
        Membre m1 = new Membre("David", "Nice");
        Membre m2 = new Membre("David", "Nice");

        assertEquals(0, m1.compareTo(m2));
    }
}
