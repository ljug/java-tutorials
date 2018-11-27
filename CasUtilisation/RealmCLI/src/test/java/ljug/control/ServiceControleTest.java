package ljug.control;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import ljug.provide.ServiceFactory;
import ljug.provide.Services;
import ljug.util.JDBCUtil;
import ljug.util.JDBCUtilFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author pfares
 */
public class ServiceControleTest {
   
    public ServiceControleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        JDBCUtil jdbcUtil= JDBCUtilFactory.create("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/?useSSL=false", "realm-ms", "realm-ms");
        jdbcUtil.startStatement();
        try {
            //S'assurer que la BD existe (Les tables seront crée par JPA)
            jdbcUtil.executeSQLCommand("create database if not exists realm");
        } catch (Exception ex) {
            Logger.getLogger(ServiceControleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        jdbcUtil.endStatement();
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
     * Test of getEntityManager method, of class ServiceControle.
     */
    

    /**
     * Test of addUser method, of class ServiceControle.
     */
    @org.junit.Test
    public void testAny() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("REALMPU");
        
        Services service = ServiceFactory.ServicesBuilder(emf);
        
        service.addUser("pascal", "pascal", null);
        assertNotNull(service.getUser("pascal"));
        service.rmUser("pascal");
        assertNull(service.getUser("pascal"));
        service.addRole("admin");
        assertNotNull(service.getRole("admin"));
        service.rmtRole("admin");
        assertNull(service.getRole("admin"));
        /*
        service.associate("pascal", "admin");
        service.associate("pascal", "prof");
        service.associate("pascal1", "prof");
        
        service.dessociate("pascal1", "prof");
        
        service.rmUser("pascal");
        */
        
    }

   
    
}
