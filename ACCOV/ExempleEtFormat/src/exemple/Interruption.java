/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemple;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pascalfares
 */
public class Interruption implements Runnable {

    private int id;

    public Interruption(int id) {
        this.id = id;
    }

    public void run() {
        int i = 0;
        while (!Thread.interrupted()) {
            System.out.println(i + "i° exécution de " + id);
            i++;
        }
        System.out.println("Fin d'exéc. du code " + id);
// L'appel à interrupted() a réinitialisé // le statut d'interruption 
        System.out.println(Thread.currentThread().isInterrupted()); // Affiche: false 
    }

    public static void main(String[] args) throws IOException {
        Thread t1 = new Thread(new Interruption(1));
        Thread t2 = new Thread(new Interruption(2));
        t1.start();
        t2.start();
        // Lecture du numéro du processus léger à interrompre 
        Scanner sc;
        while (true) {
            System.out.print("> ");
            sc = new Scanner(System.in);
            int res = sc.nextInt();
            System.out.println("Dans main "+res);
            switch (res) {
                case 1:
                    t1.interrupt();
                    break;
                case 2:
                    t2.interrupt();
                    break;
            }
        }
    }

}
