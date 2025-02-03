package constant.exception.custom;

import constant.exception.ErrorMessage;

public class LackPaymentAmountException extends RuntimeException{

    public LackPaymentAmountException() {
        super(ErrorMessage.LACK_PAYMENT_AMOUNT.getMessage());
    }
}
