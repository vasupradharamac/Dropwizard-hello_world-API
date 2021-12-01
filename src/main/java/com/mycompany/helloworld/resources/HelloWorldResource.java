package com.mycompany.helloworld.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.helloworld.api.HelloWorld;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String defaultMessage;

    public HelloWorldResource(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @GET
    @Path("/hey")
    public HelloWorld sayHello() {
        return new HelloWorld(defaultMessage);
    }

    @POST
    @Path("/hey")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HelloWorld replyMessage(HelloWorld content) {
        return content;
    }
}
