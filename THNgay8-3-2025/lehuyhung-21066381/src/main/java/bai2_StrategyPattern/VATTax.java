package bai2_StrategyPattern;

public class VATTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
        return price * 0.10;
    }
}
