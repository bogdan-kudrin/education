
package com.company;


import java.util.ArrayList;

public class TestApp {
   public ArrayList<CustomNumber> savedQuads = new ArrayList<CustomNumber>();

   public void printSequence(int limit) {
           System.out.printf("Квадраты чисел до числа %d: ", limit);
           for (int i=1; i <= limit; i++) {
               int quad = i*i;
               savedQuads.add(new CustomNumber(quad));
               System.out.printf("%d, ", quad);
           }
   }


}
