/**
 * 
 * <a href="http://rosenlaw.com/pdf-files/OSL3.0-comparison.pdf">Open Software License (OSL) v. 3.0</a>
*/
package net.cofares.ljug.exeptions;

/**
 *
 * @author Pascal Fares at ISSAE CNam Liban and OSLM
 */
public class ListeVide extends Exception {

    /**
     * Creates a new instance of <code>ListeVide</code> without detail message.
     */
    public ListeVide() {
    }

    /**
     * Constructs an instance of <code>ListeVide</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ListeVide(String msg) {
        super(msg);
    }
}
