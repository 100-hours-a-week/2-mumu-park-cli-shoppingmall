package model.shoppingMall.history;

import model.coupon.Coupon;
import model.shoppingMall.product.Product;

import java.time.LocalDateTime;
import java.util.List;

public class ShoppingHistory {
    private final LocalDateTime orderDate;
    private final List<ProductSimpleInfo> purchaseProductInfos;
    private final Coupon applyCoupon;
    private final int usedPoint;
    private final int totalPrice;
    private final int payAmount;
    private final int change;
    private final int accumulatedPoint;

    public ShoppingHistory(LocalDateTime orderDate, List<ProductSimpleInfo> purchaseProductInfos, Coupon applyCoupon, int usedPoint, int totalPrice, int payAmount, int change, int accumulatedPoint) {
        this.orderDate = orderDate;
        this.purchaseProductInfos = purchaseProductInfos;
        this.applyCoupon = applyCoupon;
        this.usedPoint = usedPoint;
        this.totalPrice = totalPrice;
        this.payAmount = payAmount;
        this.change = change;
        this.accumulatedPoint = accumulatedPoint;
    }
}
