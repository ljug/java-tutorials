/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author pascalfares
 */
@Path("r")
public class Resources {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Resources
     */
    public Resources() {
    }

    /**
     * Retrieves representation of an instance of net.cofares.resources.Resources
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Ca marche!";
    }

    @GET
    @Path("/new")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public TestC getData() {
        return new TestC();
    }
    /**
     * PUT method for updating or creating an instance of Resources
     * @param content representation for the resource
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public String putJson(String content) {
        return "\n<\n"+content+"\n>\n";
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public TestC postData(TestC content) {
        content.setX(content.getX()+1);
        content.setY(content.getY()+1);
        content.setComment("\nx=x+1 et y=y+1\n");
        return content;
    }
}
