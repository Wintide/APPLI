package modele;

import java.util.ArrayList;
import java.util.TreeSet;

public class ListeMembres {
    private ArrayList<Membre> chMembres;
    public ListeMembres() {
        chMembres = new ArrayList<Membre>();
    }

    public void addMembre(Membre m) {
        chMembres.add(m);
    }

    public String toString() {
        String retour = new String();
        for (Membre m : chMembres) {
            retour += m.toString();
        }
        return retour;
    }

    public ArrayList<Membre> getChMembres() {
        return chMembres;
    }
}
