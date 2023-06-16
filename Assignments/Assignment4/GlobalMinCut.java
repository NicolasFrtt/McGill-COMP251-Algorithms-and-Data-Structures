import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/****************************
  *
  * COMP251 template file
  *
  * Assignment 4
  *
  *****************************/

public class GlobalMinCut {
  
  static Random random;
  
  /**
   * One run of the contraction algorithm
   * Returns the min cut found
   *x
   * @param   graph - the graph to find min cut of
   * @return  two ArrayLists of characters (placed together in another ArrayList)
   *          representing the partitions of the cut
   */
  public static ArrayList<ArrayList<Character>> global_min_cut(Graph graph) {
    ArrayList<ArrayList<Character>> cut = new ArrayList<ArrayList<Character>>();
    
    // For each node v, we will record
    // the list S(v) of nodes that have been contracted into v
    Map<Character, ArrayList<Character>> S = new HashMap<Character, ArrayList<Character>>();
    
    
    // TODO: Initialize S(v) = {v} for each v
    
    //for each node v, add v as a key and v as an element of the ArrayList corresponding
    for(Character v : graph.getNodes()){
      ArrayList<Character> temp = new ArrayList<Character>();
      temp.add(v);
      S.put(v, temp);
    }
    
    
    while (graph.getNbNodes() > 2) {
      
      // select an edge randomly (DO NOT CHANGE THIS LINE)
      Edge edge = graph.getEdge(random.nextInt(graph.getNbEdges()));
      
      // TODO: fill in the rest
      
      //contract the edge
      graph.contractEdge(edge);
      
      
      //shortcuts
      char u = edge.node_1; //u is node_1 in our implementation of contractEdge
      char v = edge.node_2; //v is node_2 in our implementation of contractEdge
      ArrayList<Character> uTempAL = S.get(u); 
      ArrayList<Character> vTempAL = S.get(v);
      //go through S(u)
      for(int i = 0; i < uTempAL.size(); i++){
        vTempAL.add(uTempAL.get(i)); //add every node in S(u) to S(v)-temp
      }
      //update S(v)
      S.put(v, vTempAL);
      
    }
    
    // TODO: assemble the output object
    
    ArrayList<Character> tempNodesAL = graph.getNodes();
    char node1 = tempNodesAL.get(0);
    char node2 = tempNodesAL.get(1);
    cut.add(S.get(node1));
    cut.add(S.get(node2));
    
    return cut;
  }
  
  
  /**
   * repeatedly calls global_min_cut until finds min cut or exceeds the max allowed number of iterations
   *
   * @param graph         the graph to find min cut of
   * @param r             a Random object, don't worry about this, we only use it for grading so we can use seeds
   * @param maxIterations some large number of iterations, you expect the algorithm to have found the min cut
   *                      before then (with high probability), used as a sanity check / to avoid infinite loop
   * @return              two lists of nodes representing the cut
   */
  public static ArrayList<ArrayList<Character>> global_min_cut_repeated(Graph graph, Random r, int maxIterations) {
    random = (r != null) ? r : new Random();
    
    ArrayList<ArrayList<Character>> cut = new ArrayList<ArrayList<Character>>();
    int count = 0;
    do  {
      
      // TODO: use global_min_cut()
      
      //copy the graph
      Graph graphCopy = new Graph(graph);
      //remove seld-edges
      ArrayList<Edge> edgestoRemoveAL = new ArrayList<Edge>();
      for(Edge e : graphCopy.getEdges()){
        if(e.node_1 == e.node_2){
          edgestoRemoveAL.add(e);
        }
      }
      graphCopy.getEdges().removeAll(edgestoRemoveAL);
      
      //compute one cut
      cut = global_min_cut(graphCopy);
      
      
      ++ count;
      
      if (get_cut_size(graph, cut) > graph.getExpectedMinCutSize())
          System.out.println("Cut has " + get_cut_size(graph, cut) + " edges but the min cut should have " +
            graph.getExpectedMinCutSize() + ", restarting");
      
    } while (get_cut_size(graph, cut) > graph.getExpectedMinCutSize() && count < maxIterations);
    
    if (count >= maxIterations) System.out.println("Forced stop after " + maxIterations + " iterations... did something go wrong?");
    return cut;
    
  }
  
  /**
   * @param graph  the original unchanged graph
   * @param cut    the partition of graph into two lists of nodes
   * @return       the number of edges between the partitions
   */
  public static int get_cut_size(Graph graph, ArrayList<ArrayList<Character>> cut) {
    ArrayList<Edge> edges = graph.getEdges();
    int cut_size = 0;
    for (int i = 0; i < edges.size(); ++i) {
      Edge edge = edges.get(i);
      if (cut.get(0).contains(edge.node_1) && cut.get(1).contains(edge.node_2) ||
          cut.get(0).contains(edge.node_2) && cut.get(1).contains(edge.node_1)) {
        ++cut_size;
      }
    }
    return cut_size;
  }
}
