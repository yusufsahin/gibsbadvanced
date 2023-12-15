package tr.gov.gib.taskman.config;
import org.javers.spring.auditable.AuthorProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tr.gov.gib.taskman.audit.AuditorAwareImpl;


@Configuration
public class JaversConfiguration {
    private AuditorAwareImpl auditorAwareImpl;

    @Bean
    public AuthorProvider provideJaversAuthor() {

        return () -> {
            return AuthenticatedUser.getCurrentUser().toString();
        };

    }

}

