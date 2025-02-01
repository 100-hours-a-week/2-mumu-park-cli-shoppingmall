package dto;

import java.util.List;

public record PaymentInfo(
        List<ProductSimpleInfo> products,
        int couponRate,
        int appliedPoint,
        int finalTotalPrice,
        int payAmount
) {
}
