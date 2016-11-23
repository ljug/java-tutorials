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
package deadlock;

/**
 *
 * @author pascalfares
 */
public class DeadLock {

    static class Friend {

        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public synchronized void fleche(Friend lanceur) {
            System.out.format("%s: %s"
                    + "  me lance une flèche!%n",
                    this.name, lanceur.getName());
            lanceur.flecheRetour(this);
        }

        public synchronized void flecheRetour(Friend lanceur) {
            System.out.format("%s: %s"
                    + " m'a relencé une flèche!%n",
                    this.name, lanceur.getName());
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
        new Thread(new Runnable() {
            public void run() {
                alphonse.fleche(gaston);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                gaston.fleche(alphonse);
            }
        }).start();
    }

}
