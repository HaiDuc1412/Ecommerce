package fpt.haidd69.ecommerce.config;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import fpt.haidd69.ecommerce.entities.Product;
import fpt.haidd69.ecommerce.entities.ProductVariant;
import fpt.haidd69.ecommerce.entities.User;
import fpt.haidd69.ecommerce.enums.Color;
import fpt.haidd69.ecommerce.enums.ProductCategory;
import fpt.haidd69.ecommerce.enums.Role;
import fpt.haidd69.ecommerce.enums.Size;
import fpt.haidd69.ecommerce.repositories.ProductRepository;
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
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.init.admin.password:ChangeMe123!@#}")
    private String adminPassword;

    @Value("${app.init.customer.password:ChangeMe456!@#}")
    private String customerPassword;

    @Bean
    @SuppressWarnings("unused")
    CommandLineRunner initDatabase() {
        return args -> {
            initUsers();
            initProducts();
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

    /**
     * Initialize default products for the e-commerce store
     */
    private void initProducts() {
        if (productRepository.count() == 0) {
            // T-Shirts (5 products)
            createProduct("Classic T-Shirt", "Comfortable cotton t-shirt for everyday wear",
                    ProductCategory.T_SHIRT, new BigDecimal("299000"));

            createProduct("Graphic T-Shirt", "Trendy graphic design t-shirt",
                    ProductCategory.T_SHIRT, new BigDecimal("349000"));

            createProduct("V-Neck T-Shirt", "Stylish v-neck cotton t-shirt",
                    ProductCategory.T_SHIRT, new BigDecimal("329000"));

            createProduct("Polo T-Shirt", "Classic polo style t-shirt",
                    ProductCategory.T_SHIRT, new BigDecimal("399000"));

            createProduct("Striped T-Shirt", "Casual striped pattern t-shirt",
                    ProductCategory.T_SHIRT, new BigDecimal("359000"));

            // Hoodies (4 products)
            createProduct("Premium Hoodie", "Warm and stylish hoodie with fleece lining",
                    ProductCategory.HOODIE, new BigDecimal("599000"));

            createProduct("Zip Hoodie", "Comfortable zip-up hoodie",
                    ProductCategory.HOODIE, new BigDecimal("649000"));

            createProduct("Oversized Hoodie", "Trendy oversized fit hoodie",
                    ProductCategory.HOODIE, new BigDecimal("699000"));

            createProduct("Sports Hoodie", "Athletic hoodie for active lifestyle",
                    ProductCategory.HOODIE, new BigDecimal("579000"));

            // Jackets (4 products)
            createProduct("Denim Jacket", "Classic blue denim jacket",
                    ProductCategory.JACKET, new BigDecimal("899000"));

            createProduct("Leather Jacket", "Premium leather jacket",
                    ProductCategory.JACKET, new BigDecimal("1499000"));

            createProduct("Bomber Jacket", "Stylish bomber jacket",
                    ProductCategory.JACKET, new BigDecimal("799000"));

            createProduct("Windbreaker Jacket", "Lightweight windbreaker",
                    ProductCategory.JACKET, new BigDecimal("699000"));

            // Pants (4 products)
            createProduct("Casual Pants", "Comfortable everyday pants",
                    ProductCategory.PANTS, new BigDecimal("499000"));

            createProduct("Denim Jeans", "Classic blue denim jeans",
                    ProductCategory.PANTS, new BigDecimal("599000"));

            createProduct("Cargo Pants", "Multi-pocket cargo pants",
                    ProductCategory.PANTS, new BigDecimal("549000"));

            createProduct("Chino Pants", "Smart casual chino pants",
                    ProductCategory.PANTS, new BigDecimal("529000"));

            // Accessories (5 products)
            createProduct("Baseball Cap", "Adjustable baseball cap",
                    ProductCategory.ACCESSORIES, new BigDecimal("199000"));

            createProduct("Beanie Hat", "Warm winter beanie",
                    ProductCategory.ACCESSORIES, new BigDecimal("149000"));

            createProduct("Canvas Backpack", "Durable canvas backpack",
                    ProductCategory.ACCESSORIES, new BigDecimal("399000"));

            createProduct("Leather Belt", "Genuine leather belt",
                    ProductCategory.ACCESSORIES, new BigDecimal("249000"));

            createProduct("Sunglasses", "UV protection sunglasses",
                    ProductCategory.ACCESSORIES, new BigDecimal("299000"));

            log.info("Created {} default products", productRepository.count());
        }
    }

    private void createProduct(String name, String description, ProductCategory category, BigDecimal basePrice) {
        Product product = Product.builder()
                .name(name)
                .description(description)
                .category(category)
                .basePrice(basePrice)
                .imageUrl("https://via.placeholder.com/500")
                .active(true)
                .variants(new ArrayList<>())
                .build();

        // Add variants for clothing items
        if (category != ProductCategory.ACCESSORIES) {
            // Add multiple color/size combinations
            addVariant(product, Color.BLACK, Size.S, 30);
            addVariant(product, Color.BLACK, Size.M, 50);
            addVariant(product, Color.BLACK, Size.L, 50);
            addVariant(product, Color.BLACK, Size.XL, 40);
            addVariant(product, Color.BLACK, Size.XXL, 30);

            addVariant(product, Color.WHITE, Size.S, 30);
            addVariant(product, Color.WHITE, Size.M, 50);
            addVariant(product, Color.WHITE, Size.L, 50);
            addVariant(product, Color.WHITE, Size.XL, 40);
            addVariant(product, Color.WHITE, Size.XXL, 30);

            addVariant(product, Color.GRAY, Size.S, 25);
            addVariant(product, Color.GRAY, Size.M, 40);
            addVariant(product, Color.GRAY, Size.L, 40);
            addVariant(product, Color.GRAY, Size.XL, 35);

            addVariant(product, Color.BLUE, Size.M, 30);
            addVariant(product, Color.BLUE, Size.L, 30);
            addVariant(product, Color.BLUE, Size.XL, 25);

            addVariant(product, Color.RED, Size.M, 25);
            addVariant(product, Color.RED, Size.L, 25);
        } else {
            // Accessories with color variants only
            addVariant(product, Color.BLACK, null, 100);
            addVariant(product, Color.WHITE, null, 80);
            addVariant(product, Color.GRAY, null, 70);
            addVariant(product, Color.BLUE, null, 60);
            addVariant(product, Color.RED, null, 50);
        }

        productRepository.save(product);
    }

    private void addVariant(Product product, Color color, Size size, int stock) {
        // Generate SKU: ProductName-Color-Size (e.g., "TSHIRT-BLACK-M")
        String skuBase = product.getName().toUpperCase()
                .replaceAll("[^A-Z0-9]", "")
                .substring(0, Math.min(10, product.getName().replaceAll("[^A-Z0-9]", "").length()));
        String sku = skuBase + "-" + color.name() + (size != null ? "-" + size.name() : "");

        ProductVariant variant = ProductVariant.builder()
                .product(product)
                .sku(sku)
                .color(color)
                .size(size)
                .price(product.getBasePrice()) // Set price from product's basePrice
                .stockQuantity(stock)
                .build();
        product.getVariants().add(variant);
    }
}
