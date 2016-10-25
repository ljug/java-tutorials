/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author pascalfares
 */
@Path("rs")
public class RestC {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestC
     */
    public RestC() {
    }

    /**
     * Retrieves representation of an instance of net.cofares.RestC
     * @return an instance of java.lang.String
     */
    @GET
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON,  MediaType.APPLICATION_XML} )   
    public String getJson() {
        return "Hello ...";
    }

    /**
     * PUT method for updating or creating an instance of RestC
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String putJson(String content) {
         return "Hello ..."+content;
    }
}
