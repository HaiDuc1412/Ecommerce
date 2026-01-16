# 🛍️ E-Commerce Backend System (Phase 1)

## 📋 Tổng Quan

Hệ thống E-commerce Backend (Headless) được xây dựng cho **Hung Hypebeast Store** - một local brand thời trang.

**Công nghệ sử dụng:**
- ☕ Java 21
- 🍃 Spring Boot 4.0.1
- 🐘 PostgreSQL
- 🔐 Spring Security + JWT
- 📧 Spring Mail + Thymeleaf
- 📝 MapStruct
- 📚 OpenAPI/Swagger
- 🐳 Docker

---

## ✨ Tính Năng Đã Hoàn Thành

### 1. 🏷️ Catalog Management
- ✅ Quản lý sản phẩm với nhiều biến thể (Size, Color, SKU)
- ✅ Danh sách sản phẩm có phân trang và sắp xếp
- ✅ Filter theo category và khoảng giá
- ✅ Chi tiết sản phẩm

### 2. 🛒 Shopping Cart
- ✅ Thêm/xóa/cập nhật sản phẩm trong giỏ
- ✅ Kiểm tra tồn kho real-time
- ✅ Session-based cart (guest) và user-based cart
- ✅ Tự động validate số lượng với kho

### 3. 📦 Inventory Management (CRITICAL)
- ✅ **Giữ hàng tự động 10-15 phút** khi checkout
- ✅ **Pessimistic locking** để xử lý last item
- ✅ **Auto-release expired reservations** (scheduled job mỗi 5 phút)
- ✅ Atomic inventory operations
- ✅ Reserved quantity tracking

### 4. 💳 Checkout & Orders
- ✅ Checkout với thông tin giao hàng đầy đủ
- ✅ Hỗ trợ nhiều hình thức thanh toán:
  - COD (Cash on Delivery)
  - Bank Transfer
  - SePay (chuẩn bị cho Phase 2)
- ✅ Tự động tạo mã tracking
- ✅ Order confirmation

### 5. 📧 Email Notifications
- ✅ Email xác nhận đơn hàng (HTML template đẹp)
- ✅ Email cập nhật trạng thái đơn hàng
- ✅ Email xác nhận thanh toán
- ✅ Link tracking không cần đăng nhập
- ✅ Responsive email design

### 6. 🔍 Order Tracking
- ✅ Tracking bằng mã tracking code
- ✅ **Không cần đăng nhập** để xem trạng thái
- ✅ Timeline trạng thái đơn hàng
- ✅ Chi tiết đơn hàng đầy đủ

### 7. 👨‍💼 Admin Management
- ✅ Xem danh sách đơn hàng (phân trang)
- ✅ Filter đơn hàng theo trạng thái
- ✅ Cập nhật trạng thái đơn hàng
- ✅ Authentication & Authorization
- ✅ Auto-send email khi update status

### 8. 🔐 Authentication & Security
- ✅ JWT-based authentication
- ✅ Role-based access control (ADMIN, CUSTOMER)
- ✅ Secure password hashing
- ✅ Session management

---

## 🚀 Quick Start

### Prerequisites
- Java 21+
- PostgreSQL 13+
- Maven 3.8+
- Docker & Docker Compose (optional)

### 1. Clone Repository
```bash
git clone <repository-url>
cd Ecommerce
```

### 2. Database Setup

#### Option A: Using Docker Compose (Khuyến nghị)
```bash
docker-compose up -d
```

#### Option B: Manual PostgreSQL Setup
```bash
# Tạo database
createdb ecommerce_db

# Hoặc dùng psql
psql -U postgres
CREATE DATABASE ecommerce_db;
```

### 3. Configuration

Copy file `.env.example` thành `.env` và cấu hình:

```env
# Database
DB_HOST=localhost
DB_PORT=5432
DB_NAME=ecommerce_db
DB_USERNAME=postgres
DB_PASSWORD=root

# Server
SERVER_PORT=8080
APP_BASE_URL=http://localhost:8080

# Email (SMTP) - Xem hướng dẫn chi tiết ở EMAIL_SETUP_GUIDE.md
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-app-password
EMAIL_FROM=noreply@hunghypebeast.com
EMAIL_FROM_NAME=Hung Hypebeast Store

# JWT
JWT_SECRET=your-secret-key-here
JWT_EXPIRATION=86400000

# Admin credentials
ADMIN_PASSWORD=ChangeMe123!@#
CUSTOMER_PASSWORD=ChangeMe456!@#
```

**📧 Cấu hình Email:** Xem hướng dẫn chi tiết tại [EMAIL_SETUP_GUIDE.md](./EMAIL_SETUP_GUIDE.md)

### 4. Build & Run

#### Using Maven
```bash
# Build project
mvn clean install

# Run application
mvn spring-boot:run
```

#### Using Docker
```bash
# Build image
docker build -t ecommerce-backend .

# Run container
docker run -p 8080:8080 --env-file .env ecommerce-backend
```

