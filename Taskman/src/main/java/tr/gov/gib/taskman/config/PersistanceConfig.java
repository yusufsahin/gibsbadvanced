package tr.gov.gib.taskman.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tr.gov.gib.taskman.audit.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EntityScan(basePackages = {"tr.gov.gib.taskman.dao.model"})
@EnableJpaRepositories(basePackages = "tr.gov.gib.taskman.dao")
public class PersistanceConfig {

    @Bean
    AuditorAware<String> auditorProvider()
    {
        return  new AuditorAwareImpl();
    }
}
