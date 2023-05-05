import java.util.ArrayList;

public class GraphAlgos {
    
    static String[] num2Strings = {"a","b","c","d","e","f","g","h"};

    // -- counting the amount of Edges visited
    private static int count = 0;

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
        System.out.println(num2Strings[oldIndex] + " >> " + num2Strings[newIndex] + " | weight: " + weight);
    }

    // -- Dijkstra-Prim Algorithm
    public static void MSTAlgorithm(int[][] graph) {

        ArrayList<Integer> set = new ArrayList<Integer>();
        set.add(0);

        int[][] fringeSet = new int[2][graph.length];

        // -- initializing the weights from starting node (A == 0)
        for (int i = 0; i < fringeSet[0].length; i++) {
            fringeSet[0][i] = graph[0][i];
        }
        
        // -- initializing array that carries min. edge locations
        for (int i = 0; i < graph[0].length; i++) {
            fringeSet[1][i] = 0;
        }

        // -- continues until set has connected to all nodes
        while (set.size() < graph.length) {
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
            for (int i = 0; i < graph[min].length; i++) {
                // -- adding to fringeSet
                if (graph[min][i] != 0 && graph[min][i] < fringeSet[0][i]) { // -- checking if fringeSet[min][i] weight smaller than current min weight
                    // -- update weight value
                    fringeSet[0][i] = graph[min][i];
                    // -- update index value
                    fringeSet[1][i] = min;
                } else if (fringeSet[0][i] == 0) { // -- checking last node
                    // -- update weight value
                    fringeSet[0][i] = graph[min][i];
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

    public static void addCount() {
        count++;
    }

    public static void resetCount() {
        count = 0;
    }

    public static void updateSPFringe(int[][] graph, int[] shortestPath, int iNode, int oldNode, ArrayList<Integer> fringe, ArrayList<Integer> fringeWeights, ArrayList<Integer> fringeConnectingNodes) {
        int nextWeight;
        int iIndex;

        for (int i = 0; i < graph.length; i++) {
            if (i != iNode || iNode == oldNode) { // -- gets rid of iNode after first passthrough
                if (graph[oldNode][i] > 0) { // -- for each new node
                    if (shortestPath[i] == 0) { // -- check if it's in the shortestPath or not
                        
                        nextWeight = shortestPath[oldNode] + graph[oldNode][i];

                        if (!fringe.contains(i)) { // -- if it doesn't exist in the fringe, add i into the three fringe ArrayLists
                            fringe.add(i);
                            fringeWeights.add(nextWeight);
                            fringeConnectingNodes.add(oldNode);
                        } else { // -- this means that it already is in the fringe
                            // -- update the fringe
                            iIndex = fringe.indexOf(i);

                            if (nextWeight < fringeWeights.get(iIndex)) {
                                fringeWeights.set(iIndex, nextWeight);
                                fringeConnectingNodes.set(iIndex, oldNode);
                            }
                        }
                    }
                }
            }
        }
    }

    // -- Dijkstra's Shortest Path Algorithm
    public static void SPAlgorithm(int[][] graph) {
        resetCount();
        int SPFringe;
        int oldNode;
        int oldIndex;

        int[] shortestPath = new int[graph.length];
        int[] shortestConnecting = new int[graph.length];

        ArrayList<Integer> fringe = new ArrayList<Integer>();
        ArrayList<Integer> fringeWeights = new ArrayList<Integer>();
        ArrayList<Integer> fringeConnectingNodes = new ArrayList<Integer>();

        // -- initializing fringe from Node A
        updateSPFringe(graph, shortestPath, 0, 0, fringe, fringeWeights, fringeConnectingNodes);

        // -- continues until the destination is reached
        while(!fringe.isEmpty()) {
            
            // -- choose fringe node with "shortest path"
            addCount();
            SPFringe = fringeWeights.get(0);
            // -- NOTE: this cannot handle len = 0
            for (int i = 1; i < fringeWeights.size(); i++) {
                addCount();
                if (fringeWeights.get(i) < SPFringe) {
                    addCount();
                    SPFringe = fringeWeights.get(i);
                }
            }

            oldIndex = fringeWeights.indexOf(SPFringe);
            oldNode = fringe.get(oldIndex);

            // -- add the node to SP
            shortestPath[oldNode] = SPFringe;
            addCount();
            shortestConnecting[oldNode] = fringeConnectingNodes.get(oldIndex);

            // -- remove from fringe
            fringe.remove(oldIndex);
            fringeWeights.remove(oldIndex);
            fringeConnectingNodes.remove(oldIndex);

            // -- next round >:)
            updateSPFringe(graph, shortestPath, 0, oldNode, fringe, fringeWeights, fringeConnectingNodes);
        }

        // -- print the final solution
        for (int i = 0; i < shortestPath.length; i++) {
            if (i != 0) {
                System.out.println(num2Strings[i] + " >> " + num2Strings[shortestConnecting[i]] + " | length: " + shortestPath[i]);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Minimum Spanning Trees\n");
        System.out.println("----- Graph A -----");
        MSTAlgorithm(aMatrix);
        System.out.println("----- Graph B -----");
        MSTAlgorithm(bMatrix);
        System.out.println("----- Graph C -----");
        MSTAlgorithm(cMatrix);
        System.out.println("----- Graph D -----");
        MSTAlgorithm(dMatrix);

        System.out.println("\nShortest Path Trees\n");
        System.out.println("----- Graph A -----");
        SPAlgorithm(aMatrix);
        System.out.println("Count of Visits: " + count);
        System.out.println("----- Graph B -----");
        SPAlgorithm(bMatrix);
        System.out.println("Count of Visits: " + count);
        System.out.println("----- Graph C -----");
        SPAlgorithm(cMatrix);
        System.out.println("Count of Visits: " + count);
        System.out.println("----- Graph D -----");
        SPAlgorithm(dMatrix);
        System.out.println("Count of Visits: " + count);
    }
}
