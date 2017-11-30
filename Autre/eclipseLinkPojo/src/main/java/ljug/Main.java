package ljug;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import lb.edu.isae.entites.Users;
import lb.edu.isae.entites.UsersJpaController;
import util.JPAUtil;



public class Main {

    public static void main(String[] args) throws Exception {

        // Perform JPA operations
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("lb.edu.isae_mavenproject1_jar_1.0-SNAPSHOTPU");
        
        UsersJpaController uc = new UsersJpaController(emf);
        uc.createorUpdate(new Users("pascalfares3210", "chef", 0, true));
        uc.createorUpdate(new Users("pascalfares3211", "chef", 0, true));
        uc.createorUpdate(new Users("pascalfares3212", "chef", 0, true));
        uc.createorUpdate(new Users("pascalfares3213", "etud", 0, true));
        uc.createorUpdate(new Users("pascalfares3214", "prof", 0, true));
        uc.createorUpdate(new Users("pascalfares3215", "emp", 0, true));
        List<Users> lu = uc.findUsersEntities(100, 1);
        
        lu.stream().forEach((x) -> System.out.println(x));

        JPAUtil.checkData("select * from testdb.exo1_users");
    }
}
