package net.cofares.lecteurredacteur;

import net.cofares.data.Database;

/**
 * Redacteur.java redacteur dans l'entité de persitence
 *
 * @author Pascal Fares ISSAE Cnam Liban
 */
public class Redacteur extends Thread {

    public Redacteur(int w, Database db) {
        redacteurNum = w;
        donneesAEcrire = w * 10;
        server = db;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("redacteur " + redacteurNum + " dort.");
            Database.sieste();

            System.out.println("redacteur " + redacteurNum + " souhaite ecrire.");
            server.demanderEcriture();
            //Après l'accès en ecriture nous avons la propriété suivante
            // []((nbLecteur==0) && (nbRedacteur==1)) 

            System.out.println("redacteur " + redacteurNum + " écrit.["+donneesAEcrire+":"+(donneesAEcrire + 1)+"]");
            Database.sieste();
            server.ecrire(donneesAEcrire, donneesAEcrire + 1);

            server.finEcriture();
            System.out.println("redacteur " + redacteurNum + " a fini d'écrire.");
        }
    }

    private Database server;
    private int redacteurNum;
    private int donneesAEcrire;
}
