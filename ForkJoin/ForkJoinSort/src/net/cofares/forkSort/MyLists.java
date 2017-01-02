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
package net.cofares.forkSort;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pascalfares
 * @param <T> : Un eclasse de comparable pour trier une liste
 */
public class MyLists<T extends Comparable> {
    List<T> l;
    int debut;
    int fin;
    
    public MyLists(List<T> l) {
        this.l=l;
        debut=0;
        fin=l.size()-1;
    }
    public MyLists(MyLists<T> ml) {
        l=new ArrayList<T>(ml.l);        
        debut=ml.debut;
        fin=ml.fin;
    }
    
    public boolean isVide(){
        return (fin < debut);
    }
    
    public String toString() {
        if (isVide()) return "()";
        StringBuilder res=new StringBuilder("(");
        for (int i=debut;i<fin;i++) {
            res.append(l.get(i)).append(" ");
        }
        res.append(l.get(fin)).append(")");
        return res.toString();
    }
}
