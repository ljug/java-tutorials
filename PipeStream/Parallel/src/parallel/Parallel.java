/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallel;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author pascalfares
 */
public class Parallel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] ints;
        ints = new int[5000000];
        ArrayList<Integer> mlist = new ArrayList<Integer>(5000000);
        long startTime, stopTime;

        for (int i = 0; i < 5000000; i++) {
            ints[i] = (int) (Math.random() * 100);
            mlist.add(ints[i]);
        }

        //Arrays.stream(ints).forEach(i -> System.out.println(i));
        startTime = System.currentTimeMillis();
        int m = Integer.MIN_VALUE;
        for (int i : mlist) {
            if (i > m) {
                m = i;
            }
        }
        stopTime = System.currentTimeMillis();
        System.out.println("boucle " + (stopTime - startTime));
        
        
        startTime = System.currentTimeMillis();
        m = mlist.stream()
                .reduce(Integer.MIN_VALUE, Math::max);
        stopTime = System.currentTimeMillis();
        System.out.println("temps seq " + (stopTime - startTime));

        startTime = System.currentTimeMillis();
        m = mlist.stream().parallel()
                .reduce(Integer.MIN_VALUE, Math::max);
        stopTime = System.currentTimeMillis();
        System.out.println("temps parallel " + (stopTime - startTime));
    }

}
