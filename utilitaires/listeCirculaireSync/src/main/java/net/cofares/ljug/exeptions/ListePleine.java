/**
 * 
 * <a href="http://rosenlaw.com/pdf-files/OSL3.0-comparison.pdf">Open Software License (OSL) v. 3.0</a>
*/
package net.cofares.ljug.exeptions;

/**
 *
 * @author ppfar
 */
public class ListePleine extends Exception {

    /**
     * Creates a new instance of <code>ListePleine</code> without detail
     * message.
     */
    public ListePleine() {
    }

    /**
     * Constructs an instance of <code>ListePleine</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ListePleine(String msg) {
        super(msg);
    }
}
