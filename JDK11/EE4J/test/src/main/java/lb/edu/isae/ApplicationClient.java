package lb.edu.isae;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/res")
public class ApplicationClient extends Application {
	private Set<Object> singletons = new HashSet<Object>();

	public ApplicationClient() {
		singletons.add(new MyResource());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
