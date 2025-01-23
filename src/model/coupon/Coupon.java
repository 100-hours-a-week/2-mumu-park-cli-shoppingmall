package model.coupon;

import java.time.LocalDateTime;

public class Coupon {
    private int discountRate;
    private LocalDateTime issueDate;
    private LocalDateTime expireDate;

    public Coupon(int discountRate, LocalDateTime issueDate) {
        this.discountRate = discountRate;
        this.issueDate = issueDate;
        this.expireDate = issueDate.plusMinutes(5);
    }

    public boolean isExpired(LocalDateTime now) {
        if (now.isAfter(issueDate) && now.isBefore(expireDate)) {
            return true;
        }

        return false;
    }

    public int getDiscountRate() {
        return discountRate;
    }
}
