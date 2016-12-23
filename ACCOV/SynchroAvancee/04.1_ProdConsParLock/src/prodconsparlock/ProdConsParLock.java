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
package prodconsparlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author pascalfares
 */
public class ProdConsParLock {

    public static boolean DEBUG = false;

    public static void main(String[] args) {
        SharedBuffer s = new SharedBuffer();
        
        new Producteur(s).start();
        new Consomateur(s).start();
    }
}

class SharedBuffer {

    //Un buffer d'une seule place (1 charactère)
    private char c;

    //Indique si il existe quelque chose dans le buffer
    private volatile boolean disponible;

    private final Lock lock;

    
    private final Condition plein;
    private final Condition vide;

    SharedBuffer() {
        //Initialilement le buffer est vide donc rien de disponible
        disponible = false;
        //On crée le lock
        lock = new ReentrantLock();
        //Et la condition
        plein = lock.newCondition();
        vide = lock.newCondition();
    }

    Lock getLock() {
        return lock;
    }

    char getSharedChar() {
        if (ProdConsParLock.DEBUG) {
            System.out.println("enter getSharedChar brefore lock");
        }
        lock.lock();
        if (ProdConsParLock.DEBUG) {
            System.out.println("enter getSharedChar after lock");
        }
        //Ce premier lock est ici pour garantir le unlock...
        //cette menaière de faire vous assurera le fait que pour chaque lock
        //Il y aura un unlock

        try {
            while (!disponible) {
                try {
                    vide.await();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            disponible = false;
            plein.signalAll();
        } finally {
            lock.unlock();
            if (ProdConsParLock.DEBUG) {
                System.out.println("enter getSharedChar" + c);
            }
            return c;
        }
    }

    void setSharedChar(char c) {
        if (ProdConsParLock.DEBUG) {
            System.out.println("enter setSharedChar brefore lock" + c);
        }
        lock.lock();
        if (ProdConsParLock.DEBUG) {
            System.out.println("enter setSharedChar after lock" + c);
        }
        try {
            // guarded block. (un if ne suffit pas ... )
            while (disponible) {
                try {
                    plein.await();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            this.c = c;
            disponible = true;
            vide.signalAll();
        } finally {
            lock.unlock();
            if (ProdConsParLock.DEBUG) {
                System.out.println("out setSharedChar ");
            }
        }
    }
}

class Producteur extends Thread {

    private final Lock l;

    private final SharedBuffer s;

    Producteur(SharedBuffer s) {
        this.s = s;
        l = s.getLock();
    }

    @Override
    public void run() {
        if (ProdConsParLock.DEBUG) {
            System.out.println("Run de Producteur");
        }
        for (char ch = 'A'; ch <= 'Z'; ch++) {

            if (ProdConsParLock.DEBUG) {
                System.out.println("Run de Producteur before set");
            }

            s.setSharedChar(ch);
            System.out.println(ch + " produit par le producteur.");

        }
    }
}

class Consomateur extends Thread {

    private final Lock l;

    private final SharedBuffer s;

    Consomateur(SharedBuffer s) {
        this.s = s;
        l = s.getLock();
    }

    @Override
    public void run() {
        if (ProdConsParLock.DEBUG) {
            System.out.println("Run de consomateur");
        }
        char ch;
        do {

            if (ProdConsParLock.DEBUG) {
                System.out.println("Run de consomateur before get");
            }

            ch = s.getSharedChar();
            System.out.println(ch + " consomé par le condomateur.");

        } while (ch != 'Z');
    }
}
