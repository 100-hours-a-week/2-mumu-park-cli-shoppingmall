package constant.exception.custom;

import constant.exception.ErrorMessage;

public class NoSuchProductInCartException extends RuntimeException{
    public NoSuchProductInCartException() {
        super(ErrorMessage.PRODUCT_DOES_NOT_EXIST_IN_CART.getMessage());
    }
}
