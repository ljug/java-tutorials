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
package max;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import net.cofares.libListesRes.FonctionsSurListes;
import net.cofares.libListesRes.LMax;
import net.cofares.libListesRes.LMaxFork;

/**
 *
 * @author pascalfares
 */
public class Max {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        List<Integer> numbers = new ArrayList<>(
                Arrays.asList(5, 3, 1, 2, 9, 5, 0, 7,25,30)
        );
        for (int i=0; i< 10000000; i++) {
            numbers.add((int) (100 * Math.random()));
        }
        //
        long debut = System.currentTimeMillis();
        System.out.printf("lmax()=%d", FonctionsSurListes.lMax(numbers));
        System.out.println(" Elapsed "+ (System.currentTimeMillis()-debut));
        //
        debut = System.currentTimeMillis();
        System.out.printf("lmax2()=%d ", FonctionsSurListes.lMax2(numbers,0,numbers.size()-1));
        System.out.println("Elapsed "+ (System.currentTimeMillis()-debut));
        //
        debut = System.currentTimeMillis();
        LMax<Integer> li = new LMax<>(numbers);
        System.out.printf("LMax()=%d ", li.lMax());
        System.out.println("Elapsed "+ (System.currentTimeMillis()-debut));
        //
        debut = System.currentTimeMillis();
        LMaxFork<Integer> lif = new LMaxFork<>(numbers,numbers.size()/16);
        ForkJoinPool pool = new ForkJoinPool(16);
        System.out.printf("LMaxFork()=%d ", pool.invoke(lif));    
        System.out.println("Elapsed "+ (System.currentTimeMillis()-debut));
        
        //==== Autres tests
        /*
        debut = System.currentTimeMillis();
        numbers = new ArrayList<Integer>(
                Arrays.asList(5)
        );
        System.out.printf("max(5)=%d%n", FonctionsSurListes.lMax(numbers));
        numbers = new ArrayList<Integer>(
                Arrays.asList(5, 3)
        );
        
        System.out.printf("max(5, 3)=%d%n", FonctionsSurListes.lMax(numbers));

        numbers = new ArrayList<Integer>(
                Arrays.asList()
        );
        System.out.printf("max()=%d%n", FonctionsSurListes.lMax(numbers));

        numbers = null;
        System.out.printf("max(null)=%d%n", FonctionsSurListes.lMax(numbers));
        /**/
        /**
         * Résultat: 
         * max(5, 3, 1, 2, 9, 5, 0, 7)=9 
         * max(5)=5 
         * max(5, 3)=5 
         * Exception in
         * thread "main" java.lang.Exception: Pas de max de liste vide... 
         * at net.cofares.libListesRes.FonctionsSurListes.lMax(FonctionsSurListes.java:24)
         * at max.Max.main(Max.java:45)
         */
    }

}
