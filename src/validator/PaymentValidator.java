package validator;

import constant.ErrorMessage;

public class PaymentValidator {

    private PaymentValidator() {
        throw new IllegalArgumentException(ErrorMessage.VALIDATOR_CLASS.getMessage());
    }

    public static void checkValidPayAmount(int finalPrice, int payAmount) {
        if (finalPrice > payAmount) {
            throw new IllegalArgumentException(ErrorMessage.LACK_PAYMENT_AMOUNT.getMessage());
        }
    }
}
