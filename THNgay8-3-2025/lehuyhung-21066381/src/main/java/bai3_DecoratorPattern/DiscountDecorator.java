package bai3_DecoratorPattern;

public class DiscountDecorator extends PaymentDecorator {
    public DiscountDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        double discount = amount * 0.1; // 10% giảm giá
        payment.pay(amount - discount);
        System.out.println("Đã áp dụng mã giảm giá: " + discount);
    }
}