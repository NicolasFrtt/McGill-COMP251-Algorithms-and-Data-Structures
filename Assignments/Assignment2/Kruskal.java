import java.util.*;

public class Kruskal{
  
  public static WGraph kruskal(WGraph g){
    
    /* Fill this method (The statement return null is here only to compile) */
    
    //create the DisjointSets and the WGraph
    DisjointSets DS = new DisjointSets(g.getNbNodes());
    WGraph MST = new WGraph();
    
    //get the sorted list of edges
    ArrayList<Edge> edgesList = g.listOfEdgesSorted();
    
    //while the MST is not complete
    for(int i = 0; MST.getNbNodes() != g.getNbNodes() && i < edgesList.size(); i++){
      //check if the edge is safe
      Edge e = edgesList.get(i);
      if(IsSafe(DS, e)){
        MST.addEdge(e); //add it to the MST
        DS.union(e.nodes[0], e.nodes[1]); //update the DisjointSets
      }
    }
    //here the MST is complete
    return MST;
  }
  
  public static Boolean IsSafe(DisjointSets p, Edge e){
    
    /* Fill this method (The statement return 0 is here only to compile) */
    
    if(p.find(e.nodes[0]) == p.find(e.nodes[1])){ //if the roots (inside the disjoint sets structure) of the two linked nodes are the same
      return false;
    }
    
    else{
      return true;
    }
    
  }
  
  public static void main(String[] args){
    
    String file = args[0];
    WGraph g = new WGraph(file);
    WGraph t = kruskal(g);
    System.out.println(t);
    
  } 
}
