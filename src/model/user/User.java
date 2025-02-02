package model.user;

import dto.DiscountInfo;
import dto.PaymentInfo;
import model.coupon.Coupon;

import java.time.LocalDateTime;

public class User {
    private Coupon coupon;
    private int point;

    public User() {
        this.coupon = null;
        this.point = 1000;
    }

    public boolean hasCoupon(LocalDateTime now) {
        return coupon != null && !coupon.isExpired(now);
    }

    public void receiveCoupon(Coupon newCoupon) {
        coupon = newCoupon;
    }

    public int getCouponDiscountRate() {
        return coupon.getDiscountRate();
    }

    public int getPoint() {
        return point;
    }

    public DiscountInfo generateDiscountInfo(LocalDateTime now) {
        return new DiscountInfo(
                hasCoupon(now) ? coupon.getDiscountRate() : 0,
                point
        );
    }

    public void payProcess(PaymentInfo paymentInfo, int rewardPoint) {
        plusPoint(rewardPoint);
        useCoupon(paymentInfo.couponRate());
    }

    private void plusPoint(int rewardPoint) {
        this.point += rewardPoint;
    }

    private void useCoupon(int couponRate) {
        if (couponRate == 0) {
            return;
        }

        this.coupon = null;
    }
}
