

package com.company;
import java.util.Scanner;

public class TestAppMain {
    
     public static void main(String[] args) {
         Scanner in = new Scanner(System.in);
         System.out.print("Введите число:");
         int count = in.nextInt();
         TestApp testApp = new TestApp();
         testApp.printSequence(count);

         //System.out.println(i.get(testApp.equals(count)));
        
    }
  }

