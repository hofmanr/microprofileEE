package nl.rhofman.mpj.service.ping.boundary;

import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Set;

public class CustomAuthentication implements HttpAuthenticationMechanism {
    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpMessageContext httpMessageContext) throws AuthenticationException {
        Set<String> roles = Set.of("admin", "user");
        return httpMessageContext.notifyContainerAboutLogin(new CustomPrincipal("admin"), roles);
//        return httpMessageContext.notifyContainerAboutLogin("admin", roles);
    }
}
