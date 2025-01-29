package model.shoppingMall.product;

public abstract class Product {
    protected final String name;
    protected final int price;
    protected int quantity;

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void minusQuantity(int purchaseQuantity) {
        // Todo : 0개일때 예외처리
        this.quantity -= purchaseQuantity;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return price;
    }

    public Character getSize() {
        return null;
    }

    public boolean isValidPurchaseQuantity(int purchaseQuantity) {
        return quantity >= purchaseQuantity;
    }
}
