package net.cofares.lecteurredacteur;


import net.cofares.data.Database;

/**
 * Redacteur.java

 A redacteur to the database.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */

public class Redacteur extends Thread
{
   public Redacteur(int w, Database db)
   {
      redacteurNum = w;
      donneesAEcrire=w*10;
      server = db;
   }

   public void run()
   {
     while (true)
      {
       System.out.println("redacteur " + redacteurNum + " dort.");
       Database.sieste();
       
       System.out.println("redacteur " + redacteurNum + " souhaite ecrire.");
       server.demanderEcriture();
       
       // you have access to write to the database
       System.out.println("redacteur " + redacteurNum + " écrit.");
       Database.sieste();
       server.ecrire(donneesAEcrire,donneesAEcrire+1);

       server.finEcriture();
       System.out.println("redacteur " + redacteurNum + " a fini d'écrire.");
      }
   }
   
   private Database  server;
   private int       redacteurNum;
   private int donneesAEcrire;
}
