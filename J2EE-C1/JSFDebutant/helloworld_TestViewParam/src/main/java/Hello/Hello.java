package Hello;

import java.io.Serializable;
import javax.inject.Named;



@Named("my_hello")
public class Hello implements Serializable{
    private String world = "Hello World!";

    public String getworld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }
}
