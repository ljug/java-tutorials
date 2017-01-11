package net.cofares;

import net.cofares.lecteurredacteur.Lecteur;
import net.cofares.lecteurredacteur.Redacteur;
import net.cofares.data.Database;

/**
 * ServiceLecteurRedacteur.java

 This class creates the reader and writer threads and the database they will
 be using to coordinate access.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999 Copyright 2000 by Greg Gagne, Peter Galvin, Avi
 * Silberschatz Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 *
 * @author Pascal Fares ISSAE Cnam Liban modification et adaptation
 */
public class ServiceLecteurRedacteur {

    public static void main(String args[]) {
        Database server = new Database();

        Lecteur[] TabLecteurs = new Lecteur[N_LECTEURS];
        Redacteur[] TabRedacteurs = new Redacteur[N_REDACTEURS];

        for (int i = 0; i < N_LECTEURS; i++) {
            TabLecteurs[i] = new Lecteur(i, server);
            TabLecteurs[i].start();
        }

        for (int i = 0; i < N_REDACTEURS; i++) {
            TabRedacteurs[i] = new Redacteur(i, server);
            TabRedacteurs[i].start();
        }
    }

    private static final int N_LECTEURS = 3;
    private static final int N_REDACTEURS = 2;
}
