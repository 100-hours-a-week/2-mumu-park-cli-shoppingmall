package model.shoppingMall;

public class Top extends Clothes {
    private int length;

    public Top(String name, int price, int quantity, char size, int length) {
        super(name, price, quantity, size);
        this.length = length;
    }
}
