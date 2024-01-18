package nl.rhofman.mpj.service.ping.boundary;

import jakarta.security.enterprise.CallerPrincipal;

public class CustomPrincipal extends CallerPrincipal {
    private String fullName;

    public CustomPrincipal(String name) {
        super(name);
        if ("admin".equalsIgnoreCase(name)) {
            fullName = "Admin";
        } else {
            fullName = "Just an User";
        }
    }

    public static CustomPrincipal createMyPrincipal() {
        return new CustomPrincipal("default");
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "CustomPrincipal{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}
