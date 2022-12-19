package nl.rhofman.mpj.service.ping.boundary;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
public class StartupService {

    // PostConstruct is possible with MicroProfile and Jakarta EE
    @PostConstruct
    public void initOnStart() {
        System.out.println("StartupService.initOnStart()");
    }
}
