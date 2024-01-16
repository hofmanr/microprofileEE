package nl.rhofman.mpj.service.ping.boundary;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.annotation.RegistryType;

import java.time.LocalDateTime;
import java.util.Optional;

//@ApplicationScoped  // CDI, MicroProfile and Jakarta EE
@Startup
@Singleton  // EJB, Jakarta EE
@ServerEndpoint("/notifications") // WebSockets, Jakarta EE
public class AppNotifications {

    private Optional<Session> session = Optional.empty();

    @Inject
    @RegistryType(type = MetricRegistry.Type.APPLICATION)
    private MetricRegistry registry;

    @PostConstruct
    private void init() {
        // Initialize counters
        registry.counter("broadcasting_to_listeners").inc(0L);
        registry.counter("send_alive_messages").inc(0L);
    }

    @OnOpen
    public void onConnect(Session session) {
        this.session = Optional.of(session);
        System.out.println("AppNotifications.onConnect() " + session);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("AppNotifications.onClose() " + session);
        this.session = Optional.empty();
    }

    @OnMessage
    public void onMessage(String message) {
        // To send messages back
        this.session.map(Session::getAsyncRemote).ifPresent(r -> r.sendText("echo " + message));
    }

    // Every hour, minute and every 5 seconds
    @Schedule(persistent = false, hour="*", minute = "*", second = "*/5") // We can do this because it's an EJB
    public void sendPing() {
        registry.counter("broadcasting_to_listeners").inc();
        System.out.println("AppNotifications.sendPing() " + System.currentTimeMillis());

        if (session.isPresent()) {
            registry.counter("send_alive_messages").inc();
            this.session.map(Session::getAsyncRemote).ifPresent(r -> r.sendText("ping " + LocalDateTime.now()));
        }
    }
}