### 5. Access Application

- **API Base URL:** http://localhost:8080
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **API Docs:** http://localhost:8080/api-docs
- **Health Check:** http://localhost:8080/actuator/health

---

## 📚 API Documentation

### Default Admin Account
```
Email: admin@ecommerce.com
Password: ChangeMe123!@# (hoặc giá trị trong ADMIN_PASSWORD)
```

### Default Customer Account
```
Email: customer@ecommerce.com
Password: ChangeMe456!@# (hoặc giá trị trong CUSTOMER_PASSWORD)
```

### Postman Collection

Import file `Ecommerce_API_Collection.postman_collection.json` vào Postman để test APIs.

**Environment:**
- Import `Ecommerce_API.postman_environment.json`
- Set `baseUrl` = `http://localhost:8080`

### Main API Endpoints

#### Authentication
```http
POST /api/auth/login
POST /api/auth/register
POST /api/auth/refresh
```

#### Products (Public)
```http
GET    /api/products              # List products (paginated)
GET    /api/products/{id}         # Get product detail
GET    /api/products/filter       # Filter by category, price
```

#### Cart (Session-based)
```http
GET    /api/cart                  # Get cart
POST   /api/cart/items            # Add to cart
PUT    /api/cart/items/{itemId}   # Update quantity
DELETE /api/cart/items/{itemId}   # Remove item
DELETE /api/cart                  # Clear cart
```

**Header Required:** `Session-Id: your-session-id`

#### Orders
```http
POST   /api/orders                    # Create order (checkout)
GET    /api/orders/track/{trackingCode}  # Track order (no auth required)
```

#### Admin Orders
```http
GET    /api/admin/orders              # List all orders
GET    /api/admin/orders?status=PAID  # Filter by status
PUT    /api/admin/orders/{id}/status  # Update order status
```

**Authentication Required:** Bearer Token với role ADMIN

---

## 🏗️ Architecture

### Database Schema (ERD)

```
┌─────────────┐
│   Product   │
├─────────────┤
│ id          │
│ name        │
│ description │
│ category    │
│ basePrice   │
│ imageUrl    │
│ active      │
└──────┬──────┘
       │
       │ 1:N
       ▼
┌─────────────────┐         ┌──────────────────┐
│ ProductVariant  │         │ InventoryReservation │
├─────────────────┤         ├──────────────────┤
│ id              │◄────────┤ variantId        │
│ product_id      │         │ sessionId        │
│ sku             │         │ quantity         │
│ size            │         │ expiresAt        │
│ color           │         └──────────────────┘
│ price           │
│ stockQuantity   │
│ reservedQuantity│
└────────┬────────┘
         │
         │ N:M
         ▼
┌─────────────┐       ┌──────────────┐       ┌────────────┐
│  CartItem   │       │  OrderItem   │       │   Order    │
├─────────────┤       ├──────────────┤       ├────────────┤
│ id          │       │ id           │       │ id         │
│ cart_id     │       │ order_id     │◄──────┤ orderNumber│
│ variant_id  │       │ variant_id   │       │ trackingCode│
│ quantity    │       │ quantity     │       │ status     │
└─────────────┘       │ price        │       │ totalAmount│
                      │ subtotal     │       │ paymentMethod│
                      └──────────────┘       │ customerInfo│
                                             └────────────┘
```

### Key Design Patterns

1. **Repository Pattern** - Data access layer
2. **Service Layer** - Business logic
3. **DTO Pattern** - Data transfer objects
4. **MapStruct** - Object mapping
5. **Builder Pattern** - Entity construction

### Critical Features Implementation

#### Inventory Reservation System
```java
@Transactional
public void reserveInventory(sessionId, variantId, quantity) {
    // 1. Lock variant row (Pessimistic Lock)
    variant = findByIdWithLock(variantId);
    
    // 2. Check available quantity
    if (variant.availableQuantity < quantity) throw error;
    
    // 3. Create reservation (expires in 10-15 min)
    reservation = new InventoryReservation(sessionId, variantId, quantity, expiresAt);
    
    // 4. Update reserved quantity
    variant.reservedQuantity += quantity;
}

// Auto-release expired reservations
@Scheduled(fixedRate = 300000) // Every 5 minutes
public void releaseExpiredReservations() {
    // Find expired, release inventory, delete reservations
}
```

---

## 📧 Email System

Hệ thống email sử dụng **Thymeleaf templates** với design responsive và professional.

### Email Templates
- `order-confirmation.html` - Xác nhận đơn hàng
- `order-status-update.html` - Cập nhật trạng thái
- `payment-confirmation.html` - Xác nhận thanh toán

### Configuration
Xem chi tiết tại [EMAIL_SETUP_GUIDE.md](./EMAIL_SETUP_GUIDE.md)

