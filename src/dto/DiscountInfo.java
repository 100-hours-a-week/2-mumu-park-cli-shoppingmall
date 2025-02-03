package dto;

import constant.exception.custom.NoSuchPointException;
import validator.CouponValidator;
import validator.PointValidator;

import java.awt.*;
import java.util.List;

public class DiscountInfo {
    private final int couponRate;
    private int totalPoint;
    private int appliedPoint;
    private boolean isCouponUsed;

    public DiscountInfo(int couponRate, int point) {
        this.couponRate = couponRate;
        this.totalPoint = point;
        this.appliedPoint = 0;
        this.isCouponUsed = false;
    }

    public void applyCoupon() {
        CouponValidator.checkExistCouponToApply(couponRate, isCouponUsed);
        isCouponUsed = true;
    }

    public int calculateCouponAppliedPrice(int totalPrice) {
        if (!isCouponUsed) return 0;

        return (int) Math.round(totalPrice * (couponRate / 100.0));
    }

    public void usePoint(List<ProductSimpleInfo> products) {
        PointValidator.checkExistPointToUse(totalPoint);

        int finalPrice = getFinalPrice(products);
        if (finalPrice < totalPoint) {
            this.totalPoint = totalPoint - finalPrice;
            this.appliedPoint = appliedPoint + finalPrice;
        } else {
            this.appliedPoint += totalPoint;
            this.totalPoint = 0;
        }

    }

    public int getFinalPrice(List<ProductSimpleInfo> products) {
        int totalPrice = 0;
        for (ProductSimpleInfo product : products) {
            totalPrice += product.price();
        }

        totalPrice -= calculateCouponAppliedPrice(totalPrice);
        totalPrice -= appliedPoint;

        return totalPrice;
    }

    public boolean isCouponUsed() {
        return isCouponUsed;
    }

    public int getCouponRate() {
        return couponRate;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public int getAppliedPoint() {
        return appliedPoint;
    }
}
