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
package safelock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author pascalfares
 */
public class SafeLock {

    static class Friend {

        private final String name;
        private final Lock lock = new ReentrantLock();

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public boolean tendreArc(Friend lanceur) {
            Boolean myLock = false;
            Boolean yourLock = false;
            try {
                myLock = lock.tryLock();
                yourLock = lanceur.lock.tryLock();
            } finally {
                if (!(myLock && yourLock)) {
                    if (myLock) {
                        lock.unlock();
                    }
                    if (yourLock) {
                        lanceur.lock.unlock();
                    }
                }
            }
            return myLock && yourLock;
        }

        public void bow(Friend lanceur) {
            if (tendreArc(lanceur)) {
                try {
                    System.out.format("%s: %s has"
                            + " m'a lancé une flèche!%n",
                            this.name, lanceur.getName());
                    lanceur.lanceRetour(this);
                } finally {
                    lock.unlock();
                    lanceur.lock.unlock();
                }
            } else {
                System.out.format("%s: %s "
                        + " Souhaite me lancé, mais a touvé que"
                        + " je lui lance déjà une flèche"
                        + " %n",
                        this.name, lanceur.getName());
            }
        }

        public void lanceRetour(Friend lanceur) {
            System.out.format("%s: %s "
                    + " m'a lancé en retour!%n",
                    this.name, lanceur.getName());
        }
    }

    static class BoucleLance implements Runnable {

        private final Friend lanceur;
        private final Friend recepteur;

        public BoucleLance(Friend lanceur, Friend recepteur) {
            this.lanceur = lanceur;
            this.recepteur = recepteur;
        }

        @Override
        public void run() {
            Random random = new Random();
            for (;;) {
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                }
                recepteur.bow(lanceur);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Friend alphonse
                = new Friend("Alphonse");
        final Friend gaston
                = new Friend("Gaston");
        new Thread(new BoucleLance(alphonse, gaston)).start();
        new Thread(new BoucleLance(gaston, alphonse)).start();
    }

}
