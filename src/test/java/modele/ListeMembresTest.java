package modele;
import modele.ListeMembres;
import modele.Membre;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ListeMembresTest {
    @Test
    public void constructeur() throws Exception {

        ListeMembres liste = new ListeMembres();

        ArrayList<Membre> membres = liste.getlisteDesMembres();
        assertEquals(384, membres.size());
        assertEquals("Bulbizarre", membres.get(0).getnomDuMembre());
        assertEquals("Brest", membres.get(0).getvilleDeResidence());
    }

    @Test
    public void testToString() throws Exception {

        ListeMembres liste = new ListeMembres();

        String result = liste.toString();
        assertTrue(result.contains("Herbizarre"));
        assertTrue(result.contains("Perpignan"));
    }

    @Test
    public void getVilleParNom() throws Exception {

        ListeMembres liste = new ListeMembres();

        assertEquals("Lille", liste.getVilleParNom("Salamèche"));

    }
}
