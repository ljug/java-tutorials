/*
 * Copyright © <Pascal Fares @ ISSAE - Cnam Liban>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), 
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions: 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * The Software is provided “as is”, without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, 
 * fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders be liable for any claim, damages or other liability, 
 * whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the Software. »
 */
package e02;

import java.util.concurrent.TimeUnit;
import net.cofares.recursiveTasks.TailleDir;
import net.cofares.utils.FilePath;
import net.cofares.utils.Results;

/**
 *
 * @author pascalfares
 */
public class E02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Results results = new Results();
    for (int i = 0; i < 5; i++) {
      results.startTime();
      final long size = TailleDir.sizeOf(FilePath.TEST_DIR);
      if (TailleDir.DEBUG) System.out.printf("Fichier a lire ets %s",FilePath.getFile(null));
      final long taken = results.endTime();
      System.out.printf("Size of '%s': %d bytes (in %d nano)%n", FilePath.TEST_DIR, size, taken);
    }

    final long takenInNano = results.getAverageTime();
    if (TailleDir.DEBUG) System.out.printf("Average: %d nano (%d seconds)", takenInNano, TimeUnit.NANOSECONDS.toSeconds(takenInNano));
    }
    
}
