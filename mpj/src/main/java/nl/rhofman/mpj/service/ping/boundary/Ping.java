package nl.rhofman.mpj.service.ping.boundary;

public class Ping {
    public int latency;
    public String name;

    public Ping(int latency) {
        this.latency = latency;
        this.name = "juke";
    }
}
