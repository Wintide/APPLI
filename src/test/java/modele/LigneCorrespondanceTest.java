package modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LigneCorrespondanceTest {

    @Test
    void testClasse() {
        // Données de test
        String vendeur = "Alice";
        String villeVendeur = "Paris";
        String acheteur = "Bob";
        String villeAcheteur = "Lyon";
        int distance = 465;

        // Création de l'objet LigneCorrespondance
        LigneCorrespondance ligne = new LigneCorrespondance(vendeur, villeVendeur, acheteur, villeAcheteur, distance);

        // Vérification des accesseurs
        assertEquals("Alice (Paris)", ligne.getVendeur());
        assertEquals("Bob (Lyon)", ligne.getAcheteur());
        assertEquals(465, ligne.getDistance());
    }
}
