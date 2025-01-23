package view;

public class OutputView {

    public void printExceptionMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }

    public void printIssuedCoupon(int couponDiscountRate) {
        System.out.println("축하드립니다. " + couponDiscountRate + "% 쿠폰을 발급해드렸습니다.");
        System.out.println("유효기간은 5분 입니다.");
    }

    public void printUserPoint(int userPoint) {
        System.out.println("고객님의 포인트는 " + userPoint + "원 존재합니다.\n");
    }
}
