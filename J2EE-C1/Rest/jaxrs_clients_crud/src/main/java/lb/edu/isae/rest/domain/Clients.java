package lb.edu.isae.rest.domain;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Listes des clients
 * @author pfares
 */
@XmlRootElement(name = "customers")
public class Clients {
	
	protected Collection<Client> customers;

	@XmlElementRef
	public Collection<Client> getCustomers() {
		return customers;
	}

	public void setCustomers(Collection<Client> customers) {
		this.customers = customers;
	}
	
}
