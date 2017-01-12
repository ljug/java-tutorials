package net.cofares.lecteurredacteur;

import net.cofares.data.Database;

/**
 * Lecteur.java Lecture depuis l'entité de persitence
 *
 * @author Pascal Fares ISSAE Cnam Liban
 */
public class Lecteur extends Thread {

    public Lecteur(int r, Database db) {
        lecteurNum = r;
        server = db;
    }

    @Override
    public void run() {
        int c;

        while (true) {
            //System.out.println("lecteur " + lecteurNum + " is sleeping.");
            Database.sieste();

            System.out.println("lecteur " + lecteurNum + " veut lire.");
            c = server.demanderLecture();
            //Après l'accès en lecture nous avons la propriété suivante
            // []((nbLecteur >=1) && (nbRedacteur==0)) 
            System.out.println("lecteur " + lecteurNum + " nombre lecteur = " + c);
            Database.sieste();

            System.out.println("lecteur " + lecteurNum + " lit. " + server.lire());

            c = server.finLecture();
            System.out.println("lecteur " + lecteurNum + " a fini de lire . Count = " + c);
        }
    }

    private Database server;
    private int lecteurNum;
}
