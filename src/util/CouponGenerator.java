package util;

import constant.exception.custom.UtilityClassException;
import model.coupon.Coupon;

import java.time.LocalDateTime;
import java.util.Random;

public class CouponGenerator {
    private static final int MAX_COUPON_RATE = 99;
    private static final Random randomGenerator = new Random();

    private CouponGenerator() throws UtilityClassException {
        throw new UtilityClassException();
    }

    public static Coupon issueRandomCoupon() {
        int randomDiscountRate = randomGenerator.nextInt(MAX_COUPON_RATE) + 1;
        LocalDateTime issueDate = LocalDateTime.now();

        return new Coupon(randomDiscountRate, issueDate);
    }
}
