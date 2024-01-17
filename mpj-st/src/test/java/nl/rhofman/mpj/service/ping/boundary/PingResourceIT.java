package nl.rhofman.mpj.service.ping.boundary;
import java.net.URI;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Rinus Hofman
 */
public class PingResourceIT {

    private PingResourceClient client;

    @BeforeEach
    public void init() {
        URI uri = URI.create("http://localhost:9080/mpj/api/");
        this.client = RestClientBuilder.
                newBuilder().
                baseUri(uri).
                build(PingResourceClient.class);

    }

    @Test
    public void ping() {
        Response response = this.client.ping();
        int status = response.getStatus();
        assertEquals(200, status);
        Ping ping = response.readEntity(Ping.class);
        assertNotNull(ping);
        System.out.println(ping);
    }
}