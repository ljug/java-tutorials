/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lb.edu.isae.dataframe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author Acer
 */
public class DataFrameTest {
    
    public DataFrameTest() {
    }

    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of insert method, of class DataFrame.
     */

    @Disabled
    public void testInsert() {
        System.out.println("insert");
        String[] ligne = null;
        DataFrame instance = null;
        instance.insert(ligne);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of select method, of class DataFrame.
     */
    @Disabled
    public void testSelect() {
        System.out.println("select");
        List<String> projection = null;
        HashMap<String, Function<?, Boolean>> criteres = null;
        DataFrame instance = null;
        List expResult = null;
        List result = instance.select(projection, criteres);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of _afficheLigne method, of class DataFrame.
     */
    @Test
    public void test_afficheLigne() {
        System.out.println("_afficheLigne");
        List<String> ligne = Arrays.asList("AAA","BBB", "CCC");
        DataFrame instance = new DataFrame("AAA","BBB", "CCC");
        String expResult = "\tAAA\tBBB\tCCC";
        String result = instance._afficheLigne(ligne);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of _afficheLignes method, of class DataFrame.
     */
    @Test
    @Disabled
    public void test_afficheLignes() {
        System.out.println("_afficheLignes");
        List<List<String>> lignes = null;
        DataFrame instance = new DataFrame("AAA","BBB", "CCC");;
        String expResult = "";
        String result = instance._afficheLignes(lignes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DataFrame.
     */
    

    @Test
    @Disabled
    public void testToString() {
        System.out.println("toString");
        DataFrame instance = new DataFrame("AAA","BBB", "CCC");
        instance.insert("a","b", "c");
        instance.insert("a2","b2", "c2");
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
