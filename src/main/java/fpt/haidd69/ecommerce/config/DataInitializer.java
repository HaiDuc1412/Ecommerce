package fpt.haidd69.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import fpt.haidd69.ecommerce.entities.User;
import fpt.haidd69.ecommerce.enums.Role;
import fpt.haidd69.ecommerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Database initializer that runs on application startup. Creates default users
 * and products if they don't exist.
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.init.admin.password:Admin@123}")
    private String adminPassword;

    @Value("${app.init.customer.password:Customer@123}")
    private String customerPassword;

    @Bean
    @SuppressWarnings("unused")
    CommandLineRunner initDatabase() {
        return args -> {
            log.info("=== Starting database initialization ===");
            initUsers();
            log.info("=== Database initialization completed ===");
        };
    }

    /**
     * Initialize default users for testing
     */
    private void initUsers() {
        String adminEmail = "admin@ecommerce.com";
        String customerEmail = "customer@ecommerce.com";

        // Create admin user if not exists
        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = User.builder()
                    .email(adminEmail)
                    .password(passwordEncoder.encode(adminPassword))
                    .fullName("System Administrator")
                    .phone("0901234567")
                    .role(Role.ADMIN)
                    .active(true)
                    .build();

            userRepository.save(admin);
            log.info("Created default admin user: {}", adminEmail);
        }

        // Create customer user if not exists
        if (!userRepository.existsByEmail(customerEmail)) {
            User customer = User.builder()
                    .email(customerEmail)
                    .password(passwordEncoder.encode(customerPassword))
                    .fullName("Test Customer")
                    .phone("0987654321")
                    .role(Role.CUSTOMER)
                    .active(true)
                    .build();

            userRepository.save(customer);
            log.info("Created default customer user: {}", customerEmail);
        }
    }
}
