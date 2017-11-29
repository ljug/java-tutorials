package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Customer implements Serializable {

    @Id
    private int id;
    
    private String name;

    // @OneToOne annotation is default
    private Order order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }


}
