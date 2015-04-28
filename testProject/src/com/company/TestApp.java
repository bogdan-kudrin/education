
package com.company;


public class TestApp {
private int[] sq;

   public void PrintSq(int x) { 
            System.out.printf("Квадраты чисел до числа %d: ", x);
           for (int i=1; i<=x; i++) {
               
              
               System.out.printf("%d, ",i*i);
               
           }
   }   
}
