import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

public class GraphAlgorithms {
    
    
    // -- graph as an adjacency list
    int[][] aList = {
        {2, 4},
        {1, 5, 7},
        {4, 6, 7},
        {1, 3, 5},
        {2, 4, 6, 7},
        {3, 5},
        {2, 3, 5}
    };

    int[][] bList = {
        {4, 5},
        {3, 4},
        {2, 4},
        {1, 2, 3, 7},
        {1, 6},
        {5, 7},
        {4, 6}
    };

    int[][] cList = {
        {2, 4, 5},
        {3, 4, 7},
        {4},
        {6, 7},
        {4},
        {5},
        {6}
    };

    int[][] dList = {
        {2, 4, 5},
        {3},
        {7},
        {2, 3, 6, 7},
        {4},
        {7},
        {}
    };

    public int[][] a_adjacencyMatrix = {
       /*1  2  3  4  5  6  7 */
        {0, 1, 0, 1, 0, 0, 0},/*1*/
        {1, 0, 0, 0, 1, 0, 1},/*2*/
        {0, 0, 0, 1, 0, 1, 1},/*3*/
        {1, 0, 1, 0, 1, 0, 0},/*4*/
        {0, 1, 0, 1, 0, 1, 1},/*5*/
        {0, 0, 1, 0, 1, 0, 0},/*6*/
        {0, 1, 1, 0, 1, 0, 0} /*7*/
    };

    public int[][] b_adjacencyMatrix = {
       /*1  2  3  4  5  6  7 */
        {0, 0, 0, 1, 1, 0, 0},/*1*/
        {0, 0, 1, 1, 0, 0, 0},/*2*/
        {0, 1, 0, 1, 0, 0, 0},/*3*/
        {1, 1, 1, 0, 0, 0, 1},/*4*/
        {1, 0, 0, 0, 0, 1, 0},/*5*/
        {0, 0, 0, 0, 1, 0, 1},/*6*/
        {0, 0, 0, 1, 0, 1, 0} /*7*/
    };

    public int[][] c_adjacencyMatrix = { // -- directional
        /*1  2  3  4  5  6  7 */
         {0, 1, 0, 1, 1, 0, 0},/*1*/
         {0, 0, 1, 1, 0, 0, 1},/*2*/
         {0, 0, 0, 1, 0, 0, 0},/*3*/
         {0, 0, 0, 0, 0, 1, 1},/*4*/
         {0, 0, 0, 1, 0, 0, 0},/*5*/
         {0, 0, 0, 0, 1, 0, 0},/*6*/
         {0, 0, 0, 0, 0, 1, 0} /*7*/
     };

    public int[][] d_adjacencyMatrix = { // -- directional
        /*1  2  3  4  5  6  7 */
         {0, 1, 0, 1, 1, 0, 0},/*1*/
         {0, 0, 1, 0, 0, 0, 0},/*2*/
         {0, 0, 0, 0, 0, 0, 1},/*3*/
         {0, 1, 1, 0, 0, 1, 1},/*4*/
         {0, 0, 0, 1, 0, 0, 0},/*5*/
         {0, 0, 0, 0, 0, 0, 1},/*6*/
         {0, 0, 0, 0, 0, 0, 0} /*7*/
    };


    public int[][] list2Matrix(int[][] list) {
        int[][] array = {
            /*1  2  3  4  5  6  7 */
             {0, 0, 0, 0, 0, 0, 0},/*1*/
             {0, 0, 0, 0, 0, 0, 0},/*2*/
             {0, 0, 0, 0, 0, 0, 0},/*3*/
             {0, 0, 0, 0, 0, 0, 0},/*4*/
             {0, 0, 0, 0, 0, 0, 0},/*5*/
             {0, 0, 0, 0, 0, 0, 0},/*6*/
             {0, 0, 0, 0, 0, 0, 0} /*7*/
         };
        
        for (int row = 0; row < list.length; row++) {
            for (int i = 0; i < list[row].length; i++) {
                array[row][list[row][i]-1] = 1;
            }
        }
        return array;
    }

    // -- keep track of nodes that have been visited
    public int[] marks = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    LinkedList<Integer> queue = new LinkedList<Integer>();

    // -- visitation is just printing the node being visited
    public void visit(int v) {
        System.out.print((v+1));
    }

    // -- mark node v as visited
    public void mark(int v) {
        marks[v] = 1;
    }

