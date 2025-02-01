package model.coupon;

import java.time.LocalDateTime;

public class Coupon {
    private final int discountRate;
    private final LocalDateTime issueDate;
    private final LocalDateTime expireDate;

    public Coupon(int discountRate, LocalDateTime issueDate) {
        this.discountRate = discountRate;
        this.issueDate = issueDate;
        this.expireDate = issueDate.plusMinutes(5);
    }

    public boolean isExpired(LocalDateTime now) {
        return now.isAfter(expireDate);
    }

    public int getDiscountRate() {
        return discountRate;
    }
}
