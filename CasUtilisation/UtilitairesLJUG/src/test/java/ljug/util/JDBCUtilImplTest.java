/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug.util;

import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author ppfar
 */
public class JDBCUtilImplTest {
    static JDBCUtil jdt;
    public JDBCUtilImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        jdt = JDBCUtilFactory.create();
        jdt.startStatement();
        jdt.executeSQLCommand("create database IF NOT EXISTS newDatabase");        
    }
    
    @AfterClass
    public static void tearDownClass() {
        jdt.endStatement();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setup method, of class JDBCUtilImpl.
     */
    @Test
    @Ignore
    public void testSetup() {
        System.out.println("setup");
        String sql = "";
        JDBCUtilImpl instance = null;
        instance.setup(sql);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startStatement method, of class JDBCUtilImpl.
     */
    @Test
    @Ignore
    public void testStartStatement() {
        System.out.println("startStatement");
        JDBCUtilImpl instance = null;
        instance.startStatement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endStatement method, of class JDBCUtilImpl.
     */
    @Test
    @Ignore
    public void testEndStatement() {
        System.out.println("endStatement");
        JDBCUtilImpl instance = null;
        instance.endStatement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of droptable method, of class JDBCUtilImpl.
     */
    @Test
    @Ignore
    public void testDroptable() throws Exception {
        System.out.println("droptable");
        
    }

    /**
     * Test of executeSQLCommand method, of class JDBCUtilImpl.
     */
    @Test
    public void testExecuteSQLCommand() throws Exception {
        System.out.println("executeSQLCommand");
        jdt.executeSQLCommand("create table IF NOT EXISTS newDatabase.Persons ("
                + "    PersonID int,"
                + "    LastName varchar(255),"
                + "    FirstName varchar(255),"
                + "    Address varchar(255),"
                + "    City varchar(255)"
                + ")");
        jdt.executeSQLCommand("INSERT INTO newDatabase.Persons ("
                + "    PersonID,"
                + "    LastName,"
                + "    FirstName,"
                + "    Address,"
                + "    City) VALUES (1,\'Fares\',\'Pascal\',\'Une adresse\',\'Baino\')");
    }

    /**
     * Test of checkData method, of class JDBCUtilImpl.
     */
    @Test
    public void testCheckData() {
        System.out.println("checkData");
        jdt.checkData("select * from newDatabase.Persons");
    }

    /**
     * Test of outputResultSet method, of class JDBCUtilImpl.
     */
    @Test
    @Ignore
    public void testOutputResultSet() throws Exception {
        System.out.println("outputResultSet");
        ResultSet rs = null;
        JDBCUtilImpl instance = null;
        instance.outputResultSet(rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of overwrite method, of class JDBCUtilImpl.
     */
    @Test
    @Ignore
    public void testOverwrite() {
        System.out.println("overwrite");
        StringBuffer b = null;
        int pos = 0;
        String s = "";
        JDBCUtilImpl instance = null;
        instance.overwrite(b, pos, s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
