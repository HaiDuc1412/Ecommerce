package fpt.haidd69.ecommerce.constants;

/**
 * Application-wide constants. Centralized location for all application
 * constants.
 */
public final class AppConstants {

    private AppConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // ========== PAGINATION ==========
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 100;

    // ========== INVENTORY ==========
    public static final int INVENTORY_RESERVATION_MINUTES = 15;

    // ========== ORDER ==========
    public static final int ORDER_TRACKING_CODE_LENGTH = 10;

    // ========== SECURITY & ROLES ==========
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // ========== ERROR MESSAGES ==========
    public static final String CART_NOT_FOUND = "Cart not found";
    public static final String ORDER_NOT_FOUND = "Order not found";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String PRODUCT_NOT_FOUND = "Product not found";
    public static final String PRODUCT_VARIANT_NOT_FOUND = "Product variant not found";
    public static final String INSUFFICIENT_STOCK = "Insufficient stock available";
    public static final String INVALID_ORDER_STATUS = "Invalid order status transition";

    // ========== LOG MESSAGES ==========
    public static final String LOG_EMAIL_SENT = "Email sent to: {}";
    public static final String LOG_EMAIL_FAILED = "Failed to send email to: {}";
    public static final String LOG_ORDER_CREATED = "Order created: {}";
    public static final String LOG_ORDER_STATUS_UPDATED = "Order {} status updated to {}";
}
