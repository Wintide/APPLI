package modele;

import java.util.TreeSet;

public class ListeMembres {
    TreeSet<Membre> chMembres;
    public ListeMembres() {
        chMembres = new TreeSet<>();
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
}
