package nl.rhofman.mpj.service.ping.boundary;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Timed;
import jakarta.annotation.security.RolesAllowed;

import java.security.Principal;

/**
 *
 * @author rhofman
 */
@Path("ping")
public class PingResource {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    Principal principal;

    @Timed(name = "get_ping", absolute = true)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Ping ping() {
        return new Ping(13);
    }

    @GET
    @Path("message")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({ "admin", "user" })
    public String getMessage(@Context SecurityContext sc) {
        String fullName = ((CustomPrincipal) sc.getUserPrincipal()).getFullName();
        // We can't do '(MyPrincipal) principle'
        return this.message + " Jakarta EE 9 with MicroProfile 5! " + this.principal.getName() + " full name " + fullName;
    }

    // Valid is BeanValidation and belongs to Jakarta EE and not MicroProfile; Quarkus also supports BeanValidation
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
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
