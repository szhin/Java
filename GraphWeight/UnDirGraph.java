package GraphWeight;

import java.util.ArrayList;
import java.util.TreeSet;

public class UnDirGraph extends Graph {

   public UnDirGraph(int numVertices) {
      super(numVertices);   
   }

   ///////////////////////ABSTRACT////////////////////////
   @Override
   public void addEdge(int start, int end, double weight) {
      graph[start][end] = weight;
      graph[end][start] = weight;
   }

   @Override
   public void removeEdge(int start, int end) {
      graph[start][end] = Double.POSITIVE_INFINITY;
      graph[end][start] = Double.POSITIVE_INFINITY;
   }

   @Override
   public int degree(int v) {
      int result = 0;
      for (int i = 0; i < numVertices; i++) {
            if (graph[v][i] != Double.POSITIVE_INFINITY 
            || graph[i][v] != Double.POSITIVE_INFINITY) {
               result++;
            }
      }
      return result;
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
      return result / 2;
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
      return result / 2;
   }
   
   // Phương thức trả về danh sách cạnh kề của một đỉnh v
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
   
   // Phương thức in tất cả các cạnh bao gồm đỉnh bắt đầu, đỉnh đến và trọng số
   @Override
   public void printListEdge() {
      System.out.println();
      for (int i = 0; i < numVertices; i++) {
         for (int j = 0; j < numVertices; j++) {
            if (graph[i][j] != Double.POSITIVE_INFINITY) {
               // print source, destination: diem den, weight
               System.out.println(i + "-" + j + ": " + "Source: " + i + ", destination: " + j + ", weight: " +  graph[i][j]);
            }
         }
      }
   }
   
   ///////////////////////ABSTRACT////////////////////////

