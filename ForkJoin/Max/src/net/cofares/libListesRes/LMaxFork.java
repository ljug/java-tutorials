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
package net.cofares.libListesRes;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pascalfares
 * @param <T>
 */
public class LMaxFork<T extends Comparable> extends RecursiveTask<T> {

    public static boolean DEBUG = false;
    static int c;
    List<T> l;
    int debut;
    int fin;
    int seuil;

    /**
     * Pour le calcul de la fonction entre debut et fin de la liste
     * @param l
     * @param seuil
     * @param d
     * @param f 
     */
    public LMaxFork(List<T> l, int seuil, int d, int f) {
        this.l = l;
        this.seuil = seuil;
        debut = d;
        fin = f;
    }

    public LMaxFork(List<T> l, int seuil) {
        this.l = l;
        this.seuil = seuil;
        debut = 0;
        fin = l.size() - 1;
        c = 0;
    }

    public T lMax2(List<T> l, int d, int f) throws Exception {
        if ((f - d) <= 0) {
            //Pas de max de liste vide faire uhe exception
            throw new Exception("Pas de max de liste vide...");
        }
        //Si ici liste non vide et non null
        if ((f - d) == 1) {
            return l.get(d);
        }
        if ((f - d) == 2) {
            return (l.get(d).compareTo(l.get(f)) > 0 ? l.get(d) : l.get(f));
        }
        //Split the lists
        int half = (f - d) / 2;
        //List<Integer> l1 = l.subList(0, half);
        //List<Integer> l2 = l.subList(half, l.size());
        T m1, m2;
        m1 = lMax2(l, d, d + half);
        m2 = lMax2(l, d + half, f);
        return ((m1.compareTo(m2) > 0 ? m1 : m2));
    }

    public T lMax() throws Exception {
        long timed = System.currentTimeMillis();
        T res;
        if ((fin - debut) <= seuil) {
            long timein = System.currentTimeMillis();
            //En dessou d'un certain seuil calcul "Normal"
            res = lMax2(l, debut, fin);
            if (DEBUG) {
                System.out.printf("in %10d à %10d : %4d", debut, fin, System.currentTimeMillis() - timein);            
                System.out.print("d:");
            }
        } else {
            //Au dessu du seuil fork - join
            //System.out.printf(".%d %d %d%n", c++, debut, fin);
            int half = (fin - debut) / 2;
            LMaxFork<T> l1 = new LMaxFork(l, seuil, debut, debut + half);
            l1.fork();
            LMaxFork<T> l2 = new LMaxFork(l, seuil, debut + half, fin);
            //l2.fork();
            T m1, m2;
            m1 = l1.join();
            m2 = l2.compute();
            res = (m1.compareTo(m2) > 0 ? m1 : m2);
            if (DEBUG) {
                System.out.print("r:");
            }
        }
        if (DEBUG) {
            System.out.printf("temps pour lMax par fork de %10d à %10d : %4d%n", debut, fin, System.currentTimeMillis() - timed);
        }
        return res;
    }

    @Override
    protected T compute() {

        try {
            return lMax();
        } catch (Exception ex) {
            Logger.getLogger(LMaxFork.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
