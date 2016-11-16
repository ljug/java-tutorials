/*
 * Copyright © <copyright holders>
 * ISSAE - Cnam Liban
 * Pascal Fares
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), 
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions: 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * The Software is provided “as is”, without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, 
 * fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders be liable for any claim, damages or other liability, 
 * whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the Software. »
 */
package cubyhole;

/**
 *
 * @author pascalfares
 */
public class CubbyHole {

    /**
     * @param args the command line arguments
     */
    private int contents;
    private boolean available = false;

    public synchronized int get() {
        //  guarded block. (un if ne suffit pas ... )
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        available = false;
        notifyAll();
        return contents;
    }

    public synchronized void put(int value) {
        //  guarded block. (un if ne suffit pas ... )
        while (available == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        contents = value;
        available = true;
        notifyAll();
    }

    /**
     * Pour le test
     *
     * @param args
     */
    public static void main(String[] args) {
        CubbyHole c = new CubbyHole();
        Producteur p1 = new Producteur(c, 1);
        Consomateur c1 = new Consomateur(c, 1);
        Consomateur c2 = new Consomateur(c, 2);
        p1.start();
        c1.start();
        c2.start();
    }
}
