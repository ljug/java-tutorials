package net.cofares.lecteurredacteur;

import net.cofares.data.Database;

/**
 * Lecteur.java
 *
 * A lecteur to the database.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999
 * @version 1.1 - October 2, 2001. Modified the output statements so that the
 * lecteur now outputs a message before invoking finLecture().
 *
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz Applied
 * Operating Systems Concepts - John Wiley and Sons, Inc.
 * @author Pascal Fares ISSAE Cnam Liban modification et adaptation
 */
public class Lecteur extends Thread {

    public Lecteur(int r, Database db) {
        lecteurNum = r;
        server = db;
    }

    public void run() {
        int c;

        while (true) {
            //System.out.println("lecteur " + lecteurNum + " is sleeping.");
            Database.sieste();

            System.out.println("lecteur " + lecteurNum + " veut lire.");
            c = server.demanderLecture();

            // you have access to read from the database
            System.out.println("lecteur " + lecteurNum + " lit. nombre lecteur =  = " + c);
            Database.sieste();

            System.out.println("lecteur " + lecteurNum + " lit. " + server.lire());

            c = server.finLecture();
            System.out.println("lecteur " + lecteurNum + " a fini de lire . Count = " + c);
        }
    }

    private Database server;
    private int lecteurNum;
}
