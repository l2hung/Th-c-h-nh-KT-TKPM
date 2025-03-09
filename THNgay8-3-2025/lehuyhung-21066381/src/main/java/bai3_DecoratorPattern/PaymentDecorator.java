package bai3_DecoratorPattern;

public abstract class PaymentDecorator implements Payment {
    protected Payment payment;

    public PaymentDecorator(Payment payment) {
        this.payment = payment;
    }

    @Override
    public abstract void pay(double amount);
}