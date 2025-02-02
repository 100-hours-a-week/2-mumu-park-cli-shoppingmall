package model.shoppingmall.history;

import dto.ChangeAndPoint;
import dto.OrderHistory;
import dto.PaymentInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryManagement {
    private final List<ShoppingHistory> histories;

    public HistoryManagement() {
        histories = new ArrayList<>();
    }

    public void saveOrder(LocalDateTime now, PaymentInfo paymentInfo, ChangeAndPoint changeAndPoint) {
        histories.add(
                new ShoppingHistory(
                        now,
                        paymentInfo.products(),
                        paymentInfo.couponRate(), paymentInfo.appliedPoint(), paymentInfo.finalTotalPrice(), paymentInfo.payAmount(),
                        changeAndPoint.change(), changeAndPoint.rewardPoint()
                )
        );
    }

    public List<OrderHistory> getOrderHistory() {
        return histories.stream()
                .map(ShoppingHistory::toOrderHistory)
                .toList();
    }
}
