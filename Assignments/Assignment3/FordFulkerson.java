import java.io.*;
import java.util.*;




public class FordFulkerson {
  
  
  public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
    ArrayList<Integer> Stack = new ArrayList<Integer>();
    // YOUR CODE GOES HERE
    
    //create a stack to do dfs iteratively
    Stack<Integer> actualStack = new Stack<Integer>();
    //create an array to tell if a node has been visited
    int n = graph.getNbNodes();
    int[] visited = new int[n]; //we will use the nodes as the index, since the nodes are numbered from 0 to n-1
    //push the source onto the stack
    actualStack.push(source);
    //size of the arraylist
    int size = 0;
    //boolean that tells if we found the path
    boolean notFound = true;
    
    while(!actualStack.empty() && notFound){
      int temp = actualStack.pop();
      
      //System.out.println(Stack); //used to print the current path at each iteration
      
      //if not visited
      if(visited[temp] != 10){
        visited[temp] = 10; //use 10 to tell if a node is visited
        
        Stack.add(temp);
        size++;
        
        
        for(Edge e : graph.getEdges()){
          if(e.nodes[0] == temp && visited[e.nodes[1]] != 10){
            actualStack.push(e.nodes[1]);
          }
          
        }
        
        //check if the node we just added is linked to the previous one
        //if it is not, then that means that the node before the one we just added is not in the path we want
        boolean bool = true;
        for(int i = size-2; i >=0 && bool; i--){
          if(size == 1){ //if size is 1, break
            bool = false;
          }
          else if(graph.getEdge(Stack.get(i), Stack.get(size-1)) != null){ //if we found an edge, break
            bool = false;
          }
          else{ //i.e. the edge still does not exist
            Stack.remove(i);
            size--;
          }
        }
      }
      //if we just added the destination, break the while
      if(temp == destination){
        notFound = false;
      }
    }
    
    //System.out.println(Stack + "\n"); //used to print the final path
    
    if(Stack.contains(source) && Stack.contains(destination)){
      return Stack;
    }
    else{
      return null;
    }
  }
 
 
  
  public static void fordfulkerson(Integer source, Integer destination, WGraph graph, String filePath){
    String answer="";
    String myMcGillID = "260826282"; //Please initialize this variable with your McGill ID
    int maxFlow = 0;
    
    // YOUR CODE GOES HERE
    
    //initialize your residual graph with the input graph
    WGraph residualGraph = new WGraph(graph);
    //create a copy of the current graph to keep track of the capacity
    WGraph copyGraph = new WGraph(graph);
    //set all the weights of the current graph to 0 (weight will represent the flow)
    for(Edge e : graph.getEdges()){
      e.weight = 0;
    }
    
    while(pathDFS(source, destination, residualGraph) != null){
      ArrayList<Integer> Path = pathDFS(source, destination, residualGraph);
      
      //augment Path
      
      //get by how much
      int min = residualGraph.getEdge(Path.get(0), Path.get(1)).weight;
      for(int i = 2; i < Path.size(); i++){
        int temp = residualGraph.getEdge(Path.get(i-1), Path.get(i)).weight;
        if(temp < min){
          min = temp;
        }
      }
      
      //add to the max flow
      maxFlow += min;
      
      //add/remove the min flow to the edges of the starting graph
      for(int i = 1; i < Path.size(); i++){
        int node1 = Path.get(i-1);
        int node2 = Path.get(i);
        
        //if forward edge i.e. if the edge in the residual graph is also in the starting graph
        if(graph.getEdge(node1, node2) != null){
          int weightTemp = graph.getEdge(node1, node2).weight;
          graph.setEdge(node1, node2, weightTemp+min);
        }
        //else it is a backward edge then the opposite edge in the in starting graph
        else{
          int weightTemp = graph.getEdge(node2, node1).weight;
          graph.setEdge(node2, node1, weightTemp-min);
        }
      }
      
      //Now that we augnmented the path, update the residual graph based on the new graph
      
      //create a new residual graph
      residualGraph = new WGraph();
      for(Edge e : graph.getEdges()){
        int node1 = e.nodes[0];
        int node2 = e.nodes[1];
        //if the flow is positive on an edge, we can add a backward edge to the residual graph
        if(e.weight > 0){
          Edge temp = new Edge(node2, node1, e.weight);
          residualGraph.addEdge(temp);
        }
        //if the flow is lower than the capacity, we can still add so we add a forward edge to the residual graph
        int capacity = copyGraph.getEdge(node1, node2).weight;
        if(e.weight < capacity){
          Edge temp = new Edge(node1, node2, capacity-e.weight);
          residualGraph.addEdge(temp);
        }
      }
    }
    
  
  
  answer += maxFlow + "\n" + graph.toString(); 
  writeAnswer(filePath+myMcGillID+".txt",answer);
  System.out.println(answer);
 }
 
 
 public static void writeAnswer(String path, String line){
  BufferedReader br = null;
  File file = new File(path);
  // if file doesnt exists, then create it
  
  try {
  if (!file.exists()) {
   file.createNewFile();
  }
  FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
  BufferedWriter bw = new BufferedWriter(fw);
  bw.write(line+"\n"); 
  bw.close();
  } catch (IOException e) {
   e.printStackTrace();
  } finally {
   try {
    if (br != null)br.close();
   } catch (IOException ex) {
    ex.printStackTrace();
   }
  }
 }
 
  public static void main(String[] args){
   String file = args[0];
   File f = new File(file);
   WGraph g = new WGraph(file);
   fordfulkerson(g.getSource(),g.getDestination(),g,f.getAbsolutePath().replace(".txt",""));
  }
}
