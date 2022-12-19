package nl.rhofman.mpj.service.ping.boundary;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Timed;

/**
 *
 * @author rhofman
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

    @POST
    @Path("period")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeriod(Period period) {
        System.out.println("Input Period: " + period);
        return Response.ok(period).build();
    }

}
