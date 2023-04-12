package GraphWeight;

import java.util.TreeSet;

public class DirectedGraph extends Graph {

    public DirectedGraph(int numVertices) {
        super(numVertices);
    }

    ///////////////////////ABSTRACT////////////////////////
    @Override
    public void addEdge(int start, int end, double weight) {
       graph[start][end] = weight;
    }

    @Override
    public void removeEdge(int start, int end) {
       graph[start][end] = Double.POSITIVE_INFINITY;
    }

    @Override
    public int degree(int v) {
        return inDegree(v) + outDegree(v);
    }

    @Override
    public double totalWeight() {
        double result = 0;
        for (double[] ds : graph) {
            for (double d : ds) {
                if (d != Double.POSITIVE_INFINITY) {
                    result += d;
                }
            }
        }
        return result;
    }

    @Override
    public int totalEdge(double[][] graph) {
        int result = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (graph[i][j] != Double.POSITIVE_INFINITY) {
                    result++;
                }
            }
        }
        return result;
    }

    @Override
    public TreeSet<Integer> adjacentEdge(int v) {
        TreeSet<Integer> list = new TreeSet<Integer>();
        for (int i = 0; i < numVertices; i++) {
            if (graph[v][i] != Double.POSITIVE_INFINITY) {
                list.add(i);
            }
        }
      return list;
    }

    @Override
    public void printListEdge() {
        System.out.println("List edge of the graph: ");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (graph[i][j] != Double.POSITIVE_INFINITY) {
               System.out.println(i + "-" + j + ": " + "First vertex: " + i + ", last vertex: " + j + ", weight: " +  graph[i][j]);
                }
            }
        }
    }
    ///////////////////////ABSTRACT///////////////////////

    ////////////////////////METHOD////////////////////////
    // Caculates the inner half of the vertex v
    public int inDegree(int v) {
        int result = 0;
        for (int i = 0; i < graph.length; i++) {
            if (graph[i][v] != Double.POSITIVE_INFINITY) {
                result++;
            }
        }
        return result;
    }

    // Caculates the outer half of the vertex v
    public int outDegree(int v) {
        int result = 0;
        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] != Double.POSITIVE_INFINITY) {
                result++;
            }
        }
        return result;
    }
    ////////////////////////METHOD////////////////////////

    ///////////////////////MAIN///////////////////////////
    public static void main(String[] args) {
        DirectedGraph directedGraph = new DirectedGraph(4);
        directedGraph.addEdge(0, 1, 4);
        directedGraph.addEdge(1, 2, 3);
        directedGraph.addEdge(2, 0,2);
        directedGraph.addEdge(3, 0, 5);
        // directedGraph.printGraph(directedGraph.graph);
        // System.out.println("Total edge of the graph: " + directedGraph.totalEdge());
        // vertex in method degree(vertex)
        // int vertex = 0;
        // System.out.println("Degree of vertex " + vertex + ": " + directedGraph.degree(vertex));
        // System.out.println("Total weight of the graph: " + directedGraph.totalWeight());
        // vertex start and end in method hasCycleBetweenTwoVer(start, end)
        // int start = 2, end = 3;
        // System.out.println("Has cycle between " + start + " and " + end + ": " + directedGraph.hasCycleBetweenTwoVer(start, end));
        // System.out.println("Graph is connection: " + directedGraph.isConnection());
        // directedGraph.printListEdge();
    }
}
