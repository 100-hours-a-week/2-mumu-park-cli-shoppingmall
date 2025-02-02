package model.shoppingmall.product;

import dto.ProductSimpleInfo;

public class Clothes extends Product {
    protected final char size;

    public Clothes(String name, int price, int quantity, char size) {
        super(name, price, quantity);
        this.size = size;
    }

    public ProductSimpleInfo generateSimpleInfo() {
        return new ProductSimpleInfo(
                this.name,
                this.size,
                this.quantity,
                this.price
        );
    }

    @Override
    public Character getSize() {
        return this.size;
    }
}
