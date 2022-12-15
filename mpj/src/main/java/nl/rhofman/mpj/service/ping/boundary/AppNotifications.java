package nl.rhofman.mpj.service.ping.boundary;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.time.LocalDateTime;
import java.util.Optional;

//@ApplicationScoped  // CDI, MicroProfile and Jakarta EE
@Startup
@Singleton  // EJB, Jakarta EE
@ServerEndpoint("/notifications") // WebSockets, Jakarta EE
public class AppNotifications {

    private Optional<Session> session = Optional.empty();;

    @OnOpen
    public void onConnect(Session session) {
        this.session = Optional.of(session);
        System.out.println("AppNotifications.onConnect() " + session);
    }

    @OnMessage
    public void onMessage(String message) {
        // To send messages back
        this.session.map(Session::getAsyncRemote).ifPresent(r -> r.sendText("echo " +message));
    }

    // Every hour, minute and every 5 seconds
    @Schedule(persistent = false, hour="*", minute = "*", second = "*/5") // We can do this because it's an EJB
    public void sendPing() {
        System.out.println("AppNotifications.sendPing() " + System.currentTimeMillis());
        this.session.map(Session::getAsyncRemote).ifPresent(r -> r.sendText("ping " + LocalDateTime.now()));
    }
}
