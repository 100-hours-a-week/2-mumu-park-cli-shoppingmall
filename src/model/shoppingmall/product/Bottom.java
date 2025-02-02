package model.shoppingmall.product;

import dto.BottomDetailInfo;

public class Bottom extends Clothes{
    private final int length; // 기장
    private final int waist; // 허리둘레
    private final double rise; // 밑위
    private final double thigh; // 허벅지
    private final double hem; // 밑단

    public Bottom(String name, int price, int quantity, char size, int length, int waist, double rise, double thigh, double hem) {
        super(name, price, quantity, size);
        this.length = length;
        this.waist = waist;
        this.rise = rise;
        this.thigh = thigh;
        this.hem = hem;
    }

    public BottomDetailInfo generateDetailInfo() {
        return new BottomDetailInfo(
                this.name,
                this.size,
                this.length,
                this.waist,
                this.rise,
                this.thigh,
                this.hem,
                this.quantity,
                this.price
        );
    }
}
