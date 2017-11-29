package ljug;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Customer;
import entities.Order;
import util.JPAUtil;

public class Main {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void main(String[] args) {

        emf = Persistence.createEntityManagerFactory("pu1");
        em = emf.createEntityManager();

        // Create test data
        CreateTestData_NoCascade.createTestData(em);

        // Get a customer and then navigate from the ustomer to his order
        Customer c1 = em.find(Customer.class, 1);
        System.out.println("---->Customer 1 = " + c1.getName());

        Order order = c1.getOrder();
        System.out.println("---->Delivery address of the order of the customer 1 = " + order.getAddress());

        // Get an order and then navigate from the order to the customer.
        // The following does not work since there is only uni-directional relationship
//        Order o1 = em.find(Order.class, 1);
//        Customer c2 = o1.getCustomer();
//        System.out.println("---->Customer of Order 1 = " + c2.getName());

        em.close();
        emf.close();

        // Display the tables
        JPAUtil.checkData("select * from CUSTOMER ORDER BY ID");
        JPAUtil.checkData("select * from ORDER_TABLE ORDER BY ORDER_ID");

    }
}
