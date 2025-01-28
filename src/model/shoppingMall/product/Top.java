package model.shoppingMall.product;

import model.shoppingMall.product.Clothes;

public class Top extends Clothes {
    private final int length; // 기장
    private final double shoulder; // 어깨너비
    private final double chest; // 가슴둘레
    private final double sleeves; // 소매길이

    public Top(String name, int price, int quantity, char size, int length, double shoulder, double chest, double sleeves) {
        super(name, price, quantity, size);
        this.length = length;
        this.shoulder = shoulder;
        this.chest = chest;
        this.sleeves = sleeves;
    }
}
