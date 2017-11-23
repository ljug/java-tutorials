package Hello;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="my_hello")
public class Hello {
    private String world = "Hello World!";

    public String getworld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }
}
