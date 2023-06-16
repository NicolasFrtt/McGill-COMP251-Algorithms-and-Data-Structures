import java.io.*;
import java.util.*;

//No collaborators

public class mainHash {     
  
  
  public static void main(String[] args) {
    //TODO:build the hash table and insert keys using the insertKeyArray function.
    
    
       //Chaining: 
    
    //(683, '{12, 14, 77, 74, 63, 21, 69, 13, 84, 93, 35, 89, 45, 60, 15, 57, 51, 18, 42, 62}')
    Chaining chainingMap1 = new Chaining(10, 0, 683);
    int[] listX = {12, 14, 77, 74, 63, 21, 69, 13, 84, 93, 35, 89, 45, 60, 15, 57, 51, 18, 42, 62};
    System.out.println("Number of collisions for List X using chaining: " + chainingMap1.insertKeyArray(listX) + ". ");
    //output 88 with w = 10

    
    //(554, '{86, 85, 6, 97, 19, 66, 26, 14, 15, 49, 75, 64, 35, 54, 31, 9, 82, 29, 81, 13}')
    Chaining chainingMap2 = new Chaining(10, 0, 554);
    int[] listY = {86, 85, 6, 97, 19, 66, 26, 14, 15, 49, 75, 64, 35, 54, 31, 9, 82, 29, 81, 13};
    System.out.println("Number of collisions for List Y using chaining: " + chainingMap2.insertKeyArray(listY) + ". ");
    //output 3 with w = 10
    
    
    System.out.println();
    
    
       //Open adressing: 
    
    //(683, '{12, 14, 77, 74, 63, 21, 69, 13, 84, 93, 35, 89, 45, 60, 15, 57, 51, 18, 42, 62}')
    Open_Addressing openMap1 = new Open_Addressing(10, 0, 683);
    System.out.println("Number of collisions for List X using open addressing: " + openMap1.insertKeyArray(listX) + ". ");
    //output 106 with w = 10

    
    //(554, '{86, 85, 6, 97, 19, 66, 26, 14, 15, 49, 75, 64, 35, 54, 31, 9, 82, 29, 81, 13}')
    Open_Addressing openMap2 = new Open_Addressing(10, 0, 554);
    System.out.println("Number of collisions for List Y using open addressing: " + openMap2.insertKeyArray(listY) + ". ");
    //output 3 with w = 10
    
    
    System.out.println();
    System.out.println();
    System.out.println();
    
    
    
    
    Open_Addressing openMap = new Open_Addressing(4, 0, -1);
    System.out.println("m = " + openMap.m + "; A = " + openMap.A + "; r = " + openMap.r + "\n");
    int[] keyArray = {125, 63, 3, 4917, 495, 4, 947, 43, 1295, 0};
    openMap.insertKeyArray(keyArray);
    
    System.out.println("\nOther tests: ");
    openMap.insertKey(0);
    openMap.insertKey(1);
    openMap.removeKey(1);
    openMap.removeKey(63);
    openMap.insertKey(1);
    openMap.removeKey(1295);
    openMap.removeKey(0);
    openMap.removeKey(4917);
    openMap.removeKey(1);
    
    
    
    Chaining chainingMap = new Chaining(10, 0, -1);
    int chain = chainingMap.chain(1);
    System.out.println("Chaining chain check:"+(chain==30));
    System.out.println("A = " + chainingMap.A + "; r = " + chainingMap.r + "; w = " + chainingMap.w + "\n");
    for (int i = 0; i < 40; i+=2){
      chainingMap.insertKey(i);
      chainingMap.insertKey(i);
    }
    
    
    chainingMap.insertKey(3);
    chainingMap.insertKey(5);
    chainingMap.insertKey(1);
    chainingMap.insertKey(39);
    chainingMap.insertKey(301);
    chainingMap.insertKey(40);
    chainingMap.insertKey(2319);
    chainingMap.insertKey(0);
    chainingMap.insertKey(2);
    
    
    
  }
}