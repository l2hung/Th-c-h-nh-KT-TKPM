package bai2_StrategyPattern;
public class Product {
    private String name;
    private double price;
    private TaxStrategy taxStrategy;

    public Product(String name, double price, TaxStrategy taxStrategy) {
        this.name = name;
        this.price = price;
        this.taxStrategy = taxStrategy;
    }

    public void setTaxStrategy(TaxStrategy taxStrategy) {
        this.taxStrategy = taxStrategy;
    }

    public double getFinalPrice() {
        return price + taxStrategy.calculateTax(price);
    }

    public void displayInfo() {
        System.out.println("Sản phẩm: " + name);
        System.out.println("Giá gốc: " + price);
        System.out.println("Thuế: " + taxStrategy.calculateTax(price));
        System.out.println("Giá sau thuế: " + getFinalPrice());
        System.out.println("------------------------");
    }
}
