package model.shoppingMall;

public class Product {
    private String name;
    private int price;
    private int quantity;

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
