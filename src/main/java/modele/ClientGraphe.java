package modele;

public class ClientGraphe {
    public static void main(String[] args) {
        int [] [] TabVoisins = {
                {1,2,4},
                {0,2},
                {0,1,3,4},
                {2,4},
                {0,2,3}
        };

        Graphe g = new Graphe(TabVoisins);
        System.out.println(g);
        System.out.println(g.getSommets());
        System.out.println(g.getOrdre());
        System.out.println(g.getDegreMin());
        System.out.println(g.getDegreMax());
        System.out.println(g.taille());

        System.out.println(g.parcoursEnLargeur(1));


    }
}
