import java.util.*;
import java.io.*;

public class Multiply{
  
  private static int randomInt(int size) {
    Random rand = new Random();
    int maxval = (1 << size) - 1;
    return rand.nextInt(maxval + 1);
  }
  
  public static int[] naive(int size, int x, int y) {
    
    // YOUR CODE GOES HERE  (Note: Change return statement)
    
    //initialize array
    int[] resultAndCost = {0, 0};
    //size cut, i.e. keep the last 'size' bits 
    x = x%(int)(Math.pow(2, size));
    y = y%(int)(Math.pow(2, size));
    
    //base case
    if(size == 1){
      resultAndCost[0] = x*y;
      resultAndCost[1] = 1;
      return resultAndCost;
    }
    
    //induction case
    
    //shortcuts
    int halfSize = (size+1)/2; //get ceiling(size/2)
    int powerOfTwo = (int)(Math.pow(2, halfSize));
    int leftX =  x/powerOfTwo;
    int leftY = y/powerOfTwo;
    int rightX = x % powerOfTwo;
    int rightY = y % powerOfTwo;
    
    //compute four multiplications recursively
    int[] multLL = naive(halfSize, leftX, leftY);
    int[] multLR = naive(halfSize, leftX, rightY);
    int[] multRL = naive(halfSize, rightX, leftY);
    int[] multRR = naive(halfSize, rightX, rightY);
    
    //compute result
    resultAndCost[0] = (int)(Math.pow(2, 2*halfSize))*multLL[0] + powerOfTwo*(multRL[0] + multLR[0]) + multRR[0];
    //compute cost
    resultAndCost[1] = multLL[1] + multLR[1] + multRL[1] + multRR[1] + 3*halfSize;
    
    return resultAndCost;
    
  }
  
  public static int[] karatsuba(int size, int x, int y) {
    
    // YOUR CODE GOES HERE  (Note: Change return statement)
    
    //initialize array
    int[] resultAndCost = {0, 0};
    //size cut, i.e. keep the last 'size' bits 
    x = x%(int)(Math.pow(2, size));
    y = y%(int)(Math.pow(2, size));
    
    //base case
    if(size == 1){
      resultAndCost[0] = x*y;
      resultAndCost[1] = 1;
      return resultAndCost;
    }
    
    //induction case
    
    //shortcuts
    int halfSize = (size+1)/2; //get ceiling(size/2)
    int powerOfTwo = (int)(Math.pow(2, halfSize));
    int leftX =  x/powerOfTwo;
    int leftY = y/powerOfTwo;
    int rightX = x % powerOfTwo;
    int rightY = y % powerOfTwo;
    
    //compute three multiplications recursively
    int[] multLL = karatsuba(halfSize, leftX, leftY);
    int[] multMid = karatsuba(halfSize, leftX - rightX, leftY - rightY);
    int[] multRR = karatsuba(halfSize, rightX, rightY);
    
    //compute result
    resultAndCost[0] = (int)(Math.pow(2, 2*halfSize))*multLL[0] + powerOfTwo*(multLL[0] + multRR[0] - multMid[0]) + multRR[0];
    //compute cost
    resultAndCost[1] = multLL[1] + multMid[1] + multRR[1] + 6*halfSize;
    
    return resultAndCost;
    
  }
  
  public static void main(String[] args){
    
    try{
      int maxRound = 20;
      int maxIntBitSize = 16;
      for (int size=1; size<=maxIntBitSize; size++) {
        int sumOpNaive = 0;
        int sumOpKaratsuba = 0;
        for (int round=0; round<maxRound; round++) {
          int x = randomInt(size);
          int y = randomInt(size);
          int[] resNaive = naive(size,x,y);
          int[] resKaratsuba = karatsuba(size,x,y);
          
          if (resNaive[0] != resKaratsuba[0]) {
            throw new Exception("Return values do not match! (x=" + x + "; y=" + y + "; Naive=" + resNaive[0] + "; Karatsuba=" + resKaratsuba[0] + ")");
          }
          
          if (resNaive[0] != (x*y)) {
            int myproduct = x*y;
            throw new Exception("Evaluation is wrong! (x=" + x + "; y=" + y + "; Your result=" + resNaive[0] + "; True value=" + myproduct + ")");
          }
          
          sumOpNaive += resNaive[1];
          sumOpKaratsuba += resKaratsuba[1];
        }
        int avgOpNaive = sumOpNaive / maxRound;
        int avgOpKaratsuba = sumOpKaratsuba / maxRound;
        System.out.println(size + "," + avgOpNaive + "," + avgOpKaratsuba);
      }
    }
    catch (Exception e){
      System.out.println(e);
    }
    
  } 
}
