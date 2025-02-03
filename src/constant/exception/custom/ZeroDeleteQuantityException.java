package constant.exception.custom;

import constant.exception.ErrorMessage;

public class ZeroDeleteQuantityException extends RuntimeException{
    public ZeroDeleteQuantityException() {
        super(ErrorMessage.ZERO_DELETE_QUANTITY.getMessage());
    }
}
