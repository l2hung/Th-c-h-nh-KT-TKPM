package bai3_DecoratorPattern;

public class PaymentSystem {
        public static void main(String[] args) {
            // Ví dụ 1: Thanh toán bằng Thẻ tín dụng + Phí xử lý
            System.out.println("=== Ví dụ 1: Thanh toán bằng Thẻ tín dụng + Phí xử lý ===");
            Payment creditCard = new CreditCardPayment();
            Payment creditCardWithFee = new ProcessingFeeDecorator(creditCard);
            creditCardWithFee.pay(1000);

            // Ví dụ 2: Thanh toán bằng PayPal + Mã giảm giá
            System.out.println("\n=== Ví dụ 2: Thanh toán bằng PayPal + Mã giảm giá ===");
            Payment paypal = new PayPalPayment();
            Payment paypalWithDiscount = new DiscountDecorator(paypal);
            paypalWithDiscount.pay(1000);

            // Ví dụ 3: Thanh toán bằng Thẻ tín dụng + Phí xử lý + Mã giảm giá
            System.out.println("\n=== Ví dụ 3: Thanh toán bằng Thẻ tín dụng + Phí xử lý + Mã giảm giá ===");
            Payment complexPayment = new DiscountDecorator(new ProcessingFeeDecorator(new CreditCardPayment()));
            complexPayment.pay(1000);
        }
    }