import java.util.ArrayList;
import java.util.Iterator;

public class UnDirectedGraph {
    private int v;
    private ArrayList<Integer>[] adj;
    private ArrayList<Integer> cycle;

    public UnDirectedGraph(int V) {
        this.v = V;
        adj = new ArrayList[V];
        this.cycle = new ArrayList<>();
		for (int i = 0; i < v; i++) {
			adj[i] = new ArrayList<>();
		}
       
    }

    public void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }
    private void removeEdge(int u, int v) {
        adj[u].remove(v);
        adj[v].remove(u);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void printEulerCycle() {
        for (int element : cycle) {
            System.out.print(element + " ");
        }
    }
 
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void DFSUtil(int v, boolean[] visited) {
        cycle.add(v);
        visited[v] = true;

        Iterator<Integer> item = adj[v].iterator();
        while(item.hasNext()) {
            int n = item.next();
            if(!visited[n])
                DFSUtil(n, visited);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean isConnected() {
        // Set False for all v
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) 
            visited[i] = false;

        // Create count i, start traversing from 0
        // If any vertice has an adjacent edge, break
        int i;
        for (i = 0; i < v; i++)
            if(adj[i].size() != 0)
                break;

        // If all v have no any edges -> euler 
        if (i == v)
            return true;

        // After break, DFSUtil is called
        DFSUtil(i, visited);

        for (i = 0; i < v; i++) 
            if (!visited[i] && adj[i].size() > 0)
                return false;
        
        return true;
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int isEulerian() {
        if(!isConnected()) 
            return 0;

        // Create variable count quanlity odd number
        int odd = 0;
        for (int i = 0; i < v; i++) 
            if (adj[i].size() % 2 == 1)
                odd++;

        // If odd > 2 then not Euler
        if (odd > 2)
            return 0;

        // If odd = 2 then semi-Euler, else this is euler cycle
        return odd == 2 ? 2 : 1;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Method check type graph (euler, semi-euler, not euler)
     */
    public void test() {
        int result = isEulerian();

        if (result == 0)
            System.out.println("graph is not Eulerian");
        else if (result == 1)
            System.out.println("graph has a Euler cycle");
        else
            System.out.println("graph has a semi-Eulerian graph");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Method check graph called is Euler cycle or not?
     */
    public void checkEulerCycle() {
        int result = isEulerian();
        if (result == 1) 
            System.out.println("1: Graph has a Euler cycle");
        else {
            System.out.println("1: Graph is not Euler cycle");
        }
    }

    /**
     * Method check graph called is semi-Eulerian graph or not?
     */
     public void checkSemiEuler() {
        int result = isEulerian();
        if (result == 2) 
            System.out.println("2: Graph has a semi-Eulerian graph");
        else {
            System.out.println("2: Graph is not semi-Eulerian graph");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        UnDirectedGraph g2 = new UnDirectedGraph(5);
        g2.addEdge(1, 0);
        g2.addEdge(0, 2);
        g2.addEdge(2, 1);
        g2.addEdge(0, 3);
        g2.addEdge(3, 4);
        g2.addEdge(4, 0);
 
        g2.checkEulerCycle();
        g2.checkSemiEuler();
        g2.printEulerCycle();
        
        // UnDirectedGraph g1 = new UnDirectedGraph(5);
        // g1.addEdge(1, 0);
        // g1.addEdge(0, 2);
        // g1.addEdge(2, 1);
        // g1.addEdge(0, 3);
        // g1.addEdge(3, 4);
        // g1.checkSemiEuler();
        // g1.test();
       
        // UnDirectedGraph g3 = new UnDirectedGraph(5);
        // g3.addEdge(1, 0);
        // g3.addEdge(0, 2);
        // g3.addEdge(2, 1);
        // g3.addEdge(0, 3);
        // g3.addEdge(3, 4);
        // g3.addEdge(1, 3);
        // g3.test();
    }
    
}