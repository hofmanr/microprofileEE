package nl.rhofman.mpj.service.ping.boundary;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("ping")
public interface PingResourceClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response ping();
    
}