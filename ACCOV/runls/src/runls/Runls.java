package runls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.Compiler.command;

/**
 *
 * @author pascalfares
 */
public class Runls {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Process fils= Runtime.getRuntime().exec("ls -l");
        
        InputStream is = fils.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        System.out.printf("Output of running %s is:\n",
                "ls -l");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        
        //Wait to get exit value
        try {
            int exitValue = fils.waitFor();
            System.out.println("\n\nExit Value is " + exitValue);
        } catch (InterruptedException e) {
           
            e.printStackTrace();
        }
    }
    
}
