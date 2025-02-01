package model.shoppingMall.payment;

import dto.ChangeAndPoint;
import dto.PaymentInfo;

public class PaymentManagement {

    public PaymentManagement() {
    }

    public ChangeAndPoint calculateChangeAndRewardPoint(PaymentInfo paymentInfo) {
        return new ChangeAndPoint(
                calculateChange(paymentInfo.payAmount(), paymentInfo.finalTotalPrice()),
                calculateRewardPoint(paymentInfo.finalTotalPrice())
        );
    }

    private int calculateChange(int payAmount, int finalTotalPrice) {
        return payAmount - finalTotalPrice;
    }

    private int calculateRewardPoint(int finalTotalPrice) {
        return (int) (finalTotalPrice * 0.1);
    }
}
