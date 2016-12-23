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

/**
 *
 * @author pascalfares
 */
public class LMax<T extends Comparable> {
    List<T> l;
    int debut;
    int fin;
    public LMax(List<T> l) {
        this.l=l;
    }
    
    public T lMax() throws Exception {
        if ((l==null) || (l.size()==0)) {
            //Pas de max de liste vide faire uhe exception
            throw new Exception("Pas de max de liste vide...");
        }
        //Si ici liste non vide et non null
        if (l.size() == 1) return l.get(0);
        
        if (l.size()==2) return (l.get(0).compareTo(l.get(1)) > 0 ? l.get(0) : l.get(1));
        //Split the lists
        int half = l.size()/2;
        LMax<T> l1 = new LMax(l.subList(0, half));
        LMax<T> l2 = new LMax(l.subList(half, l.size()));
        T m1,m2;
        m1=l1.lMax();
        m2=l2.lMax();
        return (m1.compareTo(m2) > 0 ? m1 : m2);
    }
} 
