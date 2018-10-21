package net.cofares.ljug.tdd;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 *
 * @author pfares
 */
public class CalculetteTest {

    public CalculetteTest() {
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
     * Test of add method, of class Calculette.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        int x = 0;
        int y = 0;
        Calculette instance = new Calculette();
        int expResult = 0;
        int result = instance.add(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of sub method, of class Calculette.
     */
    @Test
    public void testSub() {
        System.out.println("sub");
        int x = 0;
        int y = 0;
        Calculette instance = new Calculette();
        int expResult = 0;
        int result = instance.sub(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of mult method, of class Calculette.
     */
    @Test
    public void testMult() {
        System.out.println("mult");
        Integer x = 0;
        Integer y = 10;
        Calculette instance = new Calculette();
        Integer expResult = 0;
        Integer result = instance.mult(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of div method, of class Calculette.
     */
    @Test
    public void testDiv() {
        System.out.println("div");
        Integer x = 100;
        Integer y = 10;
        Calculette instance = new Calculette();
        Integer expResult = 10;
        Integer result = instance.div(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CalculetteTest.class);
        result.getFailures().forEach((failure) -> {
            System.out.println(failure.toString());
        });
    }

}
