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
package net.cofares.recursiveTasks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author pascalfares
 */
public class ActiviteTailleFichiers extends RecursiveTask<Long> {

    private static final long serialVersionUID = -196522408291343951L;

    private final File file;

    public ActiviteTailleFichiers (final File file) {
      this.file = Objects.requireNonNull(file);
    }

    @Override
    protected Long compute() {
      if (TailleDir.DEBUG) System.out.printf("Calcul taille de : %s%n", file.getName());

      if (file.isFile()) {
        return file.length();
      }

      final List<ActiviteTailleFichiers> tasks = new ArrayList<>();
      final File[] children = file.listFiles();
      if (children != null) {
        for (final File child : children) {
          final ActiviteTailleFichiers task = new ActiviteTailleFichiers(child);
          task.fork();
          tasks.add(task);
        }
      }

      long size = 0;
      for (final ActiviteTailleFichiers task : tasks) {
        size += task.join();
      }

      return size;
    }
    
}
