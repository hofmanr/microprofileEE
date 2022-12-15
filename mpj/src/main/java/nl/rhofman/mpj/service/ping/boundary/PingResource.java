package nl.rhofman.mpj.service.ping.boundary;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Timed;

/**
 *
 * @author airhacks.com
 */
@Path("ping")
public class PingResource {

    @Inject
    @ConfigProperty(name = "message")
    String message;    

    @Timed(name = "get_ping", absolute = true)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Ping ping() {
        return new Ping(13);
//        return this.message + " Jakarta EE 9 with MicroProfile 5!";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(@Valid Ping ping) {
        System.out.println("---ping " + ping);
    }

}
