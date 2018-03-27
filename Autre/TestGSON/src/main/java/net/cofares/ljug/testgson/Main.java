/*
 * export CLASSPATH=~/.m2/repository/com/google/code/gson/gson/2.8.2/gson-2.8.2.jar:~/.m2/repository/net/cofares/ljug/TestGSON/1.0/TestGSON-1.0.jar
 * java net.cofares.ljug.testgson.Main
 * Ou mvn clean compile assembly:single rn ayant ajouter le necessaire dans <build> ... </build> du pom.xml
 */
package net.cofares.ljug.testgson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author pfares
 */
public class Main {

    public static void main(String args[]) {
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";
        GsonBuilder builder = new GsonBuilder();

        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Student student = gson.fromJson(jsonString, Student.class);

        System.out.println(student);
        jsonString = gson.toJson(student);

        System.out.println(jsonString);
    }

}
