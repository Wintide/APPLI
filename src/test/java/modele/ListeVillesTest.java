package modele;

import modele.ListeVilles;
import modele.Ville;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ListeVillesTest {

    @Test
    public void getlisteDesVilles() throws Exception {

        ListeVilles lv = new ListeVilles();

        ArrayList<Ville> villes = lv.getlisteDesVilles();
        assertEquals(29, villes.size());
        assertEquals("Amiens", villes.get(0).getnomVille());
        assertEquals("Angers", villes.get(1).getnomVille());
    }

    @Test
    public void getVilleParNom() throws Exception {

        ListeVilles lv = new ListeVilles();

        Ville paris = lv.getVilleParNom("Paris");
        assertNotNull(paris);
        assertEquals("Paris", paris.getnomVille());

        assertNull(lv.getVilleParNom("Rambouillet"));
    }

    @Test
    public void getDistance() throws Exception {

        ListeVilles lv = new ListeVilles();

        assertEquals(369, lv.getDistance("Amiens", "Angers"));
        assertEquals(0, lv.getDistance("Lyon", "Lyon"));
        assertEquals(Integer.MAX_VALUE, lv.getDistance("Paris", "Rambouillet"));
    }

    @Test
    public void testToString() throws Exception {

        ListeVilles lv = new ListeVilles();

        String res = lv.toString();
        assertTrue(res.contains("Paris"));
        assertTrue(res.contains("Lyon"));
        assertTrue(res.contains("0"));
    }
}

