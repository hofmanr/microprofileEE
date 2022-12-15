package nl.rhofman.mpj.service.ping.boundary;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class StartupMPService {

    public void initOnStart(@Observes @Initialized(ApplicationScoped.class) Object object) {
        System.out.println("CDI: StartupMPService.initOnStart()");
    }
}
