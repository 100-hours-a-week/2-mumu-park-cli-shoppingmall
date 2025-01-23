package model.user;

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
        if (coupon != null && coupon.isExpired(now)) {
            return true;
        }

        return false;
    }

    public void setCoupon(Coupon newCoupon) {
        coupon = newCoupon;
    }

    public int getCouponDiscountRate() {
        return coupon.getDiscountRate();
    }
}
