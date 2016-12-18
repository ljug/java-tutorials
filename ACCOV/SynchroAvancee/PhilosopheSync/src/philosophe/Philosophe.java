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
package philosophe;


/**
 *
 * @author pascalfares
 */
public class Philosophe extends Thread {

    private final int rank;
    private final Fourchette fourchetteGauche;
    private final Fourchette fourchetteDroite;
    private int loopCount;

    public Philosophe(int rank,
            Fourchette fourchetteGauche,
            Fourchette fourchetteDroite,
            int loopCount) {
        this.rank = rank;
        this.fourchetteGauche = fourchetteGauche;
        this.fourchetteDroite = fourchetteDroite;
        this.loopCount = loopCount;
    }

    public void run() {
        while (true) {
            penser();
            prendre(fourchetteGauche);
            prendre(fourchetteDroite);

            manger();
            poser(fourchetteGauche);
            poser(fourchetteDroite);
            

        }
    }

    public void penser() {
        System.out.println("Philosophe " + rank + " pense");
    }

    public void manger() {
        System.out.println("Philosophe " + rank + " mange");
    }

    public void prendre(Fourchette fourchette) {
        fourchette.prendre();
    }

    public void poser(Fourchette fourchette) {
        fourchette.poser();
    }

    public static void main(String[] args) {
        Fourchette[] fourchettes = new Fourchette[5];
        for (int i = 0; i < 5; i++) {
            fourchettes[i] = new Fourchette();
        }

        Philosophe[] philosophe = new Philosophe[5];
        for (int i = 0; i < 5; i++) {
            philosophe[i] = new Philosophe(i,
                    fourchettes[i],
                    fourchettes[(i + 1) % 5],
                    50);
        }

        for (int i = 0; i < 5; i++) {
            philosophe[i].start();
        }

        for (int i = 0; i < 5; i++) {
            try {
                philosophe[i].join();
            } catch (InterruptedException e) {
            }
        };
    }
}
