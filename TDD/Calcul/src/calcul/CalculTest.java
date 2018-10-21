/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcul;

import calcul.Calcul;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.JUnitCore;

/**
 *
 * @author pfares
 */
public class CalculTest {
    
    public CalculTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    

    /**
     * Test of add method, of class Calcul.
     */
    @Test
    public void add() {
        System.out.println("add qui imprime?");
        int x = 0;
        int y = 0;
        Calcul instance = new Calcul();
        int expResult = 0;
        int result = instance.add(x, y);
        assertEquals("Pas bon add", expResult, result);
        System.out.println("Fin test add??");
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of sub method, of class Calcul.
     */
    @Test
    public void sub() {
        System.out.println("sub");
        int x = 0;
        int y = 0;
        Calcul instance = new Calcul();
        int expResult = 1;
        int result = instance.sub(x, y);
        assertEquals("Pas bon sub", expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of mult method, of class Calcul.
     */
    @Test
    @Ignore
    public void mult() {
        System.out.println("mult");
        Integer x = null;
        Integer y = null;
        Calcul instance = new Calcul();
        Integer expResult = null;
        Integer result = instance.mult(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of div method, of class Calcul.
     */
    @Test
    @Ignore
    public void div() {
        System.out.println("div");
        Integer x = null;
        Integer y = null;
        Calcul instance = new Calcul();
        Integer expResult = null;
        Integer result = instance.div(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
  
    
}
