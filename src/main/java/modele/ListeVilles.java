package modele;

import java.util.ArrayList;

public class ListeVilles {
    private ArrayList<Ville> chVilles;

    public ListeVilles() {
        chVilles = new ArrayList<>();
    }

    public ArrayList<Ville> getChVilles() {
        return chVilles;
    }

    public void ajoutVilles(Ville ville) {
        chVilles.add(ville);
    }

    @Override
    public String toString() {
        String res = "";
        for (Ville ville : chVilles) {
            res += ville.toString() + "\n";
        }
        return res;
    }

    public Ville getVilleParNom(String nom) {
        for (Ville ville : chVilles) {
            if (ville.getChNomVille().equals(nom)) {
                return ville;
            }
        }
        return null; // ou lève une exception si nécessaire
    }

}
