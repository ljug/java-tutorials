/*
 * Copyright © <Pascal Fares @ ISSAE - Cnam Liban>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), 
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions: 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * The Software is provided “as is”, without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, 
 * fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders be liable for any claim, damages or other liability, 
 * whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the Software. »
 */
package forkjoinsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.cofares.forkSort.MyLists;

/**
 *
 * @author pascalfares
 */
public class ForkJoinSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       List<Integer> numbers = new ArrayList<>(
                Arrays.asList(5, 3, 1, 2, 9, 5, 0, 7, 30,25)
        );
       MyLists<Integer> rs=new MyLists<> (numbers);
       System.out.println(rs);
    }
    
}
