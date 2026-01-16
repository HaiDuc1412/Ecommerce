# ASSIGNMENT INSTRUCTION  
## E-COMMERCE BACKEND SYSTEM (PHASE 1)

## I. TỔNG QUAN
Học viên sẽ đóng vai trò là **Backend Developer** tiếp nhận dự án từ khách hàng  
(**Anh Hùng – Founder Local Brand**).

Dựa trên yêu cầu đầu vào, học viên cần:
- Phân tích
- Thiết kế
- Hiện thực hóa hệ thống **Backend (Headless)**

⏱ Thời gian thực hiện: **2 tuần**

---

## II. SẢN PHẨM BÀN GIAO (DELIVERABLES)

Học viên cần nộp **02 thành phần chính**:

### 1. Báo cáo kỹ thuật (Technical Report)

**Định dạng:** PDF hoặc Markdown  
**Nội dung bao gồm:**

#### (1) Đánh giá sơ bộ & Phân tích yêu cầu
- **Xác định Scope**
  - Dựa trên email khách hàng
  - Liệt kê:
    - Must-have
    - Nice-to-have
  - Phạm vi trong 2 tuần

- **Gap Analysis**
  - So sánh giữa:
    - Yêu cầu thô (Email)
    - Yêu cầu kỹ thuật thực tế

- **Đánh giá khả năng hoàn thiện**
  - Cam kết hoàn thành bao nhiêu %
  - Nếu không kịp:
    - Đề xuất cắt giảm tính năng
    - Ví dụ: Giả lập SePay Webhook thay vì tích hợp thật

#### (2) Thiết kế hệ thống (System Design)

- **DB Design**
  - Sơ đồ ERD
  - Chi tiết:
    - Bảng
    - Kiểu dữ liệu
    - Quan hệ
  - Giải thích lý do thiết kế

- **LLD – Low Level Design**
  - Danh sách API Endpoints:
    - Method
    - URL
    - Description
  - **Sequence Diagram**
    - Cho các luồng quan trọng hoặc phức tạp

---

### 2. Source Code & Hướng dẫn

- **Source Code**
  - Link GitHub Repository
  - Yêu cầu:
    - Cấu trúc rõ ràng
    - Clean Architecture / Modular

- **Setup Guide (README.md)**
  - Cài đặt môi trường
    - Node version
    - Database setup
  - Chạy Migration / Seed data
  - Chạy server

- **API Collection**
  - Postman / Insomnia export  
  - Hoặc Swagger link

---

## III. CÁC HOẠT ĐỘNG

### 1. Giai đoạn Đánh giá & Phân tích (Ngày 1–2)
- Đọc kỹ Email khách hàng
- Soạn thảo đặc tả nghiệp vụ
- Tự đặt câu hỏi **“Tại sao?”**
  - Ví dụ:
    - Tại sao phải giữ hàng 10 phút?
    - Nếu server sập thì sao?

- Đánh giá khả năng hoàn thiện
- So sánh scope với năng lực phát triển
- Đề xuất điều chỉnh scope nếu cần

> Lưu ý: Đây là quá trình nego thực tế khi presale

---

### 2. Giai đoạn Thiết kế (Ngày 3–4)
- Vẽ ERD
- Chốt trường dữ liệu & ràng buộc
- Thiết kế API Contract
  - Input
  - Output
  - Error codes
- Phương án kỹ thuật cho luồng quan trọng
  - Ví dụ: Bài toán **Last Item**
    - DB Lock
    - Atomic Update
    - Redis

---

### 3. Giai đoạn Code & Test (Ngày 5–14)
- Dựng skeleton project
- Implement tính năng
- Self-test bằng Postman
- Viết README
