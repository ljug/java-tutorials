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
package e01;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import net.cofares.executor.CalculFactorielle;

/**
 *
 * @author pascalfares
 */
public class E01 {

    /**
     * 
     * @param n taille du pool 
     */
    public static void testExecutorAndFutureCallable(int n) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(n);
        System.out.printf("Taille du ThreadPoo, est %d %n%n", n);
        System.out.println("Soumission du callable pour le calcul de factorielle 10");
        Future<Long> result10 = es.submit(new CalculFactorielle(10));
        System.out.println("Soumission du callable pour le calcul de factorielle  15");
        Future<Long> result15 = es.submit(new CalculFactorielle(15));
        System.out.println("Soumission du callable pour le calcul de factorielle  20");
        Future<Long> result20 = es.submit(new CalculFactorielle(20));
        System.out.println("Appel de get d'un Future pour récupérer la valeur de factorielle 10");
        System.out.println("Soumission du callable pour le calcul de factorielle 10");
        Future<Long> result101 = es.submit(new CalculFactorielle(10));
        System.out.println("Soumission du callable pour le calcul de factorielle  15");
        Future<Long> result151 = es.submit(new CalculFactorielle(15));
        System.out.println("Soumission du callable pour le calcul de factorielle  20");
        Future<Long> result201 = es.submit(new CalculFactorielle(20));
        System.out.println("Appel de get d'un Future pour récupérer la valeur de factorielle 10");
        Thread.sleep(1000);
        long debut = System.currentTimeMillis();
        long factorialof10 = result10.get();
        System.out.printf("%d factorial of 10 is : %d%n", System.currentTimeMillis() - debut,factorialof10);
        System.out.println("Appel de get d'un Future pour récupérer la valeur de factorielle 15");
        debut = System.currentTimeMillis();
        long factorialof15 = result15.get();
        System.out.printf("%d factorial of 15 is : %d%n", System.currentTimeMillis() - debut,factorialof15);
        
        System.out.println("Appel de get d'un Future pour récupérer la valeur de factorielle 20");
        debut = System.currentTimeMillis();
        long factorialof20 = result20.get();
        System.out.printf("%d factorial of 20 is : %d%n", System.currentTimeMillis() - debut,factorialof20);
        System.out.println("Appel de get d'un Future pour récupérer la valeur de factorielle 10 bis");
        debut = System.currentTimeMillis();
        long factorialof101 = result101.get();
        System.out.printf("%d factorial of 10 is : %d%n", System.currentTimeMillis() - debut,factorialof101);
        System.out.println("Appel de get d'un Future pour récupérer la valeur de factorielle 15 bis");
        debut = System.currentTimeMillis();
        long factorialof151 = result151.get();
        System.out.printf("%d factorial of 15 is : %d%n", System.currentTimeMillis() - debut,factorialof151);
        
        System.out.println("Appel de get d'un Future pour récupérer la valeur de factorielle 20 bis");
        debut = System.currentTimeMillis();
        long factorialof201 = result201.get();
        System.out.printf("%d factorial of 20 is : %d%n", System.currentTimeMillis() - debut,factorialof201);
        
        es.shutdown();
        System.out.printf("Fin du test du ThreadPool, de %d %n%n", n);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // creating thread pool to execute task which implements Callable 
        //Nous essayerons avec plusieur taille de Pool
        testExecutorAndFutureCallable(1);
        testExecutorAndFutureCallable(4);
        
       testExecutorAndFutureCallable(16);
        System.exit(0);
    }

}
