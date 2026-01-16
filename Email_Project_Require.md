# EMAIL YÊU CẦU DỰ ÁN

**From:** Anh Hùng – Founder "Hung Hypebeast"  
**To:** Team Dev  
**Subject:** Cần làm gấp Backend hệ thống e-commerce mới (Phase 1)

---

Chào team,

Như đã trao đổi cafe hôm qua, hiện tại shop anh đang **vỡ trận vì quản lý thủ công**.  
Anh cần build gấp **hệ thống backend e-commerce**:

- Chưa cần giao diện
- Đã có team Front-end
- Cần API để gọi
- Hoàn thành trong **2 tuần** để kịp sale cuối tháng

Dưới đây là các vấn đề cần giải quyết:

---

## 1. Hàng hóa & Hiển thị (Catalog)

### Quản lý
- Một sản phẩm có nhiều biến thể:
  - Size (M, L, XL)
  - Màu (Đen, Trắng)
- Quản lý theo từng **SKU**

### Hiển thị
- Danh sách sản phẩm:
  - Load nhanh
  - Có phân trang
- Có filter:
  - Khoảng giá
  - Loại áo (áo thun, hoodie…)

---

## 2. Giỏ hàng (Shopping Cart)
- Thêm sản phẩm vào giỏ
- Tăng / giảm số lượng
- Xóa sản phẩm
- Kiểm tra tồn kho cơ bản
  - Không cho thêm 10 cái khi kho chỉ còn 2

---

## 3. Inventory – Critical

> Vấn đề **“Hết hàng mà vẫn bán”**

- Khi khách vào trang checkout:
  - Giữ hàng **10–15 phút**
  - Người khác không mua được
- Hết thời gian → nhả hàng
- Xử lý chính xác **Last Item**

---

## 4. Thanh toán & Đơn hàng (Checkout)

### Quy trình
1. Nhập thông tin ship
2. Chọn thanh toán
3. Bấm "Đặt hàng"
4. Hệ thống tạo đơn

### Hình thức thanh toán
- **COD**: Tạo đơn là xong
- **Chuyển khoản (SePay)**
  - Tự động nhận biết đơn đã thanh toán
  - Nếu không kịp → để phase sau

---

## 5. Theo dõi đơn hàng (Tracking)

- Gửi email xác nhận sau khi mua
- Email có link xem trạng thái đơn:
  - Đã xác nhận
  - Đã thanh toán
  - Đang giao
- **Không yêu cầu đăng nhập**
  - Bấm link là xem được

---

## 6. Admin

### Chưa cần (Phase sau)
- API tạo / sửa sản phẩm
- Nhập liệu trực tiếp DB

### Cần ngay
- API xem danh sách đơn hàng
- API cập nhật trạng thái đơn:
  - Đã thanh toán → Đang giao
  - Hủy đơn

---

**Tóm lại:**
- 2 tuần
- Backend API ổn định
- Có Catalog, Cart, Inventory chuẩn
- Có Email tracking
- Admin xử lý đơn hàng

Báo giá và phương án sớm cho anh nhé.
