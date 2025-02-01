package dto;

import java.time.LocalDateTime;

public record OrderHistory(
        LocalDateTime orderDate,
        String orderProductInfo,
        int couponRate,
        int usedPoint,
        int totalPrice,
        int payAmount,
        int change,
        int rewardPoint
) {
}
