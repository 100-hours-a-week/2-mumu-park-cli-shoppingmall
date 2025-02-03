package constant.exception.custom;

import constant.exception.ErrorMessage;

public class OverPurchaseQuantityException extends RuntimeException{
    public OverPurchaseQuantityException() {
        super(ErrorMessage.OVER_PURCHASE_QUANTITY.getMessage());
    }
}
