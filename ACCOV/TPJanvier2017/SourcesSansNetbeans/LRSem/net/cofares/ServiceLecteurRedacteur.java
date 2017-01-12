package net.cofares;

import net.cofares.lecteurredacteur.Lecteur;
import net.cofares.lecteurredacteur.Redacteur;
import net.cofares.data.Database;

/**
 * ServiceLecteurRedacteur.java
 * Service lecteur Redacteur lancement de N_LECTEURS thread lecteurs 
 * et N_REDACTEURS redacteur 
 * @author Pascal Fares ISSAE Cnam Liban version semaphore
 * 
 */
public class ServiceLecteurRedacteur {

    public static void main(String args[]) {
        //Gestion de la synchro dans la classe Database (simule léntité persitante
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
