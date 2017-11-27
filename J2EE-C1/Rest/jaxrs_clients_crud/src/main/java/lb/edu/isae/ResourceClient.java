package lb.edu.isae;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import lb.edu.isae.rest.domain.Client;
import lb.edu.isae.rest.domain.Clients;

@Path("/clients")
public class ResourceClient {

    private final Map<Integer, Client> customerDB = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger();

    public ResourceClient() {
        Client customer = new Client();
        customer.setId(idCounter.incrementAndGet());
        customer.setFirstName("Pascal");
        customer.setLastName("Fares");
        customer.setStreet("10 rue Cnam");
        customer.setCity("Jounieh");
        customer.setState("Keserwan");
        customer.setZip("03240246");
        customer.setCountry("LB");
        customerDB.put(customer.getId(), customer);

        customer = new Client();
        customer.setId(idCounter.incrementAndGet());
        customer.setFirstName("Emile");
        customer.setLastName("Fares");
        customer.setStreet("10 rue Cnam");
        customer.setCity("Jounieh");
        customer.setState("Keserwan");
        customer.setZip("03240246");
        customer.setCountry("LB");
        customerDB.put(customer.getId(), customer);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public Clients getCustomers() {

        ArrayList<Client> customerList = new ArrayList<>();
        synchronized (customerDB) {
            customerDB.values().forEach(customer -> customerList.add(customer));
        }

        Clients customers = new Clients();
        customers.setCustomers(customerList);
        return customers;
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Client getCustomer(@PathParam("id") int id) {

        Client customer = customerDB.get(id);
        if (customer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return customer;
    }

    @Context
    UriInfo uriInfo;

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response createCustomer(Client customer) {
        customer.setId(idCounter.incrementAndGet());
        customerDB.put(customer.getId(), customer);
        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
        uriBuilder.path("{id}");
        return Response.created(uriBuilder.build(customer.getId())).build();

    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public Response updateCustomer(@PathParam("id") int id, Client update) {
        Client customer = customerDB.get(id);
        if (customer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        customer.setFirstName(update.getFirstName());
        customer.setLastName(update.getLastName());
        customer.setStreet(update.getStreet());
        customer.setState(update.getState());
        customer.setZip(update.getZip());
        customer.setCountry(update.getCountry());
        customerDB.put(id, customer);

        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
        uriBuilder.path("{id}");
        return Response.status(204).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCustomer(@PathParam("id") int id) {

        Client customer = customerDB.get(id);
        if (customer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        customerDB.remove(id);
        Response.ResponseBuilder builder = Response.ok();
        return builder.build();
    }

    @GET
    @Path("numerOfCustomers")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = customerDB.size();
        return String.valueOf(count);
    }
}
