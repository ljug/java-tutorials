package exempleetformat;

/**
 *
 * @author pascalfares
 */
public class ParRunnable implements Runnable {

    @Override
    public void run() {

        for (int i = 0; i < 500; i++) {

            System.out.println("ParRunnable, en " + i);
            /*
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            */
        }

        System.out.println("ParRunnable se termine");

    }

    public static void main(String[] args)
            throws InterruptedException {

        ParRunnable cible = new ParRunnable();

        Thread t = new Thread(cible);
        t.start();

        for (int i = 0; i < 500; i++) {

            System.out.println("Initial, en " + i);

            //Thread.sleep(500);

        }

        System.out.println("Initial se termine");
    }
}
