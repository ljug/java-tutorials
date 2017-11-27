package lb.edu.isae;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/resources")
public class ApplicationClient extends Application {
	private Set<Object> singletons = new HashSet<Object>();

	public ApplicationClient() {
		singletons.add(new ResourceClient());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
