package Bai1_Statepattern;

public class OrderContext {
    private OrderState state;

    public OrderContext() {
        this.state = new NewOrderState();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void processOrder() {
        state.handle(this);
    }

    public void cancelOrder() {
        setState(new CanceledOrderState());
        state.handle(this);
    }
}
