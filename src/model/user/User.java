package model.user;

import dto.DiscountInfo;
import model.coupon.Coupon;

import java.time.LocalDateTime;

public class User {
    private Coupon coupon;
    private int point;

    public User() {
        this.coupon = null;
        this.point = 0;
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
        int discountRate = hasCoupon(now)
                ?
                coupon.getDiscountRate()
                : 0;

        return new DiscountInfo(
                discountRate,
                point
        );
    }

    public void plusPoint(int accumulatedPoint) {
        this.point += accumulatedPoint;
    }
}
