/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug.jerseyrest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author pascalfares
 */
@Path("add")
public class AddResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddResource
     */
    public AddResource() {
    }

    /**
     * Retrieves representation of an instance of net.cofares.ljug.jerseyrest.AddResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{x}/{y}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("x") int x,@PathParam("y") int y) {
        return "" + (x+y) + "\n";
    }

    /**
     * PUT method for updating or creating an instance of AddResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(String content) {
        Response.ResponseBuilder builder;
        builder = Response.ok();
        return builder.build();
    }
}
