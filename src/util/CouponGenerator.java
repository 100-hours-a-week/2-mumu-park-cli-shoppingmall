package util;

import model.coupon.Coupon;

import java.time.LocalDateTime;
import java.util.Random;

public class CouponGenerator {
    private static final Random randomGenerator = new Random();

    private CouponGenerator() {
        throw new IllegalArgumentException("Utility class");
    }

    public static Coupon issueRandomCoupon() {
        int randomDiscountRate = randomGenerator.nextInt(99) + 1;
        LocalDateTime issueDate = LocalDateTime.now();

        return new Coupon(randomDiscountRate, issueDate);
    }
}
