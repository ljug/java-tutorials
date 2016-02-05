/*
 * fichier de propriétés en Java 
 */
package props;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pascalfares
 */
public class Props {

    /**
     * @param args the command line arguments 
     */
    public static void main(String[] args) {
       //Read system properties (au sens de Java)
        Properties sprop = System.getProperties();
        
        String propFile = sprop.getProperty("config.file");
        
        System.out.println("config.file="+propFile);
        
        if(args.length > 0) {
            //Get the envirement properties (au sens du Systeme d'exploitation)
            //Args[0] correspondrais a une variable d'environement système
             System.out.println(args[0]+"="+System.getenv(args[0]));
        }
        //Read my own property file
        
        Properties myprop = new Properties();
        try {
            myprop.load(new FileInputStream(propFile));
            System.out.println("aprop="+myprop.getProperty("aprop"));
        } catch (IOException ex) {
            //Probablement le fichier n'esxite pas ou la référence est incorrete
            Logger.getLogger(Props.class.getName()).log(Level.SEVERE, "propFile="+propFile, ex);
        }
        
        
    }
    
}
