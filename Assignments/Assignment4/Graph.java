import java.io.*;
import java.util.*;

/****************************
  *
  * COMP251 template file
  *
  * Assignment 4
  *
  *****************************/

class Edge {
  
  public char node_1;
  public char node_2;
  
  Edge(char u, char v) {
    this.node_1 = u;
    this.node_2 = v;
  }
  
  @Override
  public String toString() {
    return "(" + node_1 + ", " + node_2 + ")";
  }
}

public class Graph {
  
  private ArrayList<Edge> edges = new ArrayList<Edge>();
  private ArrayList<Character> nodes = new ArrayList<Character>();
  private int expectedMinCutSize;
  
  // copy constructor
  Graph(Graph other_graph) {
    expectedMinCutSize = other_graph.expectedMinCutSize;
    edges = new ArrayList<Edge>();
    nodes = new ArrayList<Character>();
    for (int i = 0; i < other_graph.edges.size(); ++i) {
      Edge other_edge = other_graph.edges.get(i);
      char u = other_edge.node_1;
      char v = other_edge.node_2;
      Edge edge = new Edge(u, v);
      edges.add(edge);
      add_node(u);
      add_node(v);
    }
  }
  
  // constructor from file. called by the tester
  Graph(String file) throws RuntimeException {
    try {
      Scanner f = new Scanner(new File(file));
      expectedMinCutSize = Integer.parseInt(f.nextLine()); // First line is the size of the min cut
      while (f.hasNext()) {
        String[] line = f.nextLine().split("\\s+");
        // Make sure there is 2 elements on the line
        if (line.length != 2) {
          continue;
        }
        
        char u = line[0].charAt(0);
        char v = line[1].charAt(0);
        Edge e = new Edge(u, v);
        edges.add(e);
        add_node(e.node_1);
        add_node(e.node_2);
      }
      f.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("File not found!");
      System.exit(1);
    }
  }
  
  // contract node u into node v
  public void contractEdge(Edge edge_to_contract) {
    char u = edge_to_contract.node_1;
    char v = edge_to_contract.node_2;
    
    // TODO: implement me
    
    
    //remove edges (u,v) and (v,u)
    Edge uv = new Edge(u,v);
    Edge vu = new Edge(v,u);
    this.edges.remove(uv);
    this.edges.remove(vu);
    
    //shortcut to avoid ConcurrentModificationException
    ArrayList<Edge> edgestoRemoveAL = new ArrayList<Edge>();
    ArrayList<Edge> edgestoAddAL = new ArrayList<Edge>();
    
    //loop through the edges
    for(Edge e : this.edges){
      
      //in case there are multiple edges from u to v or from v to u, delete them
      if((e.node_1 == u && e.node_2 == v) || (e.node_2 == u && e.node_1 == v)){
        edgestoRemoveAL.add(e); //used as a remove
      }
      
      //replace u by v
      //case u is node_1
      else if(e.node_1 == u){
        Edge temp = new Edge(v, e.node_2);
        edgestoRemoveAL.add(e); //used as a remove
        edgestoAddAL.add(temp); //used as an add
      }
      //case u is node_2
      else if(e.node_2 == u){
        Edge temp = new Edge(e.node_1, v);
        edgestoRemoveAL.add(e); //used as a remove
        edgestoAddAL.add(temp); //used as an add
      }
    }
    //remove edges thanks to edgestoRemoveAL
    this.edges.removeAll(edgestoRemoveAL);
    //add edges thanks toedgestoAddAL
    this.edges.addAll(edgestoAddAL);
    
    //remove node u
    this.removeNode(u);
  }
  
  public void removeNode(char toRemove) {
    nodes.remove((Character)toRemove);
  }
  
  public ArrayList<Edge> getEdges() {
    return edges;
  }
  
  public ArrayList<Character> getNodes() {
    return nodes;
  }
  
  public void setEdges(ArrayList<Edge> edges) {
    this.edges = edges;
  }
  
    public void setNodes(ArrayList<Character> nodes) {
      this.nodes = nodes;
    }
    
    public Edge getEdge(int edge_idx) {
      return edges.get(edge_idx);
    }
    
    public int getNbNodes() {
      return nodes.size();
    }
    
    public int getNbEdges() {
      return edges.size();
    }
    
    public String toString() {
      String out = Integer.toString(nodes.size());
      for (Edge e : edges) {
        out += "\n" + e.node_1 + " " + e.node_2;
      }
      return out;
    }
    
    public void add_node(char n) {
      if (!nodes.contains(n)) nodes.add(n);
    }
    
    public int getExpectedMinCutSize() {
      return expectedMinCutSize;
    }
    
    public void setExpectedMinCutSize(int expectedMinCutSize) {
      this.expectedMinCutSize = expectedMinCutSize;
    }
}
