/*
 * Copyright © <ISSAE - Cnam Liban>
 * Pascal Fares
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), 
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions: 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * The Software is provided “as is”, without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, 
 * fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders be liable for any claim, damages or other liability, 
 * whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the Software. »
 */
package tp1;

/**
 *
 * @author pascalfares
 */
public class Cpt extends Thread{

    private static int _cpt = 1;
    private final String _name;

    public Cpt(String name) {
        _name = name;
    }

    public static int getValeur() {
        return _cpt;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100000; i++) {
            int _c = _cpt;
            _cpt = _c + 1;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("VALEUR " + Cpt.getValeur());
        Cpt thr1 = new Cpt("Processus1");
        Cpt thr2 = new Cpt("Processus2");
        thr1.start();
        thr2.start();
        try {
            thr1.join();
            thr2.join();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("VALEUR " + Cpt.getValeur());
    }

}
