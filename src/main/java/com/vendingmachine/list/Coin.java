package com.vendingmachine.list;

public enum Coin {
    PENNY(1), NICKLE(5), DIME(10), QUARTER(25);
    private Integer price;

    private Coin(Integer price) {
        this.price = price;
    }

    public Integer getDenomination() {
        return price;
    }

}
