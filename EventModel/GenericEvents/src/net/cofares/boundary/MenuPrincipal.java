/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.boundary;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.cofares.GEvent;
import net.cofares.Source;

/**
 *
 * @author pfares
 */
public class MenuPrincipal implements Runnable {

    Source eventManager;

    public MenuPrincipal(Source<String> s) {
        eventManager = s;
    }

    public int menu() throws IOException {

        int selection;
        //Ceci efface en principe un écran (console) Linux et Windows
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
        Scanner input = new Scanner(System.in);

        /**
         * ************************************************
         */
        System.out.println("0 - Quitter");
        System.out.println("-------------------------");
        System.out.println("1 - Créer");
        System.out.println("2 - Mise à jour");
        System.out.println("3 - Suprimer");
        System.out.println("4 - Consulter");
        System.out.print("Choisir : ");

        selection = input.nextInt();
        return selection;
    }

    @Override
    public void run() {
        int choix;
        GEvent ev=eventManager.genEvent("Aucune réponse connue");
        try {
            while ((choix = menu()) != 0) {
                switch (choix) {
                    case 1:
                        ev = eventManager.genEvent("Créer");
                        break;
                    case 2:
                        ev = eventManager.genEvent("MAJ");
                        break;
                }
                if (ev != null) {
                    eventManager.dispatch(ev);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
