package com.synapse.erp.model;

public class ErpOrder {

    private String orderId;
    private String product;
    private int quantity;
    private String destination;

    public ErpOrder() {}

    public ErpOrder(String orderId, String product, int quantity, String destination) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.destination = destination;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
}
