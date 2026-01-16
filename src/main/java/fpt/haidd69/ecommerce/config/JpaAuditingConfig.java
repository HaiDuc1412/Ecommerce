package fpt.haidd69.ecommerce.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * JPA Auditing configuration to automatically populate createdBy and
 * lastModifiedBy fields.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new SpringSecurityAuditorAware();
    }

    /**
     * Implementation of AuditorAware that gets the current user from Spring
     * Security context.
     */
    public static class SpringSecurityAuditorAware implements AuditorAware<String> {

        @Override
        public Optional<String> getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()
                    || "anonymousUser".equals(authentication.getPrincipal())) {
                return Optional.of("system");
            }

            return Optional.of(authentication.getName());
        }
    }
}
