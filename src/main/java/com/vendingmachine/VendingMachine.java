package com.vendingmachine;

import com.vendingmachine.exception.InsertCoinsFirstException;
import com.vendingmachine.exception.InsufficientCoinsException;
import com.vendingmachine.list.Coin;
import com.vendingmachine.list.Product;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine implements IVendingMachine {
    List<Coin> moneyBox = new ArrayList<>();
    List<Product> productRack = new ArrayList<>();

    @Override
    public void chooseProduct(Product product) {
        int productPrice = product.getPrice();
        if (coinsValue() == 0)
            throw new InsertCoinsFirstException("First you need to insert coins");
        if (coinsValue() < productPrice)
            throw new InsufficientCoinsException("Insufficient coins for the product you choosen.\n" +
                    "Either insert more coins or choose different product");
        productRack.add(product);
    }

    @Override
    public void insertCoin(Coin coin) {
        moneyBox.add(coin);
    }

    @Override
    public Product pickProduct() {
        return productRack.get(0);
    }

    @Override
    public Product selectedProduct() {
        return productRack.get(0);
    }

    @Override
    public List<Coin> pickChange() {
        int productPrice = productRack.get(0).getPrice();
        if (productPrice == coinsValue())
            return null;
        return getChange(coinsValue() - productPrice);
    }

    @Override
    public List<Coin> refund() {
        return moneyBox;
    }

    @Override
    public void reset() {
        moneyBox.clear();
        productRack.clear();
    }

    @Override
    public Integer coinsValue() {
        return moneyBox.stream().map(Coin::getDenomination).reduce(0, Integer::sum);
    }

    private List<Coin> getChange(int balance) {
        List<Coin> change = new ArrayList<>();
        while (balance > 0) {
            change.add(Coin.NICKLE);
            balance -= Coin.NICKLE.getDenomination();
            continue;
        }
        return change;
    }

}
