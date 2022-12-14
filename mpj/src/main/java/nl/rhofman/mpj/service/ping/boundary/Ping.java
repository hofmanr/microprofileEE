package nl.rhofman.mpj.service.ping.boundary;

import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class Ping {
    public int latency;
//    @Schema(readOnly = true)
    @Size(min = 2, max = 10)
    public String name;

    public Ping(int latency) {
        this.latency = latency;
        this.name = "juke";
    }

    public Ping() {
        // default constructor for JSON-B
    }
}
