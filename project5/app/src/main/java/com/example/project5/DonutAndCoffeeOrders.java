package com.example.project5;
import android.app.Application;

public class DonutAndCoffeeOrders extends Application {
    private StoreOrders storeOrders;
    private Order currentOrder;

    /**
     * Initializes variables when the app launches.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        storeOrders = new StoreOrders();
        currentOrder = new Order();
    }

    /**
     * StoreOrders accessor
     *
     * @return current StoreOrders
     */
    public StoreOrders getStoreOrders() {
        return storeOrders;
    }

    /**
     * StoreOrders mutator
     *
     * @param storeOrders new StoreOrders
     */
    public void setStoreOrders(StoreOrders storeOrders) {
        this.storeOrders = storeOrders;
    }

    /**
     * currentOrder accessor
     *
     * @return current Order
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * currentOrder mutator
     *
     * @param currentOrder new currentOrder
     */
    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    /**
     * Completes the current order.
     */
    public void submitOrder() {
        storeOrders.add(currentOrder);
        currentOrder = new Order();
    }
}
