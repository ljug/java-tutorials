package entities;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_TABLE")
public class Order {

    @Id
    @Column(name = "ORDER_ID")
    private int id;
    
    @Column(name = "SHIPPING_ADDRESS")
    private String address;

    // This is unidirectional
    //private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
