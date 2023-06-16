
public class KaratsubaTester {
  public static void main(String[] args){
    
    //NAIVE TEST 1
    System.out.println("Naive test 1: ");
    int product = Multiply.naive(1, 8, 7)[0];
    int cost = Multiply.naive(1, 8, 7)[1];
    
    if (product == 0) {
      System.out.println("Product is a success!");
    } else {
      System.out.println("Product has error!");
    }
    
    if (cost == 1) {
      System.out.println("Cost is a success!\n");
    } else {
      System.out.println("Cost has error!\n");
    }
    
    
    //NAIVE TEST 2
    System.out.println("Naive test 2: ");
    product = Multiply.naive(2, 8, 7)[0];
    cost = Multiply.naive(2, 8, 7)[1];
    
    if (product == 0) {
      System.out.println("Product is a success!");
    } else {
      System.out.println("Product has error!");
    }
    
    if (cost == 7) {
      System.out.println("Cost is a success!\n");
    } else {
      System.out.println("Cost has error!\n");
    }
    
    
    //NAIVE TEST 3
    System.out.println("Naive test 3: ");
    product = Multiply.naive(3, 8, 7)[0];
    cost = Multiply.naive(3, 8, 7)[1];
    
    if (product == 0) {
      System.out.println("Product is a success!");
    } else {
      System.out.println("Product has error!");
    }
    
    if (cost == 34) {
      System.out.println("Cost is a success!\n");
    } else {
      System.out.println("Cost has error!\n");
    }
    
    
    //NAIVE TEST 4
    System.out.println("Naive test 4: ");
    product = Multiply.naive(4, 8, 7)[0];
    cost = Multiply.naive(4, 8, 7)[1];
    
    if (product == 56) {
      System.out.println("Product is a success!");
    } else {
      System.out.println("Product has error!");
    }
    
    if (cost == 34) {
      System.out.println("Cost is a success!\n");
    } else {
      System.out.println("Cost has error!\n");
    }
    
    
    //NAIVE TEST 5
    System.out.println("Naive test 5: ");
    product = Multiply.naive(2, 5, 7)[0];
    cost = Multiply.naive(2, 5, 7)[1];
    
    if (product == 3) {
      System.out.println("Product is a success!");
    } else {
      System.out.println("Product has error!");
    }
    
    if (cost == 7) {
      System.out.println("Cost is a success!\n");
    } else {
      System.out.println("Cost has error!\n");
    }
    
    
    //NAIVE TEST 6
    System.out.println("Naive test 6: ");
    product = Multiply.naive(3, 5, 7)[0];
    cost = Multiply.naive(3, 5, 7)[1];
    
    if (product == 35) {
      System.out.println("Product is a success!");
    } else {
      System.out.println("Product has error!");
    }
    
    if (cost == 34) {
      System.out.println("Cost is a success!\n");
    } else {
      System.out.println("Cost has error!\n");
    }
    
    
    //KARATSUBA TEST 1
    System.out.println("Karatsuba test 1: ");
    product = Multiply.karatsuba(1, 8, 7)[0];
    cost = Multiply.karatsuba(1, 8, 7)[1];
    
    if (product == 0) {
      System.out.println("Product is a success!");
    } else {
      System.out.println("Product has error!");
    }
    
    if (cost == 1) {
      System.out.println("Cost is a success!\n");
    } else {
      System.out.println("Cost has error!\n");
    }
    
    //KARATSUBA TEST 2
    System.out.println("Karatsuba test 2: ");
    product = Multiply.karatsuba(4, -8, 7)[0];
    cost = Multiply.karatsuba(4, -8, 7)[1];
    
    if (product == -56) {
      System.out.println("Product is a success!");
    } else {
      System.out.println("Product has error!");
    }
    
    if (cost == 39) {
      System.out.println("Cost is a success!");
    } else {
      System.out.println("Cost has error!");
    }
    
    
  }
}