   ////////////////////////METHOD/////////////////////////
   // Phương thức trả về cây bao trùm nhỏ nhất dựa trên thuật toán Kruskal
   public double[][] kruskalAlgorithm(double[][] graph) {
      double[][] result = new double[numVertices][numVertices];
      for (int i = 0; i < numVertices; i++) {
         for (int j = 0; j < numVertices; j++) {
            result[i][j] = Double.POSITIVE_INFINITY;
         }
      }
      // ArrayList<Double> print = new ArrayList<>();
      TreeSet<Double> treeSet = listEdgeWeightSorted();
      System.out.println(treeSet);
      while (totalEdge(result) < numVertices - 1) {
         double current = treeSet.first();
         for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
               if (graph[i][j] == current && !hasCycleBetweenTwoVer(i, j, result)) {
                  result[i][j] = current;
                  result[j][i] = current;
                  // print.add(result[i][j]);
                  break;
               } 
            }
         }
         treeSet.pollFirst();
      }
      // System.out.println();
      // System.out.println("List Weight: " + print);
      return result;
   }

   // Phương thức trả về cây bao trùm nhỏ nhất dựa trên thuật toán Prim
   public double[][] primAlgorithm(double[][] graph, int v) {
      // Mảng result laf mang 2 chiều của khung bao trùm
      double[][] result = new double[numVertices][numVertices];
      for (int i = 0; i < numVertices; i++) {
         for (int j = 0; j < numVertices; j++) {
            result[i][j] = Double.POSITIVE_INFINITY;
         }
      }
      // mảng visited sẽ thể hiện rằng đỉnh đã được duyệt qua hay chưa
      boolean[] visited = new boolean[numVertices];
      for (int i = 0; i < numVertices; i++) {
         visited[i] = false;
      }

      visited[v] = true;
      
      while (totalEdge(result) < numVertices - 1) {
         // vertexTrue sẽ lưu toàn bộ đỉnh đã được visited === true
         TreeSet<Integer> vertexTrue = new TreeSet<>();
         for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
               vertexTrue.add(i);
            }
         }

         // listAllAdjacent lưu toàn bộ đỉnh kề của các đỉnh có trong vertexTrue
         TreeSet<Integer> listAllAdjacent = new TreeSet<>();
         // foreach qua mọi phần tử có trong vertexTrue
         for (Integer element : vertexTrue) {
            // lấy ra danh sách kề của mỗi phần tử
            TreeSet<Integer> listElementAdjacent = adjacentEdge(element);
            // thêm tất cả các đỉnh kể của mỗi phần tử vào listAllAdjacent
            listAllAdjacent.addAll(listElementAdjacent);
         }

         // Chuyển TreeSet sang mảng Integer
         Integer[] arrVertexTrue = vertexTrue.toArray(new Integer[vertexTrue.size()]);
         Integer[] arrAllAdjacent = listAllAdjacent.toArray(new Integer[vertexTrue.size()]);
        
         // Lấy ra trọng số nhỏ nhất
         // Tham số 1 là tất cả các đỉnh visited === true 
         // Tham số 2 là tất cả các đỉnh kề của các đỉnh visited === true
         // 3, 4 là graph và visited
         double min = checkPrim(arrVertexTrue, arrAllAdjacent, graph, visited);    
      
         for (int i = 0; i < arrVertexTrue.length; i++) {
            for (int j = 0; j < arrAllAdjacent.length; j++) {  
               if (graph[arrVertexTrue[i]][arrAllAdjacent[j]] == min 
                  && !visited[arrAllAdjacent[j]]) {

                  result[arrVertexTrue[i]][arrAllAdjacent[j]] = min;
                  result[arrAllAdjacent[j]][arrVertexTrue[i]] = min;

                  // đánh dấu j đã được duyệt
                  visited[arrAllAdjacent[j]] = true;

               }
            }
         }  
      }
      return result;
   }

   // Phương thức trả về min trọng số của list đỉnh được duyệt qua
   public double checkPrim(Integer[] arrVertexTrue, Integer [] arrAllAdjacent, double[][] graph, boolean[] visited) {
      double min = Double.POSITIVE_INFINITY;
      for (int i = 0; i < arrVertexTrue.length; i++) {
         for (int j = 0; j < arrAllAdjacent.length; j++) {

            if (visited[arrAllAdjacent[j]] == false) {

               if (graph[arrVertexTrue[i]][arrAllAdjacent[j]] < min 
               && graph[arrVertexTrue[i]][arrAllAdjacent[j]] != Double.POSITIVE_INFINITY) {
               
                  min = graph[arrVertexTrue[i]][arrAllAdjacent[j]];
              
               }

            }
            
         }
      }
      return min;
   }

   // Phương thức sắp xếp trọng số từ nhỏ đến lớn
   public TreeSet<Double> listEdgeWeightSorted() {
      TreeSet<Double> treeSet = new TreeSet<Double>();
      for (int i = 0; i < numVertices; i++) {
         for (int j = 0; j < numVertices; j++) {
            if (graph[i][j] != Double.POSITIVE_INFINITY) {
               treeSet.add(graph[i][j]);
            }
         }
      }
      return treeSet;
   }
   ////////////////////////METHOD/////////////////////////

   ///////////////////////MAIN///////////////////////////
   public static void main(String[] args) {
      // UnDirGraph unDirGraph = new UnDirGraph(4);
      // unDirGraph.addEdge(0, 1, 7);
      // unDirGraph.addEdge(0, 2, 2);
      // unDirGraph.addEdge(0, 3,1);
      // unDirGraph.addEdge(1, 2, 5);
      // unDirGraph.addEdge(1, 3, 2);  

      UnDirGraph unDirGraph2 = new UnDirGraph(4);
      unDirGraph2.addEdge(0, 1, 2);
      unDirGraph2.addEdge(0, 3, 2);
      unDirGraph2.addEdge(3, 2,3);
      unDirGraph2.addEdge(1, 2, 6);
      unDirGraph2.addEdge(1, 3, 5);  
      // unDirGraph.printGraph();
      // System.out.println("Total edge of the graph: " + unDirGraph.totalEdge());
      // vertex in method degree(vertex)
      // int vertex = 0;
      // System.out.println("Degree of vertex " + vertex + ": " + unDirGraph.degree(vertex));
      // System.out.println("Total weight of the graph: " + unDirGraph.totalWeight());
      // vertex start and end in method hasCycleBetweenTwoVer(start, end)
      // int start = 2, end = 3;
      // System.out.println("Has cycle between " + start + " and " + end + ": " + unDirGraph.hasCycleBetweenTwoVer(start,end));     
      // System.out.println("Graph is connection: " + unDirGraph.isConnection());
      // System.out.println(unDirGraph.adjacentEdge(3));
      // unDirGraph.printListEdge();
      // System.out.println(unDirGraph.listEdgeWeightSorted());
   
      unDirGraph2.printGraph(unDirGraph2.kruskalAlgorithm(unDirGraph2.graph));
      // unDirGraph.primAlgorithm(unDirGraph.graph, 0);
      // unDirGraph2.printGraph(unDirGraph2.primAlgorithm(unDirGraph2.graph, 0));
   }
}