    // -- this is for an adjacency matrix, if you have an adj list, pass that in instead
    // -- perform the Depth First Traversal
    public void DepthFirstTraversal(int[][] graph, int v) {
        // -- visit node v
        visit(v);
        // -- visit node v as visited
        mark(v);
        // -- for all edges vw in graph
        for (int w = 0; w < graph[v].length; w++) {
        // -- this processes largest node first
        // -- for (int w = graph[v].length - 1; w >= 0; w--) {} 

            // check if an edge exists at (v,w)
            if (graph[v][w] == 1) {
                // -- if w is not marked
                if (marks[w] == 0) {
                    // -- recursive call from node w
                    DepthFirstTraversal(graph, w);
                }
            }
        }
    }

    public void enqueue(int v) { 
        queue.addLast(v);
    }

    public int dequeue() { 
        return queue.pop();
    }

    public void reset() {
        for (int i = 0; i < marks.length; i++) {
            marks[i] = 0;
        }
    }

    public void BreadthFirstTraversal(int[][] graph, int v) {
        visit(v);
        mark(v);
        enqueue(v);
        while (queue.peek() != null) {
            int popped = dequeue();
            for (int w = 0; w < graph.length; w++) {
                if (marks[w] == 0 && graph[popped][w] > 0) {
                    visit(w);
                    mark(w);
                    enqueue(w);
                }
            }
        }
    }

    public static void main(String args[]) {
        GraphAlgorithms traversal = new GraphAlgorithms();
        
        System.out.println("Depth First Traversals (Adjacency Matrix):");
        System.out.print("Graph A: ");
        traversal.DepthFirstTraversal(traversal.a_adjacencyMatrix, 0);
        traversal.reset();
        System.out.print("\nGraph B: ");
        traversal.DepthFirstTraversal(traversal.b_adjacencyMatrix, 0);
        traversal.reset();
        System.out.print("\nGraph C: ");
        traversal.DepthFirstTraversal(traversal.c_adjacencyMatrix, 0);
        traversal.reset();
        System.out.print("\nGraph D: ");
        traversal.DepthFirstTraversal(traversal.d_adjacencyMatrix, 0);
        traversal.reset();

        System.out.println("\nBreadth First Traversals (Adjacency Matrix):");
        System.out.print("Graph A: ");
        traversal.BreadthFirstTraversal(traversal.a_adjacencyMatrix, 0);
        traversal.reset();
        System.out.print("\nGraph B: ");
        traversal.BreadthFirstTraversal(traversal.b_adjacencyMatrix, 0);
        traversal.reset();
        System.out.print("\nGraph C: ");
        traversal.BreadthFirstTraversal(traversal.c_adjacencyMatrix, 0);
        traversal.reset();
        System.out.print("\nGraph D: ");
        traversal.BreadthFirstTraversal(traversal.d_adjacencyMatrix, 0);
        traversal.reset();
        
        // -- conversion from Lists to Matrices
        int[][] aMat = traversal.list2Matrix(traversal.aList);
        int[][] bMat = traversal.list2Matrix(traversal.bList);
        int[][] cMat = traversal.list2Matrix(traversal.cList);
        int[][] dMat = traversal.list2Matrix(traversal.dList);

        System.out.println("\n\nDepth First Traversals (Adjacency List):");
        System.out.print("Graph A: ");
        traversal.DepthFirstTraversal(aMat, 0);
        traversal.reset();
        System.out.print("\nGraph B: ");
        traversal.DepthFirstTraversal(bMat, 0);
        traversal.reset();
        System.out.print("\nGraph C: ");
        traversal.DepthFirstTraversal(cMat, 0);
        traversal.reset();
        System.out.print("\nGraph D: ");
        traversal.DepthFirstTraversal(dMat, 0);
        traversal.reset();

        System.out.println("\nBreadth First Traversals (Adjacency List):");
        System.out.print("Graph A: ");
        traversal.BreadthFirstTraversal(aMat, 0);
        traversal.reset();
        System.out.print("\nGraph B: ");
        traversal.BreadthFirstTraversal(bMat, 0);
        traversal.reset();
        System.out.print("\nGraph C: ");
        traversal.BreadthFirstTraversal(cMat, 0);
        traversal.reset();
        System.out.print("\nGraph D: ");
        traversal.BreadthFirstTraversal(dMat, 0);
        traversal.reset();
    }
}