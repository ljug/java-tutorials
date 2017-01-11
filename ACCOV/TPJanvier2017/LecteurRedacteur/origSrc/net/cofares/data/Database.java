package net.cofares.data;

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
        existeLecteur = false;
        existeRedacteur = false;
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
        Thread.yield(); // to force problems in case not well synchronized
        data.setElt2(elt2);
    }

    // reader will call this when they start reading
    public synchronized int demanderLecture() {
        // pasDeRedacteur -> lire
        //traduit en java par
        //while (existeRedacteur) wait();
        //lire (a la sortie de wile on a !existeRedacteur
        while (existeRedacteur == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        ++ContLecteurs;

        //si je suis le premier lecteur
        if (ContLecteurs == 1) {
            existeLecteur = true;
        }

        return ContLecteurs;
    }

    // reader will call this when they finish reading
    public synchronized int finLecture() {
        --ContLecteurs;

        //Si je suis le dernier lecteur
        if (ContLecteurs == 0) {
            existeLecteur = false;
        }

        notifyAll();

        System.out.println("Reader Count = " + ContLecteurs);

        return ContLecteurs;
    }

    
    public synchronized void demanderEcriture() {
        // pasDeRedacteur et pasDeLecteur -> ecrire
        //traduit en java par
        //while (existeRedacteur ou existeLecteur) wait();
        //ecrire (a la sortie de wile on a !(existeRedacteur et existelecteur)
        //
        while (existeLecteur == true || existeRedacteur == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        //Il existe maintenant un redacteur
        existeRedacteur = true;
    }

    
    public synchronized void finEcriture() {
        existeRedacteur = false;

        notifyAll();
    }

    // the number of active readers
    private int ContLecteurs;

    // flags to indicate whether the database is
    // being read or written
    private boolean existeLecteur;
    private boolean existeRedacteur;
    private Data data;

    private static final int TEMP_SIESTE = 5;

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
