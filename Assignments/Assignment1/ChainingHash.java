import java.io.*;
import java.util.*;

//No collaborators

public class ChainingHash {
  
  public int m; // number of SLOTS 
  public int A; // the default random number
  int w;
  int r;
  public ArrayList<ArrayList<Integer>>  Table;
  
  // if A==-1, then a random A is generated. else, input A is used.
  protected ChainingHash(int w, int seed, int A){
    this.w = w;
    this.r = (int) (w-1)/2 +1;
    this.m = power2(r);
    this.Table = new ArrayList<ArrayList<Integer>>(m);
    for (int i=0; i<m; i++) {
      Table.add(new ArrayList<Integer>());
    }
    if (A==-1){
      this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
    }
    else{
      this.A = A;
    }
    
  }
  /** Calculate 2^w*/
  public static int power2(int w) {
    return (int) Math.pow(2, w);
  }
  //generate a random number in a range (for A)
  public static int generateRandom(int min, int max, int seed) {     
    Random generator = new Random(); 
    if(seed>=0){
      generator.setSeed(seed);
    }
    int i = generator.nextInt(max-min-1);
    return i+min+1;     
  }
  
  
  
  
  /**Implements the hash function h(k)*/
  public int chain (int key) {
    // TODO: implement this and change the return statement
    int hFunction = (this.A*key)%(power2(this.w))>>(this.w-this.r); //implement h(k) using the variables defined in the constructor
    //System.out.println("h(" + key + ") = " + hFunction);
    return hFunction; 
  }
  
  
  /**Inserts key k into hash table. Returns the number of collisions encountered*/
  public int insertKey(int key){
    //TODO: implement this and change the return statement
    
    int index = chain(key); //set the index thanks to the hash function
    int nCollisions = Table.get(index).size(); //get the number of element in the sub arraylist
    Table.get(index).add(key); //add the key in the bin at the correct index
    
    //System.out.println("Key " + key + " successfully inserted at index " + index + ". ");
    //System.out.println("Number of collisions: " + nCollisions + ". \n");

    return nCollisions;
    
  }
  
  
  
  /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
  public int insertKeyArray (int[] keyArray){
    int collision = 0;
    for (int key: keyArray) {
      collision += insertKey(key);
    }
    return collision;
  }
}