package model.shoppingMall.history;

import dto.OrderHistory;
import dto.ProductSimpleInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;

public class ShoppingHistory {
    private final LocalDateTime orderDate;
    private final List<ProductSimpleInfo> purchaseProductInfos;
    private final int couponRate;
    private final int usedPoint;
    private final int totalPrice;
    private final int payAmount;
    private final int change;
    private final int rewardPoint;

    public ShoppingHistory(LocalDateTime orderDate, List<ProductSimpleInfo> purchaseProductInfos, int couponRate, int usedPoint, int totalPrice, int payAmount, int change, int rewardPoint) {
        this.orderDate = orderDate;
        this.purchaseProductInfos = purchaseProductInfos;
        this.couponRate = couponRate;
        this.usedPoint = usedPoint;
        this.totalPrice = totalPrice;
        this.payAmount = payAmount;
        this.change = change;
        this.rewardPoint = rewardPoint;
    }

    public OrderHistory toOrderHistory() {
        return new OrderHistory(
                orderDate,
                formatProductInfo(),
                couponRate,
                usedPoint,
                totalPrice,
                payAmount,
                change,
                rewardPoint
        );
    }

    private String formatProductInfo() {
        StringJoiner result = new StringJoiner(", ");

        for (ProductSimpleInfo purchaseProductInfo : purchaseProductInfos) {
            result.add(purchaseProductInfo.name() + " "
                    + purchaseProductInfo.size() + "사이즈 "
                    + purchaseProductInfo.quantity() + "개");
        }

        return result.toString();
    }
}
