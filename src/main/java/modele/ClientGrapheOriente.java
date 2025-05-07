package modele;

public class ClientGrapheOriente {
    public static void main(String[] args) {
        int[][] table = {
                {3},     // 0 → 3
                {3},     // 1 → 3
                {0, 1},  // 2 → 0, 2 → 1
                {},      // 3 → rien
                {2}      // 4 → 2
        };



        GrapheOriente g = new GrapheOriente(table);
        System.out.println(g.triTopologique());
    }
}
