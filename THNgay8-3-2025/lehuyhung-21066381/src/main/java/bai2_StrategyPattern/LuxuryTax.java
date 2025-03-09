package bai2_StrategyPattern;

public class LuxuryTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
        return price * 0.20;
    }
}
