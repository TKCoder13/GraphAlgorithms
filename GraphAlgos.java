import java.util.ArrayList;

public class GraphAlgos {
    
    static String[] num2Strings = {"a","b","c","d","e","f","g","h"};

    static int[][] aMatrix = {
    //   a  b  c  d  e  f  g  h
        {0, 2, 0, 0, 0, 3, 0, 0}, /*a*/
        {2, 0, 5, 0, 4, 0, 3, 0}, /*b*/
        {0, 5, 0, 0, 2, 0, 0, 4}, /*c*/
        {0, 0, 0, 0, 2, 4, 2, 0}, /*d*/
        {0, 4, 2, 2, 0, 0, 0, 3}, /*e*/
        {3, 0, 0, 4, 0, 0, 1, 0}, /*f*/
        {0, 3, 0, 2, 0, 1, 0, 1}, /*g*/
        {0, 0, 4, 0, 3, 0, 1, 0}  /*h*/
    };

    static int[][] bMatrix = {
    //   a  b  c  d  e  f  g 
        {0, 2, 4, 6, 0, 0, 0}, /*a*/
        {2, 0, 2, 0, 6, 0, 0}, /*b*/
        {4, 2, 0, 1, 3, 0, 0}, /*c*/
        {6, 0, 1, 0, 2, 3, 0}, /*d*/
        {0, 6, 3, 2, 0, 0, 5}, /*e*/
        {0, 0, 0, 3, 0, 0, 4}, /*f*/
        {0, 0, 0, 0, 5, 4, 0}  /*g*/
    };

    static int[][] cMatrix = {
    //   a  b  c  d  e  f  g  h
        {0, 1, 0, 5, 0, 2, 0, 0}, /*a*/
        {1, 0, 0, 0, 2, 0, 0, 0}, /*b*/
        {0, 0, 0, 0, 4, 0, 0, 1}, /*c*/
        {5, 0, 0, 0, 3, 0, 2, 0}, /*d*/
        {0, 2, 4, 3, 0, 0, 2, 0}, /*e*/
        {2, 0, 0, 0, 0, 0, 4, 0}, /*f*/
        {0, 0, 0, 2, 2, 4, 0, 5}, /*g*/
        {0, 0, 1, 0, 0, 0, 5, 0}  /*h*/
    };

    static int[][] dMatrix = {
    //   a  b  c  d  e  f  g  h
        {0, 1, 0, 2, 0, 0, 0, 0}, /*a*/
        {1, 0, 2, 0, 3, 0, 0, 0}, /*b*/
        {0, 2, 0, 4, 0, 0, 0, 0}, /*c*/
        {2, 0, 4, 0, 2, 0, 0, 0}, /*d*/
        {0, 3, 0, 2, 0, 5, 0, 3}, /*e*/
        {0, 0, 0, 2, 5, 0, 4, 5}, /*f*/
        {0, 0, 0, 0, 0, 4, 0, 1}, /*g*/
        {0, 0, 0, 0, 3, 5, 1, 0}  /*h*/
    };

    

    public static void printStep(int oldIndex, int newIndex, int weight) {
        System.out.println(num2Strings[oldIndex] + " >> " + num2Strings[newIndex] + " weight: " + weight);
    }

    // -- Dijkstra-Prim Algorithm
    public static void MSTAlgorithm(int[][] matrix) {

        ArrayList<Integer> set = new ArrayList<Integer>();
        set.add(0);

        int[][] fringeSet = new int[2][matrix.length];

        // -- initializing the weights from starting node (A == 0)
        for (int i = 0; i < fringeSet[0].length; i++) {
            fringeSet[0][i] = matrix[0][i];
        }
        
        // -- initializing array that carries min. edge locations
        for (int i = 0; i < matrix[0].length; i++) {
            fringeSet[1][i] = 0;
        }

        // -- continues until set has connected to all nodes
        while (set.size() < matrix.length) {
            //  -- location of minimum weight value;
            int min = -1;

            for (int i = 0; i < fringeSet[0].length; i++) {
                if (min == -1) { // -- base case
                    if (fringeSet[0][i] > 0) { // -- make sure the weight value is valid
                        min = i;
                    }
                } else {
                    if (fringeSet[0][i] > 0 && fringeSet[0][i] < fringeSet[0][min]) { // -- checking if fringeSet[0][i] smaller than current min value
                        min = i;
                    }
                }
            }

            printStep(fringeSet[1][min], min, fringeSet[0][min]);

            //adding to the known set
            set.add(min);

            // -- need to update fringeSet (adding first)
            for (int i = 0; i < matrix[min].length; i++) {
                // -- adding to fringeSet
                if (matrix[min][i] != 0 && matrix[min][i] < fringeSet[0][i]) { // -- checking if fringeSet[min][i] weight smaller than current min weight
                    // -- update weight value
                    fringeSet[0][i] = matrix[min][i];
                    // -- update index value
                    fringeSet[1][i] = min;
                } else if (fringeSet[0][i] == 0) { // -- checking last node
                    // -- update weight value
                    fringeSet[0][i] = matrix[min][i];
                    // -- update index value
                    fringeSet[1][i] = min;
                }
            }

            // -- removing most recently connected node in the known set from the fringeSet
            for(int i = 0; i < set.size(); i++){
                fringeSet[0][(int)set.get(i)] = 0;
                fringeSet[1][(int)set.get(i)] = 0;
            }
        }
    }
    // -- Dijkstra's Shortest Path Algorithm
    // public static void SPAlgorithm(int[][] matrix) {

    // }

    public static void main(String[] args) {
        System.out.println("Minimum Spanning Trees\n");
        System.out.println("--- Graph A ---");
        MSTAlgorithm(aMatrix);
        System.out.println("--- Graph B ---");
        MSTAlgorithm(bMatrix);
        System.out.println("--- Graph C ---");
        MSTAlgorithm(cMatrix);
        System.out.println("--- Graph D ---");
        MSTAlgorithm(dMatrix);

        // System.out.println("Shortest Path Trees\n");
        // System.out.println("--- Graph A ---");
        // MSTAlgorithm(aMatrix);
        // System.out.println("--- Graph B ---");
        // MSTAlgorithm(bMatrix);
        // System.out.println("--- Graph C ---");
        // MSTAlgorithm(cMatrix);
        // System.out.println("--- Graph D ---");
        // MSTAlgorithm(dMatrix);
    }
}
