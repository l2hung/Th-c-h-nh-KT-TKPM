package bai3_DecoratorPattern;

public class ProcessingFeeDecorator extends PaymentDecorator {
    public ProcessingFeeDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        double fee = amount * 0.02; // 2% phí xử lý
        payment.pay(amount + fee);
        System.out.println("Đã thêm phí xử lý: " + fee);
    }
}