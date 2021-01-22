package com.vendingmachine.list;

public enum Product {

    COKE(25), PEPSI(35), SODA(45);
    private int price;

    private Product(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}
	