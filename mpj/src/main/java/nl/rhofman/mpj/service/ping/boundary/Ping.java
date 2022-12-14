package nl.rhofman.mpj.service.ping.boundary;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class Ping {
    public int latency;
    @Schema(readOnly = true)
    public String name;

    public Ping(int latency) {
        this.latency = latency;
        this.name = "juke";
    }
}
