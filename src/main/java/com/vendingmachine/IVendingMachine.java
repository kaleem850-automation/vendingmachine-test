package com.vendingmachine;

import com.vendingmachine.list.Coin;
import com.vendingmachine.list.Product;

import java.util.List;

public interface IVendingMachine {
    void chooseProduct(Product product);
    void insertCoin(Coin coin);
    Product pickProduct();
    Product selectedProduct();
    List<Coin> pickChange();
    List<Coin> refund();
    void reset();
    Integer coinsValue();
}