**Quick Setup:**
1. Tạo Gmail App Password
2. Update `.env` với credentials
3. Restart application
4. Test bằng cách tạo order mới

---

## 🧪 Testing

### Test với Postman
```bash
# Import collection
Ecommerce_API_Collection.postman_collection.json

# Import environment
Ecommerce_API.postman_environment.json
```

### Manual Test Flow

1. **Register/Login** để lấy JWT token
2. **Get Products** - xem danh sách sản phẩm
3. **Add to Cart** - thêm sản phẩm vào giỏ (dùng Session-Id header)
4. **Checkout** - tạo đơn hàng
5. **Check Email** - nhận email xác nhận
6. **Track Order** - dùng tracking code (không cần login)
7. **Admin Login** - đăng nhập admin
8. **Update Status** - cập nhật trạng thái đơn hàng

---

## 🔧 Configuration Files

### application.properties
Chứa tất cả cấu hình Spring Boot:
- Database connection
- JPA/Hibernate settings
- Email (SMTP) configuration
- JWT settings
- Swagger/OpenAPI config
- Actuator endpoints
- Scheduling config

### docker-compose.yml
Setup PostgreSQL database với Docker:
```yaml
services:
  postgres:
    image: postgres:16-alpine
    ports:
      - "5432:5432"
    environment:
      POSTGRES_DB: ecommerce_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: root
```

---

## 🐛 Troubleshooting

### Database Connection Error
```
Caused by: org.postgresql.util.PSQLException: Connection refused
```
**Solution:**
- Kiểm tra PostgreSQL đang chạy: `pg_isready`
- Kiểm tra port: `netstat -an | grep 5432`
- Kiểm tra credentials trong `.env`

### Email Not Sending
```
Failed to send email. Error: Authentication failed
```
**Solution:**
- Xem [EMAIL_SETUP_GUIDE.md](./EMAIL_SETUP_GUIDE.md)
- Kiểm tra App Password (Gmail)
- Kiểm tra SMTP credentials
- Enable debug: `logging.level.org.springframework.mail=DEBUG`

### Inventory Issues
```
InsufficientStockException: Insufficient stock
```
**Solution:**
- Đảm bảo có data seed (DataInitializer tự động chạy)
- Kiểm tra `reservedQuantity` trong database
- Clear expired reservations: Scheduled job chạy mỗi 5 phút

---

## 📝 API Usage Examples

### 1. Create Order (Checkout)
```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Session-Id: test-session-123" \
  -H "Content-Type: application/json" \
  -d '{
    "customerName": "Nguyễn Văn A",
    "customerEmail": "test@example.com",
    "customerPhone": "0901234567",
    "shippingAddress": "123 Đường ABC, Quận 1, TP.HCM",
    "paymentMethod": "COD",
    "notes": "Giao hàng giờ hành chính"
  }'
```

### 2. Track Order (No Auth Required)
```bash
curl http://localhost:8080/api/orders/track/{trackingCode}
```

### 3. Admin Update Status
```bash
curl -X PUT http://localhost:8080/api/admin/orders/{orderId}/status?status=SHIPPING \
  -H "Authorization: Bearer {admin-jwt-token}"
```

---

## 📦 Project Structure

```
src/main/java/fpt/haidd69/ecommerce/
├── config/              # Security, JWT, OpenAPI config
├── constants/           # App constants, error messages
├── controllers/         # REST API endpoints
├── dto/                 # Data Transfer Objects
├── entities/            # JPA entities
├── enums/               # Enums (Status, PaymentMethod, etc.)
├── exceptions/          # Custom exceptions
├── mappers/             # MapStruct mappers
├── repositories/        # JPA repositories
└── services/            # Business logic
    └── impl/            # Service implementations

src/main/resources/
├── templates/
│   └── emails/          # Thymeleaf email templates
├── static/              # Static resources
└── application.properties

```

---

## 🚧 Phase 2 Features (Upcoming)

- [ ] SePay webhook integration
- [ ] Admin product management APIs
- [ ] Product image upload
- [ ] Advanced filtering (search, sort)
- [ ] Order history for customers
- [ ] Wishlist
- [ ] Product reviews & ratings
- [ ] Discount codes & promotions
- [ ] Shipping cost calculation
- [ ] Analytics & reports

---

## 👥 Contributors

- **Developer:** [Your Name]
- **Client:** Anh Hùng - Founder Hung Hypebeast

---

## 📄 License

This project is proprietary software for Hung Hypebeast Store.

---

## 🙏 Acknowledgments

- Spring Boot Team
- PostgreSQL Community
- Thymeleaf Team
- MapStruct Contributors

---

## 📞 Support

For issues and questions:
- **Email:** support@hunghypebeast.com
- **Documentation:** Check [EMAIL_SETUP_GUIDE.md](./EMAIL_SETUP_GUIDE.md) for email setup
- **API Docs:** http://localhost:8080/swagger-ui.html

---

**Built with ❤️ for Hung Hypebeast Store**
