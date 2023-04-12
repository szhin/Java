package GraphWeight;

import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;

public abstract class Graph {
    protected int numVertices;
    protected double[][] graph;
    
    public Graph(int numVertices) {
        this.numVertices = numVertices;
        graph = new double[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                graph[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }

    ///////////////////////ABSTRACT////////////////////////
    public abstract void addEdge(int start, int end, double weight);
    public abstract void removeEdge(int start, int end);
    public abstract int degree(int v);
    public abstract double totalWeight();
    public abstract int totalEdge(double[][] graph);
    public abstract TreeSet<Integer> adjacentEdge(int v);
    public abstract void printListEdge();
    ///////////////////////ABSTRACT////////////////////////

    ////////////////////////METHOD/////////////////////////
    // This method to print the graph to console
    public void printGraph(double[][] graph) {
        for (int i = 0; i < numVertices; i++) {
            System.out.println();
            for (int j = 0; j < numVertices; j++) 
                if (graph[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.print(graph[i][j] + "  ");    
            
                } else {
                    System.out.print(graph[i][j] + "       ");    
                }
        }
        System.out.println();
    }

    // This method caculates the sum of the weights of the graph
    public double weightTotal() {
        double result = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                result += graph[i][j];
            }
        }
        return result;
    }

    // This method check has cycle between two vertex in graph
    public boolean hasCycleBetweenTwoVer(int start, int end) {
        boolean[] visited = new boolean[numVertices];
        visited[start] = true;
        // stack will contain the vertex being traversed
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int i = 0; i < numVertices; i++) {
                if (graph[current][i] != Double.POSITIVE_INFINITY) {
                    if (current == end) {
                        return true;
                    }
                    if (!visited[i]) {
                        visited[i] = true;
                        stack.push(i);
                    }
                }
            }
        }
        return false;
    }

    // This method check graph is connection
    public boolean isConnection() {
        boolean[] visited = new boolean[numVertices];
        visited[0] = true;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int i = 0; i < numVertices; i++) {
                if (graph[current][i] != Double.POSITIVE_INFINITY) {
                    if (!visited[i]) {
                        visited[i] = true;
                        stack.push(i);
                    }
                }
            }
        }
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
    ////////////////////////METHOD////////////////////////

    ////////////////////////MAIN//////////////////////////
    public static void main(String[] args) {
        Graph unDirGraph = new UnDirGraph(4);
        unDirGraph.addEdge(0, 1, 7);
        unDirGraph.addEdge(0, 2, 2);
        unDirGraph.addEdge(0, 3,1);
        unDirGraph.addEdge(1, 2, 5);
        unDirGraph.addEdge(1, 3, 2);
        unDirGraph.printGraph(unDirGraph.graph);

        // Graph directedGraph = new DirectedGraph(4);
        // directedGraph.addEdge(0, 1, 4);
        // directedGraph.addEdge(1, 2, 3);
        // directedGraph.addEdge(2, 0,2);
        // directedGraph.addEdge(3, 0, 5);
        // directedGraph.printGraph();
      
    }
}
