package fpt.haidd69.ecommerce.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * OpenAPI (Swagger) configuration.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Hung Hypebeast E-commerce API",
                version = "1.0.0",
                description = """
                ## E-Commerce Backend REST API
                
                Backend API cho hệ thống e-commerce "Hung Hypebeast" - Local Brand Fashion Store.
                
                ### Features
                - Product Catalog với filter và pagination
                - Shopping Cart management (session-based)
                - Inventory Management với reservation system
                - Order Checkout (COD payment)
                - Order Tracking (không cần login)
                - Admin APIs cho order management
                
                ### Authentication
                - Most endpoints are public (Product, Cart, Order Tracking)
                - Admin endpoints require JWT Bearer token
                - Use `/api/auth/login` to get token
                
                ### Session Management
                - Cart operations require `Session-Id` header
                - Generate a unique session ID (UUID) for each customer
                - Example: `Session-Id: 550e8400-e29b-41d4-a716-446655440000`
                """,
                contact = @Contact(
                        name = "Anh Phuong",
                        email = "support@hunghypebeast.com",
                        url = "https://github.com/your-repo"
                ),
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                )
        ),
        servers = {
            @Server(url = "http://localhost:8080", description = "Development Server")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = """
            Enter your JWT token. 
            
            To get token:
            1. Register user via `/api/auth/register`
            2. Login via `/api/auth/login`
            3. Copy the `token` from response
            4. Click 'Authorize' button above
            5. Enter token (without 'Bearer ' prefix)
            """
)
public class OpenAPIConfig {
}
