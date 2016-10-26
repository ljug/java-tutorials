/*
 * Copyright © <copyright holders>
 * ISSAE - Cnam Liban
 * Pascal Fares
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), 
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions: 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * The Software is provided “as is”, without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, 
 * fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders be liable for any claim, damages or other liability, 
 * whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the Software. »
 */
package runany;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pascalfares
 */
public class RunAny {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        List<String> command = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            command.add(args[i]);
        }

        ProcessBuilder builder = new ProcessBuilder(command);
        Map<String, String> environ = builder.environment();
        
        //Les variables d'environnement : hérités du Système d'exploitation
        for (Map.Entry<String, String> entry : environ.entrySet()) {
            System.out.println("clé=" + entry.getKey() + "\t\t\tvaleur=" + entry.getValue());
        }
        
        final Process process = builder.start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("Fin du processus ... ");
    }

}
