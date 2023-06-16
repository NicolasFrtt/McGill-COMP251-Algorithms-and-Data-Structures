public class BellmanFord{
  
  
  /**
   * Utility class. Don't use.
   */
  public class BellmanFordException extends Exception{
    private static final long serialVersionUID = -4302041380938489291L;
    public BellmanFordException() {super();}
    public BellmanFordException(String message) {
      super(message);
    }
  }
  
  /**
   * Custom exception class for BellmanFord algorithm
   * 
   * Use this to specify a negative cycle has been found 
   */
  public class NegativeWeightException extends BellmanFordException{
    private static final long serialVersionUID = -7144618211100573822L;
    public NegativeWeightException() {super();}
    public NegativeWeightException(String message) {
      super(message);
    }
  }
  
  /**
   * Custom exception class for BellmanFord algorithm
   *
   * Use this to specify that a path does not exist
   */
  public class PathDoesNotExistException extends BellmanFordException{
    private static final long serialVersionUID = 547323414762935276L;
    public PathDoesNotExistException() { super();} 
    public PathDoesNotExistException(String message) {
      super(message);
    }
  }
  
  private int[] distances = null;
  private int[] predecessors = null;
  private int source;
  
  BellmanFord(WGraph g, int source) throws BellmanFordException{
    /* Constructor, input a graph and a source
     * Computes the Bellman Ford algorithm to populate the
     * attributes 
     *  distances - at position "n" the distance of node "n" to the source is kept
     *  predecessors - at position "n" the predecessor of node "n" on the path
     *                 to the source is kept
     *  source - the source node
     *
     *  If the node is not reachable from the source, the
     *  distance value must be Integer.MAX_VALUE
     *  
     *  When throwing an exception, choose an appropriate one from the ones given above
     */
    
    /* YOUR CODE GOES HERE */
    
    
    //populate the source attribute
    this.source = source;
    
    //some shortcuts
    int nbNodes = g.getNbNodes();
    
    //create arrays for the other attributes
    this.distances = new int[nbNodes];// automatically initialized to 0
    this.predecessors = new int[nbNodes];
    
    for(int i = 0; i < nbNodes; i++){
      this.distances[i] = Integer.MAX_VALUE; //used as infinity
      this.predecessors[i] = -1;// use -1 as NIL
    }
    this.distances[source] = 0;
    
    //iterate n-1 times
    for(int i = 1; i < nbNodes; i++){
      
      //for every edge
      for(Edge e : g.getEdges()){
        int node1 = e.nodes[0];
        int node2 = e.nodes[1];
        
        //otherwise we cannot relax an edge that has infinity as first node
        if(distances[node1] != Integer.MAX_VALUE){
          
          //relax the edge
          if(e.weight + this.distances[node1] < this.distances[node2]){
            //update distance
            this.distances[node2] = e.weight + this.distances[node1];
            //update predecessor
            this.predecessors[node2] = node1;
          }
        }
      }
    }
    
    //then check if negative cycles
    for(Edge e : g.getEdges()){
      int node1 = e.nodes[0];
      int node2 = e.nodes[1];
      
      if(distances[node1] != Integer.MAX_VALUE){
        if(e.weight + this.distances[node1] < this.distances[node2]){
          throw new NegativeWeightException("Your graph contains a negative cycle! ");
        }
      }
    }
  }
  
  public int[] shortestPath(int destination) throws BellmanFordException{
    /*Returns the list of nodes along the shortest path from 
     * the object source to the input destination
     * If not path exists an Exception is thrown
     * Choose appropriate Exception from the ones given 
     */
    
    /* YOUR CODE GOES HERE (update the return statement as well!) */
    
    int nbNodes = this.distances.length;
    int[] reverseArray = new int[nbNodes];// will store in reverse order
    
    //if destination is unreachable from source
    if(this.distances[destination] == Integer.MAX_VALUE){
      //then path does not exist
      throw new PathDoesNotExistException("There is no path from node " + this.source + " to node " + destination + ". ");
    }
    
    int previous = destination;
    int size = 0;// keep track of the size of the path in terms of vertices (and is used as index)
    
    //add vertices in the reverse array
    while(previous != source){
      reverseArray[size] = previous;
      previous = this.predecessors[previous];
      size++;
    }
    reverseArray[size] = source;
    size++;
    
    //create actual array to be returned
    int[] actualArray = new int[size];
    //populate the array
    for(int i = 0; i < size; i++){
      actualArray[i] = reverseArray[size-1-i];
    }
    
    return actualArray;
  }
  
  public void printPath(int destination){
    /*Print the path in the format s->n1->n2->destination
     *if the path exists, else catch the Error and 
     *prints it
     */
    try {
      int[] path = this.shortestPath(destination);
      for (int i = 0; i < path.length; i++){
        int next = path[i];
        if (next == destination){
          System.out.println(destination);
        }
        else {
          System.out.print(next + "-->");
        }
      }
    }
    catch (BellmanFordException e){
      System.out.println(e);
    }
  }
  
  public static void main(String[] args){
    
    String file = args[0];
    WGraph g = new WGraph(file);
    try{
      BellmanFord bf = new BellmanFord(g, g.getSource());
      bf.printPath(g.getDestination());
    }
    catch (BellmanFordException e){
      System.out.println(e);
    }
    
  } 
}
