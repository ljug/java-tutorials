package net.cofares.data;

import java.util.concurrent.Semaphore;

/**
 * Database.java
 *
 * This class contains the methods the readers and writers will use to
 * coordinate access to the database. Access is coordinated using Java
 * synchronization.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999
 * @version 1.1 - Ocotber 2, 2001 - Added output message to finLecture().

 Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz Applied
 Operating Systems Concepts - John Wiley and Sons, Inc.
 *
 * @author Pascal Fares ISSAE Cnam Liban modification et adaptation
 */
public class Database {

    public Database() {
        ContLecteurs = 0;
        mutex=new Semaphore(1);
        entiteLibre=new Semaphore(1);
        data = new Data(12, 13);
    }

    // readers and writers will call this to nap
    public static void sieste() {
        int sleepTime = (int) (TEMP_SIESTE * Math.random());
        try {
            Thread.sleep(sleepTime * 1000);
        } catch (InterruptedException e) {
        }
    }

    //
    public String lire() {
        return "[" + data.getElt1() + "," + data.getElt2() + "]";
    }

    public void ecrire(int elt1, int elt2) {
        
        data.setElt1(elt1);
        
        Thread.yield(); 
        
        data.setElt2(elt2);
        
    }

    public int demanderLecture() {
       
        mutex.acquireUninterruptibly();
        if (ContLecteurs==0) {
             //si je suis le premier lecteur
            entiteLibre.acquireUninterruptibly();
        }
        ++ContLecteurs;
        mutex.release();
        return ContLecteurs;
    }

    public int finLecture() {
        mutex.acquireUninterruptibly();
        --ContLecteurs;

        //Si je suis le dernier lecteur
        if (ContLecteurs == 0) {
            entiteLibre.release();
        }
        mutex.release();
        //System.out.println("Nombre de lecteur = " + ContLecteurs);
        return ContLecteurs;
    }

    
    public void demanderEcriture() {
        entiteLibre.acquireUninterruptibly();
    }

    
    public void finEcriture() {
        
        entiteLibre.release();
    }

    // the number of active readers
    private int ContLecteurs;

    
    private final Data data;
    Semaphore mutex;
    Semaphore entiteLibre;
    

    private static final int TEMP_SIESTE = 5;

    /**
     * Bouchon représentant une simulation de données
     */
    class Data {

        int elt1, elt2;

        Data(int pairElt1, int pairElt2) {
            elt1 = pairElt1;
            elt2 = pairElt2;
        }

        int getElt1() {
            return elt1;
        }

        int getElt2() {
            return elt2;
        }

        void setElt1(int elt1) {
            this.elt1 = elt1;
        }

        void setElt2(int elt2) {
            this.elt2 = elt2;
        }
    }
}
