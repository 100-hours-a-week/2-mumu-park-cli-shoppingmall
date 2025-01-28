package model.shoppingMall.product;

public class Clothes extends Product {
    private final char size;

    public Clothes(String name, int price, int quantity, char size) {
        super(name, price, quantity);
        this.size = size;
    }
}
