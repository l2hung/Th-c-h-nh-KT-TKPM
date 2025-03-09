package Bai1_Statepattern;

public class OrderManagementSystem {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();
        order.processOrder(); // Kiểm tra thông tin đơn hàng
        order.processOrder(); // Đóng gói và vận chuyển
        order.processOrder(); // Cập nhật trạng thái đơn hàng

        // Kiểm thử hủy đơn hàng
        OrderContext canceledOrder = new OrderContext();
        canceledOrder.cancelOrder(); // Hủy đơn hàng và hoàn tiền
    }
}
