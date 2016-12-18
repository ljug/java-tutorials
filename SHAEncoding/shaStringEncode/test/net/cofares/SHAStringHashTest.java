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
package net.cofares;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pascalfares
 */
public class SHAStringHashTest {
    
    public SHAStringHashTest() {
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
     * Test of convertToHexString method, of class SHAStringHash.
     */
    @Test
    public void testConvertToHexString_String() {
        System.out.println("convertToHexString");
        String str = "pascal";
        String expResult = "826ecad4ae11c8196ab3432ccbb22400691c248131b97fa4fe6f02dcf20f6049";
        String result = SHAStringHash.convertToHexString(str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of convertToHexString2 method, of class SHAStringHash.
     */
    @Test
    public void testConvertToHexString2() {
        System.out.println("convertToHexString2");
        String str = "pascal";
        String expResult = "826ecad4ae11c8196ab3432ccbb22400691c248131b97fa4fe6f02dcf20f6049";
        String result = SHAStringHash.convertToHexString2(str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of convertToHexString method, of class SHAStringHash.
     */
    @Test
    public void testConvertToHexString_String_String() throws Exception {
        System.out.println("convertToHexString");
        String str = "pasca";
        String algo = "sha1";
        String expResult = "de9b8d32d21ac4abee992cab0591365746b5103f";
        String result = SHAStringHash.convertToHexString(str, algo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of convertToHexStringV2 method, of class SHAStringHash.
     */
    @Test
    public void testConvertToHexStringV2() throws Exception {
        System.out.println("convertToHexStringV2");
        String str = "pascal";
        String algo = "sha1";
        String expResult = "de9b8d32d21ac4abee992cab0591365746b5103f";
        String result = SHAStringHash.convertToHexStringV2(str, algo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class SHAStringHash.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] a = {"pascal"};
        SHAStringHash.main(a);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
