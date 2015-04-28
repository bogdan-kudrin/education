package com.company;

/**
 * User: BKudrin
 * Date: 28.04.2015
 * Time: 17:07
 */
public class CustomNumber {
    int number;
    boolean isEven;

    public CustomNumber(int number){
        this.number = number;
        this.isEven =  number % 2 == 0;
    }
}
