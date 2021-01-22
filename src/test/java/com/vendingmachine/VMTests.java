package com.vendingmachine;

import com.vendingmachine.exception.InsertCoinsFirstException;
import com.vendingmachine.exception.InsufficientCoinsException;
import com.vendingmachine.list.Coin;
import com.vendingmachine.list.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VMTests {

    VendingMachine vendingMachine;

    @BeforeEach
    void vendingMachineSetUp() {
        vendingMachine = new VendingMachine();
    }

    @AfterEach
    void resetVendingMachine() {
        vendingMachine.reset();
    }

    @DisplayName("Accept Penny")
    @Test
    void testAcceptPenny() {
        vendingMachine.insertCoin(Coin.PENNY);
        int totalMoney = vendingMachine.coinsValue();
        assertEquals(1, totalMoney);
    }

    @DisplayName("Accept Nickel")
    @Test
    void testAcceptNickel() {
        vendingMachine.insertCoin(Coin.NICKLE);
        int totalMoney = vendingMachine.coinsValue();
        assertEquals(5, totalMoney);
    }

    @DisplayName("Accept Dime")
    @Test
    void testAcceptDime() {
        vendingMachine.insertCoin(Coin.DIME);
        int totalMoney = vendingMachine.coinsValue();
        assertEquals(10, totalMoney);
    }

    @DisplayName("Accept Quarter")
    @Test
    void testAcceptQuarter() {
        vendingMachine.insertCoin(Coin.QUARTER);
        int totalMoney = vendingMachine.coinsValue();
        assertEquals(25, totalMoney);
    }

    @DisplayName("Select Coke")
    @Test
    void testSelectCoke() {
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.chooseProduct(Product.COKE);
        //Assert product Selected
        Product product = vendingMachine.selectedProduct();
        assertEquals(Product.COKE, product);
    }

    @DisplayName("Select Pepsi")
    @Test
    void testSelectPepsi() {
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.chooseProduct(Product.PEPSI);
        //Assert product Selected
        Product product = vendingMachine.selectedProduct();
        assertEquals(Product.PEPSI, product);
    }

    @DisplayName("Select Soda")
    @Test
    void testSelectSoda() {
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.chooseProduct(Product.SODA);
        //Assert product Selected
        Product product = vendingMachine.selectedProduct();
        assertEquals(Product.SODA, product);
    }

    @DisplayName("Pick Product and Change")
    @Test
    void testPickProductAndChange() {
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.chooseProduct(Product.SODA);
        //Assert product Selected
        Product product = vendingMachine.selectedProduct();
        assertEquals(Product.SODA, product);
        //Assert for change
        List<Coin> change = vendingMachine.pickChange();
        assertEquals(Coin.NICKLE, change.get(0));
    }

    @DisplayName("Refund")
    @Test
    void testRefund() {
        vendingMachine.insertCoin(Coin.QUARTER);
        //Assert for refund
        List<Coin> change = vendingMachine.refund();
        assertEquals(Coin.QUARTER, change.get(0));
    }

    @DisplayName("Test Exception Insufficient Coins")
    @Test
    void testInsufficientCoins() {
        vendingMachine.insertCoin(Coin.QUARTER);
        assertThrows(InsufficientCoinsException.class, () -> vendingMachine.chooseProduct(Product.SODA));
    }

    @DisplayName("Test Exception to Insert Coins First")
    @Test
    void testInsertCoinsFirst() {
        assertThrows(InsertCoinsFirstException.class, () -> vendingMachine.chooseProduct(Product.COKE));
    }
}
