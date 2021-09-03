/*In this Kata you must create a function that takes an amount of US currency
in cents, and returns a dictionary/hash which shows the least amount of coins used to
make up that amount. The only coin denominations considered in this exercise are:
Pennies (1¢), Nickels (5¢), Dimes (10¢) and Quarters (25¢).
Therefor the dictionary returned should contain exactly 4 key/value pairs.

Notes:

If the function is passed either 0 or a negative number, the function should return the dictionary with all values equal to 0.
If a float is passed into the function, its value should be be rounded down, and the resulting dictionary should never contain fractions of a coin.
Examples
    loose_change(56)    ==>  {'Nickels': 1, 'Pennies': 1, 'Dimes': 0, 'Quarters': 2}
    loose_change(-435)  ==>  {'Nickels': 0, 'Pennies': 0, 'Dimes': 0, 'Quarters': 0}
    loose_change(4.935) ==>  {'Nickels': 0, 'Pennies': 4, 'Dimes': 0, 'Quarters': 0}
    */

package com.github.nikhrom.javatraining.codewars.six_kyu;
import java.awt.geom.QuadCurve2D;
import java.util.HashMap;

public class Change {

    private static final int PENNIE = 1;
    private static final int NICKEL = 5;
    private static final int DIME = 10;
    private static final int QUARTER = 25;


    public static void main(String[] args) {
        System.out.println(looseChange(27));
    }

    public static HashMap<String, Integer> looseChange(int cent) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Pennies", 0);
        map.put("Nickels", 0);
        map.put("Dimes", 0);
        map.put("Quarters", 0);

        if(cent <= 0) return map;

        int newValue = cent / QUARTER;
        map.replace("Quarters", newValue);
        cent -= QUARTER * newValue;

        newValue = cent / DIME;
        map.replace("Dimes", newValue);
        cent -= DIME * newValue;

        newValue = cent / NICKEL;
        map.replace("Nickels", newValue);
        cent -= NICKEL * newValue;

        newValue = cent / PENNIE;
        map.replace("Pennies", newValue);
        cent -= PENNIE * newValue;


        return map;
    }
}
