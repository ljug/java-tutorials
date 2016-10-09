/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exempleetformat;

/**
 *
 * @author pascalfares
 */
public class ThreadExampleSimple {

    public static void main(String[] args) throws InterruptedException {
        Thread t = Thread.currentThread();
        // Affiche caractéristiques de la thread courante 
        System.out.println(t);
        // Changer le nom :  donne un nouveau nom 
        t.setName("ISSAE");
        System.out.println(t);
        // Rend le processus léger courant 
        // inactif pendant 1 seconde 1000ms
        Thread.sleep(1000);
        System.out.println("fin");
    }
}
