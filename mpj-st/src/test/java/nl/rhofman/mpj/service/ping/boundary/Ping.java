package nl.rhofman.mpj.service.ping.boundary;

public class Ping {
    public int latency;
    public String name;

    public Ping() {
        // default constructor for JSON-B
    }

    @Override
    public String toString() {
        return "Ping{" +
                "latency=" + latency +
                ", name='" + name + '\'' +
                '}';
    }
}
