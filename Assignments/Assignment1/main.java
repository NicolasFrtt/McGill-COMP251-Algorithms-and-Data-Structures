import java.io.*;
import java.util.*;

//No collaborators

public class main {     
  
  
  public static void main(String[] args) {
    //TODO:build the hash table and insert keys using the insertKeyArray function.
        
        //Chaining: 
    
    //List X: (683, '{12, 14, 77, 74, 63, 21, 69, 13, 84, 93, 35, 89, 45, 60, 15, 57, 51, 18, 42, 62}')
    Chaining chainingMap1 = new Chaining(10, 0, 683);
    int[] listX = {12, 14, 77, 74, 63, 21, 69, 13, 84, 93, 35, 89, 45, 60, 15, 57, 51, 18, 42, 62};
    System.out.println("Number of collisions for List X using chaining: " + chainingMap1.insertKeyArray(listX) + ". ");
    //output 88 with w = 10

    
    //List Y: (554, '{86, 85, 6, 97, 19, 66, 26, 14, 15, 49, 75, 64, 35, 54, 31, 9, 82, 29, 81, 13}')
    Chaining chainingMap2 = new Chaining(10, 0, 554);
    int[] listY = {86, 85, 6, 97, 19, 66, 26, 14, 15, 49, 75, 64, 35, 54, 31, 9, 82, 29, 81, 13};
    System.out.println("Number of collisions for List Y using chaining: " + chainingMap2.insertKeyArray(listY) + ". ");
    //output 3 with w = 10
    
    System.out.println();
    
        //Open adressing: 
    
    //List X: (683, '{12, 14, 77, 74, 63, 21, 69, 13, 84, 93, 35, 89, 45, 60, 15, 57, 51, 18, 42, 62}')
    Open_Addressing openMap1 = new Open_Addressing(10, 0, 683);
    System.out.println("Number of collisions for List X using open addressing: " + openMap1.insertKeyArray(listX) + ". ");
    //output 106 with w = 10

    
    //List Y: (554, '{86, 85, 6, 97, 19, 66, 26, 14, 15, 49, 75, 64, 35, 54, 31, 9, 82, 29, 81, 13}')
    Open_Addressing openMap2 = new Open_Addressing(10, 0, 554);
    System.out.println("Number of collisions for List Y using open addressing: " + openMap2.insertKeyArray(listY) + ". ");
    //output 3 with w = 10
    
  }
}
