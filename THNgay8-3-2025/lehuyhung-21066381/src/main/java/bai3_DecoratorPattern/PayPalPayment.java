package bai3_DecoratorPattern;

public class PayPalPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán " + amount + " bằng PayPal.");
    }
}