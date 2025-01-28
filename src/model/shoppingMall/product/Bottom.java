package model.shoppingMall.product;

public class Bottom extends Clothes{
    private int length;
    private int waist;

    public Bottom(String name, int price, int quantity, char size, int length, int waist) {
        super(name, price, quantity, size);

        this.length = length;
        this.waist = waist;
    }
}
