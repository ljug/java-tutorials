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
class HeritageThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("HeritageThread , en " + i);
            /* try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println(ie);
            } */
        }
        System.out.println("HeritageThread se termine");
    }

    public static void main(String[] args)
            throws InterruptedException {
        Thread t = new HeritageThread();
        t.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("Initial main, en " + i);
            /* Thread.sleep(3);
            */
        }
        //t.join();
        System.out.println("Initial main se termine");
    }
}
