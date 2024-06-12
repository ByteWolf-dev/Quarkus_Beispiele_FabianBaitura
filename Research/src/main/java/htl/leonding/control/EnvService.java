package htl.leonding.control;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class EnvService {
    @Inject
    @ConfigProperty(name = "greeting")
    String greeting;

    public String getGreeting() {
        return greeting;
    }
}
