import java.io.*;
import java.util.*;

//No collaborators

public class Open_Addressing {
  public int m; // number of SLOTS AVAILABLE
  public int A; // the default random number
  int w;
  int r;
  public int[] Table;
  
  protected Open_Addressing(int w, int seed, int A) {
    
    this.w = w;
    this.r = (int) (w-1)/2 +1;
    this.m = power2(r);
    if (A==-1){
      this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
    }
    else{
      this.A = A;
    }
    this.Table = new int[m];
    for (int i =0; i<m; i++) {
      Table[i] = -1;
    }
    
  }
  
  /** Calculate 2^w*/
  public static int power2(int w) {
    return (int) Math.pow(2, w);
  }
  public static int generateRandom(int min, int max, int seed) {     
    Random generator = new Random(); 
    if(seed>=0){
      generator.setSeed(seed);
    }
    int i = generator.nextInt(max-min-1);
    return i+min+1;
  }
  /**Implements the hash function g(k)*/
  public int probe(int key, int i) {
    //TODO: implement this function and change the return statement.
    
    if (i>=this.m) return -1; //if i is greater than the size of the table
    
    int hFunction = (this.A*key)%(power2(this.w))>>(this.w-this.r); //h(k)
    int gFunction = (hFunction+i)%power2(r); //add the probe number and the modulo to get g(k)
    
    return gFunction;
  }
  
  
  /**Inserts key k into hash table. Returns the number of collisions encountered*/
  public int insertKey(int key){
    //TODO : implement this and change the return statement.
    
    int nCollisions = 0; //number of collisions (also used as probe number)
    int index = probe(key,nCollisions); //get the index thanks to the hash function
    
    while (Table[index] != -1 && nCollisions < this.m){ //while the slot is not empty (!=-1) and the number of collision is not m
      
      nCollisions++; //increment the number of collisions
      if(nCollisions != this.m) index = probe(key,nCollisions); //if we haven't reached the maximum number of collision, update the index
    }
    
    if (nCollisions != this.m){ //i.e. the slot we are at is empty
      Table[index] = key; //insert the key
    }
    
    return nCollisions;  
  }
  
  
  /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
  public int insertKeyArray (int[] keyArray){
    //TODO
    int collision = 0;
    for (int key: keyArray) {
      collision += insertKey(key);
    }
    return collision;
  }
  
  /**Inserts key k into hash table. Returns the number of collisions encountered*/
  public int removeKey(int key){
    //TODO: implement this and change the return statement
    
    int nCollisions = 0; //number of collisions (also used as probe number)
    int index = probe(key,nCollisions); //get the index thanks to the hash function
    
    while (Table[index] != key && nCollisions < this.m){ //while the slot does not contain the key and the number of collision is not m
      
      nCollisions++; //if the hash function did not led us to the key, try again with the next probe number
      if (nCollisions != this.m) index = probe(key,nCollisions); //if we haven't reached the maximum number of collision, update the index
    }
    
    if (Table[index]==key){ //i.e. if the element is in the table using probe number 'nCollisions'
      Table[index] = -1; //we will use -1 to indicate a deleted element (which is the same as an empty slot)
    }
    
    return nCollisions;
  }
}

