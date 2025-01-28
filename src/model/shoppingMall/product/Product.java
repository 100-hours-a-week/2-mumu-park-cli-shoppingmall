package model.shoppingMall.product;

import dto.ProductSimpleInfo;

import java.util.List;

public abstract class Product {
    protected final String name;
    protected final int price;
    protected int quantity;

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void minusQuantity() {
        // Todo : 0개일때 예외처리
        this.quantity--;
    }
}
