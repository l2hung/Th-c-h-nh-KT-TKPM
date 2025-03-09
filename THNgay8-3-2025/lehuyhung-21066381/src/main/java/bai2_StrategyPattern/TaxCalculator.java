package bai2_StrategyPattern;

public class TaxCalculator {
    public static void main(String[] args) {
        Product book = new Product("Sách", 100, new VATTax());
        Product luxuryBag = new Product("Túi Xách Hàng Hiệu", 1000, new LuxuryTax());
        Product essentialProduct = new Product("Nước Suối", 50, new NoTax());

        book.displayInfo();
        luxuryBag.displayInfo();
        essentialProduct.displayInfo();

        // Thay đổi loại thuế động
        System.out.println("Cập nhật thuế cho sản phẩm...");
        luxuryBag.setTaxStrategy(new VATTax());
        luxuryBag.displayInfo();
    }
}
