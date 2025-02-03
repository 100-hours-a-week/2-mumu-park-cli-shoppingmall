package validator;

import constant.exception.custom.LackPaymentAmountException;
import constant.exception.custom.ValidatorClassException;

public class PaymentValidator {

    private PaymentValidator() throws ValidatorClassException {
        throw new ValidatorClassException();
    }

    public static void checkValidPayAmount(int finalPrice, int payAmount) {
        if (finalPrice > payAmount) {
            throw new LackPaymentAmountException();
        }
    }
}
